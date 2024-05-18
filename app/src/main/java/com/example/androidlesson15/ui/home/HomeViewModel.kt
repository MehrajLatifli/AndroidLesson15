package com.example.androidlesson15.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidlesson15.api.ApiUtils
import com.example.androidlesson15.models.Todo
import com.example.androidlesson15.models.TodoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val api = ApiUtils.getApi()

    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos

    val isLoading = MutableLiveData<Boolean>()

    init {
        getTodo()
    }

    private fun getTodo() {
        isLoading.value = true

        api.getTodo().enqueue(object : Callback<TodoResponse> {
            override fun onResponse(call: Call<TodoResponse>, response: Response<TodoResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { model ->
                        model.todos?.let {
                            isLoading.value = false
                            _todos.postValue(it) // Update the value asynchronously
                        }
                    }
                }
            }

            override fun onFailure(call: Call<TodoResponse>, t: Throwable) {
                Log.e("Throwable", t.message.toString())
            }
        })
    }
}
