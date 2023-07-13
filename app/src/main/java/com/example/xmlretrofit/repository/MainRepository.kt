package com.example.xmlretrofit.repository

import com.example.xmlretrofit.api.ApiHelper
import com.example.xmlretrofit.models.Tv
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun getTvProgram() = apiHelper.getTvProgram()
}