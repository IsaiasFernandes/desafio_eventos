package com.example.appeventos.present.eventslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appeventos.domain.model.Events
import com.example.appeventos.domain.usecases.GetEventsCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class EventListViewModel @Inject constructor(private val getEventsCase: GetEventsCase) : ViewModel() {

    private val _eventsLiveData: MutableLiveData<List<Events>> = MutableLiveData()
    val eventsLiveData: LiveData<List<Events>>
        get() = _eventsLiveData


    fun getEvents() {
        viewModelScope.launch {
            _eventsLiveData.postValue(getEventsCase.execute())
        }
    }

}