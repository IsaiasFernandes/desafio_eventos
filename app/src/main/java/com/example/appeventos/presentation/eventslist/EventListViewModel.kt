package com.example.appeventos.presentation.eventslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appeventos.domain.model.Events
import com.example.appeventos.domain.usecases.GetEventsCase
import com.example.appeventos.util.architecture.ResultOf
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.HttpException

@HiltViewModel
class EventListViewModel @Inject constructor(private val getEventsCase: GetEventsCase) : ViewModel() {

    private val _eventsLiveData: MutableLiveData<ResultOf<List<Events>>> = MutableLiveData()
    val eventsLiveData: LiveData<ResultOf<List<Events>>>
        get() = _eventsLiveData

    private val _eventoSelectIdLiveData = MutableLiveData<String>()
    var eventoSelectIdLiveData: LiveData<String> = _eventoSelectIdLiveData

    fun getEvents() {
        viewModelScope.launch {
            try {
                val respnse = getEventsCase.execute()
                _eventsLiveData.postValue(ResultOf.Success(respnse))
            } catch ( io  : IOException) {

                Log.d("tag", "Teste catch IO")
                _eventsLiveData.postValue(ResultOf.Failure(io.message, io))
            } catch ( http : HttpException) {

                Log.d("tag", "Teste catch Http")
                _eventsLiveData.postValue(ResultOf.Failure(http.message, http))
            }
        }
    }

    fun onSelectEvent(id: String) {
        _eventoSelectIdLiveData.value = id
    }

}