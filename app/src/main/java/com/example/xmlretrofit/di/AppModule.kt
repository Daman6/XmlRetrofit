package com.example.xmlretrofit.di

import android.app.Application
import androidx.core.os.BuildCompat
import com.example.xmlretrofit.api.ApiHelper
import com.example.xmlretrofit.api.ApiHelperImpl
import com.example.xmlretrofit.api.ApiService
import com.example.xmlretrofit.repository.MainRepository
import com.example.xmlretrofit.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


//    @Singleton
//    @Provides
//    fun provideOkHttpClient() : OkHttpClient{
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        return OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideRetrofit(okHttpClient: OkHttpClient,BASE_URL:String):Retrofit = Retrofit.Builder()
//        .addConverterFactory(
//            SimpleXmlConverterFactory.createNonStrict(
//                Persister(AnnotationStrategy())
//            )
//        )
//        .baseUrl(BASE_URL)
//        .client(okHttpClient)
//        .build()
//
//
//    @Singleton
//    @Provides
//    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)


    @Provides
    @Singleton
    fun provideRetrofit(): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(
                SimpleXmlConverterFactory.createNonStrict(
                    Persister(AnnotationStrategy())
                )
            )
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .build()
            .create(ApiService::class.java)
    }

        @Provides
        @Singleton
        fun provideApiService(api: ApiService) : ApiHelper{
            return ApiHelperImpl(api)
        }

//    @Provides
//    fun provideApiHelper(): ApiHelper {
//        return ApiHelperImpl()
//    }
    }