package com.example.fetchproject.data.repository

import com.example.fetchproject.data.remote.ApiService
import com.example.fetchproject.data.model.Item
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ItemRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    suspend fun getItems(): List<Item> {
        return apiService.fetchItems()
    }
}
