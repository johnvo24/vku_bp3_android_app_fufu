package com.example.fufu.data.model

data class FoodSearchModel(
    val itemId: Int,
    val itemImg: String,
    val itemName: String,
    val resId: Int,
    val resName: String,
    val itemDes: String,
    val itemPrice: Int) : java.io.Serializable {

}