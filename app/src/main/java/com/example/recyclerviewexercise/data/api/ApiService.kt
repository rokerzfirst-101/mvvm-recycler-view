package com.example.recyclerviewexercise.data.api

import com.example.recyclerviewexercise.data.model.MainActivityResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("quotes")
    suspend fun getQuotes(): Response<MainActivityResponse>

}