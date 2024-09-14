package com.example.fufu.ui.cart_component

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fufu.asset.Helper
import com.example.fufu.data.model.BillDetail
import com.example.fufu.data.model.CartItem
import com.example.fufu.data.repository.OrderRepository
import kotlinx.coroutines.launch

class CartViewModel: ViewModel() {
    val cartLiveData = MutableLiveData<List<CartItem>>()

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
    fun saveCartDefault(context: Context) {
        cartLiveData.value?.let { Helper().saveCart(context, it) }
    }
    fun clearCart(context: Context ) {
        Helper().clearCart(context)
    }

    fun addBill(context: Context, totalPrice: Double, bill: List<BillDetail>) {
        viewModelScope.launch {
            OrderRepository().addBill(Helper().getCurrentUser(context)!!, totalPrice, bill)
        }
    }
}