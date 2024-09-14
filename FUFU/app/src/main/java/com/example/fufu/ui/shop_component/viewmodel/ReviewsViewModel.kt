package com.example.fufu.ui.shop_component.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fufu.data.model.Review
import com.example.fufu.data.repository.ReviewRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ReviewsViewModel : ViewModel() {
    //repository
    private val reviewRepository = ReviewRepository()
    //live data
    val reviewListLiveData = MutableLiveData<List<Review>>()

    fun getReviewList(resId: String, userId: String) {
        viewModelScope.launch {
            val reviewsDeferred = async { reviewRepository.getReviewsByResId(resId, userId) }
            val myReviewDeferred = async { reviewRepository.getReviewByUserId(resId, userId) }

            val reviews = reviewsDeferred.await()
            val myReview = myReviewDeferred.await()

            val reviewsList: List<Review> = myReview + reviews

            reviewListLiveData.value = reviewsList
        }
    }

    fun deleteReview(reviewId: String) {
        viewModelScope.launch {
            reviewRepository.deleteReview(reviewId)
        }
    }

    fun insertReview(review: Review) {
         viewModelScope.launch {
             reviewRepository.insertReview(review)
         }
    }
}