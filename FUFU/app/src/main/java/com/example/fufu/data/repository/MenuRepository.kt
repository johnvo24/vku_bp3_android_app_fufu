package com.example.fufu.data.repository

import com.example.fufu.data.model.Item
import com.example.fufu.data.network.RetrofitHelper
import com.example.fufu.data.network.restaurant.MenuApi

class MenuRepository {
    private val menuApi: MenuApi = RetrofitHelper.getInstance().create(MenuApi::class.java)

    suspend fun getMenuList(resId: String): List<Item> {
        return menuApi.getItemsByResId(resId)
    }
}