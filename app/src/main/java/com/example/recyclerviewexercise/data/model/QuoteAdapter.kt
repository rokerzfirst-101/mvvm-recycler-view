package com.example.recyclerviewexercise.data.model

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexercise.databinding.AdapterQuoteBinding

class QuoteAdapter: RecyclerView.Adapter<QuoteViewHolder>() {

    private val TAG = "QuoteAdapter"
    
    private var quotes = listOf<Quote>()

    fun setQuoteList(quotes: List<Quote>) {
        Log.d(TAG, "setQuoteList: QuoteListSet")
        this.quotes = quotes
        notifyDataSetChanged()
        Log.d(TAG, "setQuoteList: ${this.quotes}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterQuoteBinding.inflate(inflater, parent, false)
        return QuoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = quotes[position]
        holder.binding.quote.text = quote.content
        holder.binding.author.text = quote.author
    }

    override fun getItemCount(): Int {
        return quotes.size
    }
}

class QuoteViewHolder(val binding: AdapterQuoteBinding) : RecyclerView.ViewHolder(binding.root)