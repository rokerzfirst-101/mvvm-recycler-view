package com.example.recyclerviewexercise.data.api

import com.example.recyclerviewexercise.data.model.MainActivityResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getQuotes(): Response<MainActivityResponse>
}