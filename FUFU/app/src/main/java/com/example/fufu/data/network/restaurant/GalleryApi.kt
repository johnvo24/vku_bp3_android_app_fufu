package com.example.fufu.data.network.restaurant

import com.example.fufu.data.model.Resource
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.io.File

interface GalleryApi {
    @GET("/api/restaurant/gallery/{resId}")
    suspend fun getResources(@Path("resId") resId: String): List<Resource>

    @Multipart
    @POST("api/restaurant/gallery/upload/{resId}")
    suspend fun uploadResources(@Path("resId") resId: String, @Part multipartBody: MultipartBody.Part)
}