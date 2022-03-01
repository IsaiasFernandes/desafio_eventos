package com.example.appeventos.util.architecture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map

abstract class ResourceOf<T>() {

    var element: T? = null
        private set
    private val _callStarter = MutableLiveData<Unit>()

    private val resource = _callStarter
        .switchMap { loadResource() }
        .asFlow()

    private val hasError = resource
        .distinctUntilChanged()
        .map { it is ResultOf.Failure }

    val data = resource
        .filterIsInstance<ResultOf.Success<T>>()
        .distinctUntilChanged()
        .map { it.value }
        .asLiveData()

    val error = resource
        .filterIsInstance<ResultOf.Failure<T>>()
        .distinctUntilChanged()
        .map { it.error }
        .asLiveData()

    private val _onLoadStarted = MutableLiveData<Event<Unit>>()
    val onLoadStarted : LiveData<Event<Unit>> = _onLoadStarted

    private val _onDataLoaded = MutableLiveData<Event<T>>()
    val onDataLoaded : LiveData<Event<T>> = _onDataLoaded

    private val _onErrorThrown = MutableLiveData<Event<Throwable>>()
    val onErrorThrown : LiveData<Event<Throwable>> = _onErrorThrown

    private val loading = resource.map { it is ResultOf.Loading }

    val showData = combine(hasError, loading) { error, load -> !error && !load }
        .distinctUntilChanged()
        .asLiveData()

    val showError = combine(hasError, loading) { error, load -> error && !load }
        .distinctUntilChanged()
        .asLiveData()

    val showLoading = loading
        .distinctUntilChanged()
        .asLiveData()

    fun loadData() {
        _callStarter.value = Unit
    }

    private fun loadResource(): LiveData<ResultOf<T>> = liveData {
        element = null
        _onDataLoaded.value?.markHandled()
        _onErrorThrown.value?.markHandled()

        emit(ResultOf.Loading())
        _onLoadStarted.value = Event(Unit)
        try {
            val value = resource()
            element = value
            emit(ResultOf.Success(value))
            _onDataLoaded.value = Event(value)
        } catch (error: Throwable) {
            emit(ResultOf.Failure(error))
            _onErrorThrown.value = Event(error)
        }
    }

    protected abstract suspend fun resource(): T
}

fun <T> resource(func: suspend () -> T): ResourceOf<T> {
    return object : ResourceOf<T>() {
        override suspend fun resource() = func()
    }
}

sealed class ResultOf<out T> {
    data class Success<out R>(val value: R) : ResultOf<R>()
    data class Failure<T>(val error: Throwable) : ResultOf<T>()
    class Loading<T> : ResultOf<T>()
}
