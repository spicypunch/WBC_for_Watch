package com.example.wbc_for_watch.retrofit

import com.example.wbc_for_watch.data.BusArrivalResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BusService {

    @GET("/6410000/busarrivalservice/getBusArrivalList")
    fun getBusArrivalTime(
        @Query("serviceKey") serviceKey: String,
        @Query("stationId") stationId: String
    ): Call<BusArrivalResponse>
}