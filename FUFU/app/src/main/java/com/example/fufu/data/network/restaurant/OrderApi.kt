package com.example.fufu.data.network.restaurant

import com.example.fufu.data.model.BillDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderApi {

    @POST("/api/order/add/{userId}/{totalPrice}")
    suspend fun addBill(
        @Path("userId") userId: String,
        @Path("totalPrice") totalPrice: Float,
        @Body bill: List<BillDetail>
    )
}