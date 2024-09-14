package com.example.fufu.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fufu.R
import com.example.fufu.data.model.HomeFood
import com.example.fufu.data.network.food.ClickItemFoodListener
import com.google.android.material.card.MaterialCardView

class FoodHomeListAdapter(var foodHomeList: List<HomeFood>, var itemListener: ClickItemFoodListener)
    : RecyclerView.Adapter<FoodHomeListAdapter.FoodHomeListViewHolder>(){

    class FoodHomeListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodId: TextView = view.findViewById(R.id.item_id_around_you)
        val resId: TextView = view.findViewById(R.id.res_id_around_you)
        val foodImg: ImageView = view.findViewById(R.id.img_food_around_you)
        val foodName: TextView = view.findViewById(R.id.item_name_around_you)
        val foodDes: TextView = view.findViewById(R.id.item_des_around_you)
        val foodPrice: TextView = view.findViewById(R.id.item_price_around_you)
        val resName: TextView = view.findViewById(R.id.res_name_around_you)
        val itemClick: MaterialCardView = view.findViewById(R.id.layoutClick2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHomeListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_around_you_item_layout, parent, false)
        return FoodHomeListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodHomeListViewHolder, position: Int) {
        val homeFood: HomeFood = foodHomeList[position]
        holder.foodId.text = homeFood.itemId.toString()
        holder.resId.text = homeFood.resId.toString()
        Glide.with(holder.foodName.context).load(homeFood.itemImg).into(holder.foodImg)
        holder.foodName.text = homeFood.itemName
        holder.foodDes.text = homeFood.itemDes
        holder.foodPrice.text = homeFood.itemPrice.toString()
        holder.resName.text = homeFood.resName
        holder.itemClick.setOnClickListener {
            itemListener.onClickItemFoodAround(homeFood)
        }
    }

    override fun getItemCount() = foodHomeList.size

}