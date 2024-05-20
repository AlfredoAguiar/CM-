package com.example.trabpratico.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("teste")
    suspend fun getTeste(): String

    @POST("teste") // Update the endpoint accordingly
    suspend fun postTeste(@Body message: String): String
}
