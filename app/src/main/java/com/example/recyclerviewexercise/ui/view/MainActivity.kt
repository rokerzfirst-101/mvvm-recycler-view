package com.example.recyclerviewexercise.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewexercise.R
import com.example.recyclerviewexercise.data.api.RetrofitService
import com.example.recyclerviewexercise.data.model.QuoteAdapter
import com.example.recyclerviewexercise.data.repository.MainRepository
import com.example.recyclerviewexercise.databinding.ActivityMainBinding
import com.example.recyclerviewexercise.ui.main.ViewModelFactory
import com.example.recyclerviewexercise.ui.main.viewmodel.MainViewModel
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()
    private val adapter = QuoteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
                this,
                ViewModelFactory(MainRepository(retrofitService))
            ).get(MainViewModel::class.java)

        binding.recyclerview.adapter = adapter
        viewModel.quoteList.observe(this, {
            Log.d(TAG, "onCreate: $it")
            adapter.setQuoteList(it.results)
        })
        viewModel.loadQuoteData()
    }
}