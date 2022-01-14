package com.example.recyclerviewexercise.data.model

sealed class QuoteView {
    data class Quote(val quoteData: QuoteData): QuoteView()
    data class Ad(val adData: AdData): QuoteView()
}

fun List<ResultData>.toQuoteViewList(): List<QuoteView> {
    val quoteViewList = mutableListOf<QuoteView>()
    forEach {
        when (it.type) {
            "quote" -> quoteViewList.add(QuoteView.Quote(QuoteData(_id = it._id, author = it.author, content = it.content)))
            "ad" -> quoteViewList.add(QuoteView.Ad(AdData(_id = it._id, content = it.content)))
        }
    }
    return quoteViewList
}