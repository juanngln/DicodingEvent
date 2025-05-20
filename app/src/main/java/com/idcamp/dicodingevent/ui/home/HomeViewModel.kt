package com.idcamp.dicodingevent.ui.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idcamp.dicodingevent.data.response.EventResponse
import com.idcamp.dicodingevent.data.response.ListEventsItem
import com.idcamp.dicodingevent.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _listUpcomingEvent = MutableLiveData<List<ListEventsItem>>()
    val listUpcomingEvent: LiveData<List<ListEventsItem>> = _listUpcomingEvent

    private val _listFinishedEvent = MutableLiveData<List<ListEventsItem>>()
    val listFinishedEvent: LiveData<List<ListEventsItem>> = _listFinishedEvent

    private val _isLoadingUpcoming = MutableLiveData<Boolean>()
    val isLoadingUpcoming: LiveData<Boolean> = _isLoadingUpcoming

    private val _isLoadingFinished = MutableLiveData<Boolean>()
    val isLoadingFinished: LiveData<Boolean> = _isLoadingFinished

    companion object{
        private const val TAG = "HomeViewModel"
    }

    fun loadEvents(isActive: String) {
        if (isActive == "1") {
            _isLoadingUpcoming.value = true
        } else {
            _isLoadingFinished.value = true
        }
        val client = ApiConfig.getApiService().getEvents(isActive = isActive)
        client.enqueue(object : Callback<EventResponse> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<EventResponse>,
                response: Response<EventResponse>
            ) {
                if (isActive == "1") {
                    _isLoadingUpcoming.value = false
                } else {
                    _isLoadingFinished.value = false
                }
                if (response.isSuccessful) {
                    val events = response.body()?.listEvents ?: emptyList()
                    if (isActive == "1") {
                        _listUpcomingEvent.value = events
                    } else {
                        _listFinishedEvent.value = events
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                if (isActive == "1") {
                    _isLoadingUpcoming.value = false
                } else {
                    _isLoadingFinished.value = false
                }
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}
