package com.example.fufu.data.repository

import com.example.fufu.data.model.Resource
import com.example.fufu.data.network.RetrofitHelper
import com.example.fufu.data.network.restaurant.GalleryApi
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import java.io.File

class GalleryRepository {
    private val galleryApi:GalleryApi  = RetrofitHelper.getInstance().create(GalleryApi::class.java)

    suspend fun getResourcesByResId(resId: String):List<Resource> {
        return galleryApi.getResources(resId)
    }
    suspend fun uploadResource(resId: String, multipartBody: MultipartBody.Part) {
        galleryApi.uploadResources(resId, multipartBody)
    }
}