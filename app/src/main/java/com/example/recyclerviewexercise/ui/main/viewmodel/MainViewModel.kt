package com.example.recyclerviewexercise.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewexercise.data.model.MainActivityResponse
import com.example.recyclerviewexercise.data.repository.MainRepository
import com.example.recyclerviewexercise.utils.NetworkHelper
import com.example.recyclerviewexercise.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _quotes = MutableLiveData<Resource<MainActivityResponse>>()
    val quotes: LiveData<Resource<MainActivityResponse>>
        get() = _quotes

    init {
        fetchQuotes()
    }

    private fun fetchQuotes() {
        viewModelScope.launch {
            _quotes.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getQuotes().let {
                    if (it.isSuccessful) {
                        _quotes.postValue(Resource.success(it.body()))
                    } else _quotes.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _quotes.postValue(Resource.error("No internet connection", null))
        }
    }

}