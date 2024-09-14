package com.example.fufu.ui.shop_component.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fufu.asset.Helper
import com.example.fufu.data.model.CartItem
import com.example.fufu.data.model.Item
import com.example.fufu.data.model.Restaurant
import com.example.fufu.data.repository.MenuRepository
import com.example.fufu.data.repository.RestaurantRepository
import kotlinx.coroutines.launch

class RestaurantDetailViewModel : ViewModel() {
    //repository
    private val menuRepository = MenuRepository()
    private val restaurantRepository = RestaurantRepository()
    //live data
    val menuListLiveData = MutableLiveData<List<Item>>()
    val restaurantLiveData = MutableLiveData<Restaurant>()
    val cartLiveData = MutableLiveData<List<CartItem>>()

    fun getMenuList(resId: String) {
        viewModelScope.launch {
            val response = menuRepository.getMenuList(resId)
            menuListLiveData.value = response
        }
    }
    fun getRestaurantByResId(resId: String) {
        viewModelScope.launch {
            val response = restaurantRepository.getRestaurantByResId(resId)
            restaurantLiveData.value = response
        }
    }
    fun setMenuList(menuList: List<Item>) {
        menuListLiveData.value = menuList
    }
    fun getCart(context: Context) {
        cartLiveData.value = Helper().getCart(context)
    }
    fun addCart(cartItem: CartItem) {
        cartLiveData.value = if(cartLiveData.value != null) cartLiveData.value!!.plus(cartItem) else listOf(cartItem)
    }
    fun removeCart(cartItem: CartItem) {
        cartLiveData.value = cartLiveData.value?.filter { it.item.itemId != cartItem.item.itemId }
    }
    fun saveCart(context: Context, cart: List<CartItem>) {
        Helper().saveCart(context, cart)
    }
}