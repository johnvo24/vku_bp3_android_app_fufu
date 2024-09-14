package com.example.fufu.asset

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.example.fufu.data.model.CartItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*

class Helper {
    private lateinit var sharePref: SharedPreferences
    private val gson = Gson()
    val host: String = "192.168.196.94:80"

    fun checkUserRole(context: Context): Boolean {
        sharePref = context.getSharedPreferences("currentUser", AppCompatActivity.MODE_PRIVATE)
        return (sharePref.getString("userRole", null) == "1")
    }
    fun getCurrentUser(context: Context): String? {
        sharePref = context.getSharedPreferences("currentUser", AppCompatActivity.MODE_PRIVATE)
        return sharePref.getString("userId", null)
    }
    fun getCurrentResId(context: Context): String? {
        sharePref = context.getSharedPreferences("currentUser", AppCompatActivity.MODE_PRIVATE)
        return sharePref.getString("resId", null)
    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(timeString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = inputFormat.parse(timeString)

        val timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh")
        val outputFormat = SimpleDateFormat("dd/MM/yyyy")
        outputFormat.timeZone = timeZone
        return outputFormat.format(date)
    }

    fun getPathFromUri(context: Context, uri: Uri): String? {
        var path: String? = null
        val projection = arrayOf(MediaStore.Files.FileColumns.DATA)
        val cursor = context.contentResolver.query(uri, projection, null, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)
                path = cursor.getString(columnIndex)
            }
            cursor.close()
        }
        return path
    }

    //Cart
    fun saveCart(context: Context, cart: List<CartItem>) {
        sharePref = context.getSharedPreferences("myCart", AppCompatActivity.MODE_PRIVATE)
        sharePref.edit().putString("cart", gson.toJson(cart).toString()).apply()
    }
    fun getCart(context: Context): List<CartItem>? {
        sharePref = context.getSharedPreferences("myCart", AppCompatActivity.MODE_PRIVATE)
        val type = object : TypeToken<List<CartItem>>() {}.type
        return gson.fromJson(sharePref.getString("cart", null), type)
    }
    fun clearCart(context: Context) {
        sharePref = context.getSharedPreferences("myCart", AppCompatActivity.MODE_PRIVATE)
        sharePref.edit().clear().apply()
    }
}