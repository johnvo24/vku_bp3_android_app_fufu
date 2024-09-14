package com.example.fufu.data.network.restaurant

import com.example.fufu.data.model.Restaurant
import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantApi {
    @GET("api/restaurant/{resId}")
    suspend fun getRestaurantByResId(@Path("resId") resId: String): List<Restaurant>
}