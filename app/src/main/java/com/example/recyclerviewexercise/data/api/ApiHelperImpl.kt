package com.example.recyclerviewexercise.data.api

import com.example.recyclerviewexercise.data.model.MainActivityResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getQuotes(): Response<MainActivityResponse> = apiService.getQuotes()
}