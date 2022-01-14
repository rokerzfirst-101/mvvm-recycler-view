package com.example.recyclerviewexercise.data.model

import com.google.gson.annotations.SerializedName

data class MainActivityResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val results: List<ResultData>,
)

data class ResultData(
    @SerializedName("_id")
    val _id: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("type")
    val type: String
)