package com.example.fufu.ui.shop_component.viewmodel

import android.content.Context
import android.net.Uri
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fufu.asset.Helper
import com.example.fufu.data.model.Resource
import com.example.fufu.data.repository.GalleryRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class GalleryViewModel : ViewModel() {
    //repository
    private val galleryRepository = GalleryRepository()
    //live data
    val resourcesLiveData = MutableLiveData<List<Resource>>()

    fun getResourcesByResId(resId: String) {
        viewModelScope.launch {
            val resourcesDeferred = async { galleryRepository.getResourcesByResId(resId) }
            val resources: List<Resource> = resourcesDeferred.await()
            resourcesLiveData.value = resources
        }
    }

    fun uploadResource(context: Context, resId: String, fileUri: Uri) {
        val file = File(Helper().getPathFromUri(context, fileUri))
        //create RequestBody object
        val requestBody = RequestBody.create(MediaType.parse("image/* video/*"), file)
        //create body for multipart
        val multipartBody = MultipartBody.Part.createFormData("gallery", file.name, requestBody)
        viewModelScope.launch {
            galleryRepository.uploadResource(resId, multipartBody)
        }
    }
}