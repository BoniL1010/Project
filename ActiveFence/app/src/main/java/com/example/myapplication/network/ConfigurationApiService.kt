package com.example.myapplication.network

import com.example.myapplication.network.response.ConfigResponse
import retrofit2.Call
import retrofit2.http.GET

interface ConfigurationApiService {
    @GET("v3/b/6458fdf39d312622a35a127f")
    fun getDataFromConfig(): Call<ConfigResponse>
}
