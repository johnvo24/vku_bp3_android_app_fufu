package com.example.fufu.ui.common.action

import com.example.fufu.data.model.CartItem

interface ICartAction {
    fun onIncreaseItemAmount(cartItem: CartItem)
    fun onDecreaseItemAmount(cartItem: CartItem)
}