package com.example.androidlesson15.models


import com.google.gson.annotations.SerializedName

data class TodoResponse(
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("skip")
    val skip: Int?,
    @SerializedName("todos")
    val todos: List<Todo>?,
    @SerializedName("total")
    val total: Int?
)