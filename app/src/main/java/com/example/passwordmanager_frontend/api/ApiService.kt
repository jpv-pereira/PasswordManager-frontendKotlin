package com.example.passwordmanager_frontend.api

import com.example.passwordmanager_frontend.model.ClientRegistrationData
import com.example.passwordmanager_frontend.model.TestData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/api/customer/register")
    fun tryRegister(@Body data: ClientRegistrationData): Call<ClientRegistrationData>

    @GET("api/customer")
    fun tryTest(): Call<String>
}