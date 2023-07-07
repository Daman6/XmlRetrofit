package com.example.xmlretrofit

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
//        @GET("posts")
//    suspend fun getPosts():List<Post>
    @GET("epg/3b0c7e04-6d56-45ab-ad84-a45896b9c857.xml")
    suspend fun getPosts(): Response<Tv>
}