package com.example.recyclerviewexercise.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recyclerviewexercise.data.model.QuoteList
import com.example.recyclerviewexercise.data.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {

    private val TAG = "MainViewModel"
    val quoteList = MutableLiveData<QuoteList>()
    val errorMessage = MutableLiveData<String>()

    fun loadQuoteData() {
        Log.d(TAG, "loadQuoteData: Called")
        val response = mainRepository.getQuotes()
        response.enqueue(object: Callback<QuoteList> {
            override fun onResponse(call: Call<QuoteList>, response: Response<QuoteList>) {
                Log.d(TAG, "loadQuoteData: ${response.body()}")
                quoteList.postValue(response.body())
            }

            override fun onFailure(call: Call<QuoteList>, t: Throwable) {
                Log.d(TAG, "loadQuoteData: ${t.message}")
                errorMessage.postValue(t.message)
            }
        })
    }

}