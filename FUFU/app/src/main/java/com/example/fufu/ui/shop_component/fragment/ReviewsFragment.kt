package com.example.fufu.ui.shop_component.fragment

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.get
import com.example.fufu.R
import com.example.fufu.data.model.Review
import com.example.fufu.databinding.FragmentReviewsBinding
import com.example.fufu.ui.adapter.RestaurantReviewItemAdapter
import com.example.fufu.ui.shop_component.viewmodel.ReviewsViewModel

class ReviewsFragment : Fragment() {
    private lateinit var reviewsViewModel: ReviewsViewModel
    private lateinit var reviewsBinding: FragmentReviewsBinding
    private lateinit var restaurantReviewItemAdapter: RestaurantReviewItemAdapter

    //Variable
    private lateinit var userId: String
    private lateinit var resId: String
    private var reviewsList: List<Review> = emptyList()
    private var myReview: List<Review> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //userId
        userId = activity?.intent?.getStringExtra("userId").toString()
        resId = activity?.intent?.getStringExtra("resId").toString()
        println("--------------------------------------------$resId")
        //view model
        reviewsViewModel = ViewModelProvider(this)[ReviewsViewModel::class.java]
        //binding
        reviewsBinding = FragmentReviewsBinding.inflate(layoutInflater)
        //adapter
        restaurantReviewItemAdapter = RestaurantReviewItemAdapter(emptyList(), reviewsViewModel)
        //recyclerview
        reviewsViewModel.getReviewList(resId, userId)
        reviewsBinding.btnSendReview.visibility = View.INVISIBLE
        reviewsViewModel.reviewListLiveData.observe(viewLifecycleOwner) {
            reviewsList = it
            if(it.isNotEmpty() && it[0].userId == userId) {
                myReview = listOf(it[0])
            }
            restaurantReviewItemAdapter = RestaurantReviewItemAdapter(reviewsList, reviewsViewModel)
            reviewsBinding.rcvRestaurantReviewList.adapter = restaurantReviewItemAdapter

            if(reviewsList.isEmpty() || reviewsList[0].userId != userId) {
                //my review exist
                reviewsBinding.myReview.visibility = View.VISIBLE
                reviewsBinding.reviewPoint.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                    if(rating.toInt() > 0) {
                        reviewsBinding.btnSendReview.visibility = View.VISIBLE
                        reviewsBinding.btnSendReview.setOnClickListener {
                            reviewsViewModel.insertReview(Review("", resId, userId,
                                "", rating.toInt(), reviewsBinding.reviewComment.text.toString(), "null")
                            )
                            reviewsViewModel.getReviewList(resId, userId)
                            Toast.makeText(requireContext(), "Your review has been inserted!", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        reviewsBinding.btnSendReview.visibility = View.INVISIBLE
                    }
                }
            }else {
                reviewsBinding.myReview.visibility = View.GONE
            }
        }

        return reviewsBinding.root
    }

}