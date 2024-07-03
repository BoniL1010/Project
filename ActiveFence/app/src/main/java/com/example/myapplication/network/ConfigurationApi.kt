package com.example.myapplication.network

import com.example.myapplication.network.response.ConfigResponse
import retrofit2.Call
import retrofit2.Response

class ConfigurationApi {
    fun fetchConfig(onResult: (ConfigResponse?) -> Unit) {
        val retrofit = ApiServiceBuilder.buildService()
        retrofit.getDataFromConfig().enqueue(
            object : retrofit2.Callback<ConfigResponse> {
                override fun onResponse(call: Call<ConfigResponse>, response: Response<ConfigResponse>) {
                    val getConfigResponse = response.body()
                    onResult(getConfigResponse)
                }
                override fun onFailure(call: Call<ConfigResponse>, t: Throwable){
                    onResult(null)
                }

            }
        )
    }
}