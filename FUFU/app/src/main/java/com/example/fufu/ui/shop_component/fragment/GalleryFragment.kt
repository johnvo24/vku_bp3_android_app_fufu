package com.example.fufu.ui.shop_component.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import com.example.fufu.R
import com.example.fufu.data.model.Resource
import com.example.fufu.databinding.FragmentGalleryBinding
import com.example.fufu.databinding.FragmentReviewsBinding
import com.example.fufu.ui.adapter.RestaurantResourceItemAdapter
import com.example.fufu.ui.adapter.RestaurantReviewItemAdapter
import com.example.fufu.ui.shop_component.viewmodel.GalleryViewModel
import com.example.fufu.ui.shop_component.viewmodel.ReviewsViewModel
import com.google.gson.Gson
import java.io.File

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    private lateinit var galleryBinding: FragmentGalleryBinding
    private lateinit var resourceItemAdapter: RestaurantResourceItemAdapter

    private lateinit var resourcesLiveData: LiveData<List<Resource>>
    private lateinit var userId: String
    private lateinit var resId: String

    private val PICK_RESOURCE_REQUEST = 1

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //userId
        userId = activity?.intent?.getStringExtra("userId").toString()
        resId = activity?.intent?.getStringExtra("resId").toString()
        //view model
        galleryViewModel = ViewModelProvider(this)[GalleryViewModel::class.java]
        //binding
        galleryBinding = FragmentGalleryBinding.inflate(layoutInflater)
        //livedata
        resourcesLiveData = galleryViewModel.resourcesLiveData
        //adapter
        resourceItemAdapter = RestaurantResourceItemAdapter(emptyList())
        //recyclerview
        galleryBinding.rcvRestaurantReviewList.adapter = resourceItemAdapter
        galleryViewModel.getResourcesByResId(resId)
        resourcesLiveData.observe(viewLifecycleOwner) {
            resourceItemAdapter.setResources(it)
            resourceItemAdapter.notifyDataSetChanged()
        }

        galleryBinding.resourceBtnAdd.setOnClickListener {
            //Activity for upload resource
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/* video/*"
            startActivityForResult(intent, PICK_RESOURCE_REQUEST)
        }

        return galleryBinding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_RESOURCE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val fileUri = data.data as Uri
            galleryViewModel.uploadResource(requireContext(), resId, fileUri)
        }
    }
}