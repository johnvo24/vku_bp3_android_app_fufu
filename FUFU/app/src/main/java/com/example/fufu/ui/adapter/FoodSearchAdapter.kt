package com.example.fufu.ui.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fufu.R
import com.example.fufu.data.model.FoodSearchModel
import com.example.fufu.data.network.food.ClickItemFoodListener
import com.example.fufu.ui.detail_component.DetailActivity
import com.google.android.material.card.MaterialCardView

class FoodSearchAdapter(var foodSearchList: List<FoodSearchModel>, var itemListener: ClickItemFoodListener)
    : RecyclerView.Adapter<FoodSearchAdapter.FoodViewHolder>(){

    class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodId: TextView = view.findViewById(R.id.item_id)
        val foodImg: ImageView = view.findViewById(R.id.img_food)
        val foodName: TextView = view.findViewById(R.id.item_name)
        val foodResId: TextView = view.findViewById(R.id.res_id)
        val foodRes: TextView = view.findViewById(R.id.item_res)
        val foodDes: TextView = view.findViewById(R.id.item_des)
        val foodPrice: TextView = view.findViewById(R.id.item_price)
        val itemClick: MaterialCardView = view.findViewById(R.id.layoutClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foodSearch: FoodSearchModel = foodSearchList[position]
        holder.foodId.text = foodSearch.itemId.toString()
        Glide.with(holder.foodName.context).load(foodSearch.itemImg).into(holder.foodImg)
        holder.foodName.text = foodSearch.itemName
        holder.foodResId.text = foodSearch.resId.toString()
        holder.foodRes.text = foodSearch.resName
        holder.foodDes.text = foodSearch.itemDes
        holder.foodPrice.text = foodSearch.itemPrice.toString()

        holder.itemClick.setOnClickListener {
            itemListener.onClickItemFood(foodSearch)
        }
    }

    override fun getItemCount() = foodSearchList.size

    fun setFilteredList(foodList: List<FoodSearchModel>){
        this.foodSearchList = foodList
        notifyDataSetChanged()
    }

}