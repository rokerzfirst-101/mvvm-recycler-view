package com.example.recyclerviewexercise.data.repository

import com.example.recyclerviewexercise.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getQuotes() = apiHelper.getQuotes()
}