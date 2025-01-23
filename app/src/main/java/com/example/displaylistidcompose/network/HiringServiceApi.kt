package com.example.displaylistidcompose.network

import com.example.displaylistidcompose.model.HiringListItem
import retrofit2.http.GET

interface HiringServiceApi {

    @GET("hiring.json")
    suspend fun getHiringList():List<HiringListItem>

}