package com.example.fufu.ui.shop_component.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fufu.data.model.Restaurant
import com.example.fufu.data.repository.RestaurantRepository
import kotlinx.coroutines.launch

class MapsViewModel: ViewModel() {
    private val restaurantRepository = RestaurantRepository()
    val restaurantLiveData = MutableLiveData<Restaurant>()

//    fun getRestaurantByUserId(userId: String) {
//        viewModelScope.launch {
//            val response = restaurantRepository.getRestaurantByUserId(userId)
//            restaurantLiveData.value = response
//        }
//    }
}