package com.example.fufu.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fufu.data.model.CartItem
import com.example.fufu.databinding.CartBillItemLayoutBinding
import com.example.fufu.databinding.RestaurantMenuItemLayoutBinding
import com.example.fufu.ui.common.action.ICartAction

class CartItemAdapter(private val cart: List<CartItem>) : RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {
    var iCartAction: ICartAction? = null

    class ViewHolder(binding: CartBillItemLayoutBinding, private val context: Context, private val iCartAction: ICartAction?) : RecyclerView.ViewHolder(binding.root) {
        private val bindingItem = binding
        fun bind(cartItem: CartItem) {
            bindingItem.cartItem = cartItem
            bindingItem.btnDecrease.setOnClickListener {
                if(cartItem.amount > 1) {
                    cartItem.amount = cartItem.amount - 1
                    bindingItem.cartAmount.text = cartItem.amount.toString()
                    iCartAction?.onDecreaseItemAmount(cartItem)
                } else {
                    cartItem.amount = cartItem.amount - 1
                    iCartAction?.onDecreaseItemAmount(cartItem)
                    Toast.makeText(context , "Item has been deleted", Toast.LENGTH_LONG).show()
                }
            }
            bindingItem.btnIncrease.setOnClickListener {
                if(cartItem.amount < 15) {
                    cartItem.amount++
                    bindingItem.cartAmount.text = cartItem.amount.toString()
                    iCartAction?.onIncreaseItemAmount(cartItem)
                } else {
                    Toast.makeText(context, "15", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CartBillItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, parent.context, iCartAction)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cart[position])
    }

    override fun getItemCount(): Int {
        return cart.size
    }
}