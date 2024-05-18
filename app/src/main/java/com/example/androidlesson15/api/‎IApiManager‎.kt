package com.example.androidlesson15.api

import com.example.androidlesson15.models.Todo
import com.example.androidlesson15.models.TodoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IApiManager {

    @GET("todos")
    fun getTodo(): Call<TodoResponse>

    @GET("todos/{id}")
    fun getTodoById(@Path("id") id: String): Call<Todo>
}