package com.idcamp.dicodingevent.data.retrofit

import com.idcamp.dicodingevent.data.response.DetailEventResponse
import com.idcamp.dicodingevent.data.response.EventResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("events")
    fun getEvents(
        @Query("active") isActive: String
    ): Call<EventResponse>

    @GET("events/{id}")
    fun getDetailEvent(
        @Path("id") id: String
    ): Call<DetailEventResponse>
}
