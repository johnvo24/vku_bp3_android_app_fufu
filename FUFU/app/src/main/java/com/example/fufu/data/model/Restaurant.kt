package com.example.fufu.data.model

data class Restaurant(
    val resId: String,
    val resName: String,
    val resAvt: String,
    val resAddress: String,
    val resLatitude: Double,
    val resLongitude: Double,
    val resPhone: String,
    val resDescription: String,
    val resOpenTime: String,
    val resSeat: Int,
    val createdAt: String,
    val updatedAt: String,
    val userId: String,
    val revAvg: Float,
    val revAmount: Int
)