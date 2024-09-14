package com.example.fufu.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fufu.R
import com.example.fufu.data.model.FoodOrders

class FoodOrdersAdapter(val foodListOrders: List<FoodOrders>)
    : RecyclerView.Adapter<FoodOrdersAdapter.FoodOrdersListViewHolder>(){

    class FoodOrdersListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodImg: ImageView = view.findViewById(R.id.imgFoodOrder)
        val foodName: TextView = view.findViewById(R.id.foodNameOrder)
        val foodRes: TextView = view.findViewById(R.id.foodResOrder)
        val foodAmount: TextView = view.findViewById(R.id.foodAmountOrder)
        val foodTotalPrice: TextView = view.findViewById(R.id.foodTotalPriceOrder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodOrdersListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return FoodOrdersListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodOrdersListViewHolder, position: Int) {
        val foodOrders: FoodOrders = foodListOrders[position]
        holder.foodImg.setImageResource(foodOrders.itemImg)
        holder.foodName.text = foodOrders.itemName
        holder.foodRes.text = foodOrders.itemRes
        holder.foodAmount.text = foodOrders.itemAmount.toString()
        holder.foodTotalPrice.text = foodOrders.itemTotalPrice.toString()
    }

    override fun getItemCount() = foodListOrders.size

}