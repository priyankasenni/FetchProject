package com.example.fetchproject.data.remote

import com.example.fetchproject.data.model.Item
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    suspend fun fetchItems(): List<Item>
}
