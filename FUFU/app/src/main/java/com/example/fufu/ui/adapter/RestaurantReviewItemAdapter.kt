package com.example.fufu.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fufu.asset.Helper
import com.example.fufu.data.model.Review
import com.example.fufu.databinding.RestaurantReviewItemLayoutBinding
import com.example.fufu.ui.shop_component.viewmodel.ReviewsViewModel

class RestaurantReviewItemAdapter(private var reviewList: List<Review>, private var restaurantReviewsViewModel: ReviewsViewModel): RecyclerView.Adapter<RestaurantReviewItemAdapter.ViewHolder>() {
    fun setReviewList(_reviewList: List<Review>) {
        reviewList = _reviewList
        reviewList.map { it.createdAt = Helper().getDate(it.createdAt) }
    }

    class ViewHolder(binding: RestaurantReviewItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        private val bindingItem = binding
        fun bind(review: Review, restaurantReviewsViewModel: ReviewsViewModel) {
            if(review.userId == Helper().getCurrentUser(bindingItem.root.context)) {
                bindingItem.btnDeleteReview.visibility = View.VISIBLE
                bindingItem.btnDeleteReview.setOnClickListener {
                    restaurantReviewsViewModel.deleteReview(review.reviewId)
                    restaurantReviewsViewModel.getReviewList(review.resId, review.userId)
                    Toast.makeText(bindingItem.root.context, "Your review has been deleted!", Toast.LENGTH_SHORT).show()
                }
            }
            bindingItem.review = review
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RestaurantReviewItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reviewList[position], restaurantReviewsViewModel)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }
}