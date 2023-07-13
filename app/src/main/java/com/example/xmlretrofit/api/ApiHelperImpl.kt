package com.example.xmlretrofit.api

import com.example.xmlretrofit.models.Tv
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
):ApiHelper {
    override suspend fun getTvProgram(): Response<Tv> {
        return apiService.getTvProgram()
    }
}