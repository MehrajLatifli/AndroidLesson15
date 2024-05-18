package com.example.androidlesson15.api

class ApiUtils {

    companion object{

        fun getApi(): IApiManager {

            return RetrofitClient.retrofit.create(IApiManager::class.java)

        }
    }
}