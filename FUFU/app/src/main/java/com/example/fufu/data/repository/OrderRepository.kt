package com.example.fufu.data.repository

import com.example.fufu.data.model.BillDetail
import com.example.fufu.data.network.RetrofitHelper
import com.example.fufu.data.network.restaurant.OrderApi

class OrderRepository {
    private val orderApi: OrderApi = RetrofitHelper.getInstance().create(OrderApi::class.java)

    suspend fun addBill(userId: String, totalPrice: Double, bill: List<BillDetail>) {
        orderApi.addBill(userId, totalPrice.toFloat(), bill)
    }
}