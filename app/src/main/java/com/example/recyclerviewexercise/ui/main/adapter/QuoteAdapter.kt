package com.example.recyclerviewexercise.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexercise.data.model.QuoteView
import com.example.recyclerviewexercise.databinding.AdapterAdBinding
import com.example.recyclerviewexercise.databinding.AdapterQuoteBinding

class QuoteAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val QUOTE = 1
        const val AD = 2
        const val SPACE = 3
        const val TAG = "QuoteAdapter"
    }

    private var quotes = listOf<QuoteView>()

    class QuoteViewHolder(private val binding: AdapterQuoteBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: QuoteView.Quote) {
//            binding.removeBtn.setOnClickListener {
//                deleteListener(item)
//            }
            binding.author.text = item.quoteData.author
            binding.quote.text = item.quoteData.content
        }
    }

    class AdViewHolder(private val binding: AdapterAdBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: QuoteView) {
            val imageView = binding.imageHolder
        }
    }

    private fun deleteListener(quote: QuoteView) {
        val tempList = quotes as MutableList<QuoteView>
        val position = tempList.indexOf(quote)
        tempList.remove(quote)
        quotes = tempList
        notifyItemRemoved(position)
    }

    fun setQuoteList(quotes: List<QuoteView>) {
        Log.d(TAG, "setQuoteList: QuoteListSet")
        this.quotes = quotes
        notifyDataSetChanged()
        Log.d(TAG, "setQuoteList: ${this.quotes}")
    }

    override fun getItemViewType(position: Int): Int {
        return when (quotes.getOrNull(position)) {
            is QuoteView.Quote -> QUOTE
            is QuoteView.Ad -> AD
            else -> SPACE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            QUOTE -> {
                val binding = AdapterQuoteBinding.inflate(inflater, parent, false)
                QuoteViewHolder(binding)
            }
            AD -> {
                val binding = AdapterAdBinding.inflate(inflater, parent, false)
                AdViewHolder(binding)
            }
            else -> {
                val binding = AdapterAdBinding.inflate(inflater, parent, false)
                AdViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is QuoteViewHolder -> holder.bind(quotes[position] as QuoteView.Quote)
            is AdViewHolder -> holder.bind(quotes[position] as QuoteView.Ad)
        }
    }

    override fun getItemCount(): Int = quotes.size
}