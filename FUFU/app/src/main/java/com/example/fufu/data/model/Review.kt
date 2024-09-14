package com.example.fufu.data.model

data class Review (
    val reviewId: String,
    val resId: String,
    val userId: String,
    val userFullName: String,
    val revPoint: Int,
    val revComment: String,
    var createdAt: String
)