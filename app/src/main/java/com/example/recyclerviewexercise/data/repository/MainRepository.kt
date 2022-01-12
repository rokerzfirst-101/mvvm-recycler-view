package com.example.recyclerviewexercise.data.repository

import com.example.recyclerviewexercise.data.api.RetrofitService
import com.example.recyclerviewexercise.data.model.QuoteList
import retrofit2.Response

class MainRepository(private val retrofitService: RetrofitService) {

    fun getQuotes() = retrofitService.getQuotes()

}