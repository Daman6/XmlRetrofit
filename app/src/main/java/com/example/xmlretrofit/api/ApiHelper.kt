package com.example.xmlretrofit.api

import com.example.xmlretrofit.models.Tv
import retrofit2.Response

interface ApiHelper {
    suspend fun getTvProgram():Response<Tv>
}