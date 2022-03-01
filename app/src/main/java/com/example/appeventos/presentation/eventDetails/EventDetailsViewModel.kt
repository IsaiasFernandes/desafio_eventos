package com.example.appeventos.presentation.eventDetails

import android.util.Log
import androidx.hilt.Assisted
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appeventos.domain.model.EventDetails
import com.example.appeventos.domain.usecases.GetEventDetailsCase
import com.example.appeventos.util.architecture.ResultOf
import com.example.appeventos.util.architecture.resource
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.HttpException

@HiltViewModel
class EventDetailsViewModel @Inject constructor(private val getEventDetailsCase: GetEventDetailsCase) : ViewModel() {

    var eventId : String = ""

    val eventDetails = resource { getEventDetailsCase.execute(eventId) }

    private val _eventDetailsCheck = MutableLiveData<String>()
    val eventDetailsCheck: LiveData<String> = _eventDetailsCheck

    fun getDetailsEvents() {
        Log.d("tag", "entrou na getDetailsEvents")
        eventDetails.loadData()
    }

    fun addEventId(id : String) {
        Log.d("tag", "entrou na addEventId")
        eventId = id
    }

    fun onCkeckEvent(id: String) {
        _eventDetailsCheck.value = id
    }
}