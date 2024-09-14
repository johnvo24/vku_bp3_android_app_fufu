package com.example.fufu.ui.common.action

import com.example.fufu.data.model.CartItem

interface IRestaurantAction {
    fun onAddToCart(cartItem: CartItem)
    fun onRemoveFromCart(cartItem: CartItem)
}