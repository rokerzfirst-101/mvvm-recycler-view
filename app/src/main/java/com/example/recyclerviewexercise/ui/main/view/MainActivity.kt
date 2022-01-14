package com.example.recyclerviewexercise.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexercise.data.model.MainActivityResponse
import com.example.recyclerviewexercise.data.model.toQuoteViewList
import com.example.recyclerviewexercise.ui.main.adapter.QuoteAdapter
import com.example.recyclerviewexercise.databinding.ActivityMainBinding
import com.example.recyclerviewexercise.ui.main.viewmodel.MainViewModel
import com.example.recyclerviewexercise.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: QuoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        recyclerView = binding.recyclerview
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = QuoteAdapter()
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.quotes.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { quotes -> renderList(quotes) }
                }
                Status.LOADING -> {
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(quotes: MainActivityResponse) {
        adapter.setQuoteList(quotes.results.toQuoteViewList())
    }

}