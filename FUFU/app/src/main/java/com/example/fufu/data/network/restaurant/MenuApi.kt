package com.example.fufu.data.network.restaurant

import com.example.fufu.data.model.Item
import retrofit2.http.GET
import retrofit2.http.Path

interface MenuApi {
    @GET("/api/restaurant/menu/item/{resId}")
    suspend fun getItemsByResId(@Path("resId") resId: String): List<Item>
}