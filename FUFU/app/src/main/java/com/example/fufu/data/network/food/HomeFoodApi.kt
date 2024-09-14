package com.example.fufu.data.network.food

import com.example.fufu.asset.Helper
import com.example.fufu.data.model.FoodCanYouLike
import com.example.fufu.data.model.HomeFood
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface HomeFoodApi {
    companion object {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl("http://${ Helper().host }/fufuAPI/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val homeFoodApi: HomeFoodApi = retrofit.create(HomeFoodApi::class.java)
        val homeFoodYouLikeApi: HomeFoodApi = retrofit.create(HomeFoodApi::class.java)
    }

    @GET("itemAPI.php")
    fun getDataFood() : Call<List<HomeFood>>

    @GET("searchItemAPI.php")
    fun getDataFoodYouLike() : Call<List<FoodCanYouLike>>
}