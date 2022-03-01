package com.example.appeventos.presentation.eventslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appeventos.domain.model.Events
import com.example.appeventos.domain.usecases.GetEventsCase
import com.example.appeventos.util.architecture.ResultOf
import com.example.appeventos.util.architecture.resource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.HttpException

@HiltViewModel
class EventListViewModel @Inject constructor(private val getEventsCase: GetEventsCase) : ViewModel() {

    val eventsLiveData = resource { getEventsCase.execute() }

    private val _eventoSelectIdLiveData = MutableLiveData<String>()
    var eventoSelectIdLiveData: LiveData<String> = _eventoSelectIdLiveData

    fun getEvents() {
        eventsLiveData.loadData()
    }

    fun onSelectEvent(id: String) {
        _eventoSelectIdLiveData.value = id
    }

}