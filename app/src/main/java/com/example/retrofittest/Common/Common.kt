package com.example.retrofittest.Common
import com.example.retrofittest.Interface.RetrofitServieces
import com.example.retrofittest.Retrofit.RetrofitClient

object Common {
    private val BASE_URL = "https://www.simplifiedcoding.net/demos/"
    val retrofitService: RetrofitServieces
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServieces::class.java)
}