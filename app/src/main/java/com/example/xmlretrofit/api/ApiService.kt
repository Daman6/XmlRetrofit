package com.example.xmlretrofit.api

import com.example.xmlretrofit.models.Tv
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("epg/3b0c7e04-6d56-45ab-ad84-a45896b9c857.xml")
    suspend fun getTvProgram(): Response<Tv>
}