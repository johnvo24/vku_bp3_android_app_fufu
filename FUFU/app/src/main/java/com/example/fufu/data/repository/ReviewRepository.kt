package com.example.fufu.data.repository

import com.example.fufu.data.model.Item
import com.example.fufu.data.model.Review
import com.example.fufu.data.network.RetrofitHelper
import com.example.fufu.data.network.restaurant.MenuApi
import com.example.fufu.data.network.restaurant.ReviewApi

class ReviewRepository {
    private val reviewApi: ReviewApi = RetrofitHelper.getInstance().create(ReviewApi::class.java)

    suspend fun getReviewByUserId(resId:String, userId: String): List<Review> {
        return reviewApi.getReviewByUserId(resId, userId)
    }

    suspend fun getReviewsByResId(resId: String, userId: String): List<Review> {
        return reviewApi.getReviewsByResId(resId, userId)
    }

    suspend fun deleteReview(reviewId: String) {
        reviewApi.deleteReview(reviewId)
    }

    suspend fun insertReview(review: Review) {
        reviewApi.insertReview(review)
    }
}