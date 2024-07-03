package com.example.myapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceBuilder {
    private const val BASE_URL = "https://api.jsonbin.io/"

    fun buildService(): ConfigurationApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ConfigurationApiService::class.java)
    }
}