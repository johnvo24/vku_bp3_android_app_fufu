package com.example.fufu.ui.detail_component

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.fufu.R
import com.example.fufu.asset.Helper
import com.example.fufu.data.model.FoodCanYouLike
import com.example.fufu.data.model.FoodSearchModel
import com.example.fufu.data.model.HomeFood
import com.example.fufu.databinding.ActivityDetailBinding
import com.example.fufu.ui.shop_component.RestaurantActivity

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle = intent.extras!!
        var foodHome: HomeFood? = null
        var foodSearch: FoodSearchModel? = null
        var foodLike: FoodCanYouLike? = null
        when (intent.getStringExtra("model")) {
            "searchFood" -> {
                foodSearch = bundle.get("food") as FoodSearchModel
                if (foodSearch != null) {
                    binding.itemNameDetail.text = foodSearch.itemName
                    binding.itemResDetail.text = foodSearch.resName
                    binding.itemDesDetail.text = foodSearch.itemDes
                    binding.itemPriceDetail.text = foodSearch.itemPrice.toString()
                    Glide.with(this)
                        .load(foodSearch.itemImg).into(binding.itemImgDetail)
                } else {
                    Toast.makeText(this, "null", Toast.LENGTH_SHORT).show()
                }
            }
            "homeFood" -> {
                foodHome = bundle.get("food") as HomeFood
                if (foodHome != null) {
                    binding.itemNameDetail.text = foodHome.itemName
                    binding.itemResDetail.text = foodHome.resName
                    binding.itemDesDetail.text = foodHome.itemDes
                    binding.itemPriceDetail.text = foodHome.itemPrice.toString()
                    Glide.with(this)
                        .load(foodHome.itemImg).into(binding.itemImgDetail)
                } else {
                    Toast.makeText(this, "null", Toast.LENGTH_SHORT).show()
                }
            }
            "likeFood" -> {
                foodLike = bundle.get("food") as FoodCanYouLike
                if (foodLike != null) {
                    binding.itemNameDetail.text = foodLike.itemName
                    binding.itemResDetail.text = foodLike.resName
                    binding.itemDesDetail.text = foodLike.itemDes
                    binding.itemPriceDetail.text = foodLike.itemPrice.toString()
                    Glide.with(this)
                        .load(foodLike.itemImg).into(binding.itemImgDetail)
                } else {
                    Toast.makeText(this, "null", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnOrder.setOnClickListener {
            when (intent.getStringExtra("model")) {
                "searchFood" -> {
                    val i = Intent(this, RestaurantActivity::class.java)
                    i.putExtra("userId", Helper().getCurrentUser(applicationContext))
                    i.putExtra("resId", foodSearch!!.resId.toString())
                    startActivity(i)
                    finish()
                }
                "homeFood" -> {
                    val i = Intent(this, RestaurantActivity::class.java)
                    i.putExtra("userId", Helper().getCurrentUser(applicationContext))
                    i.putExtra("resId", foodHome!!.resId.toString())
                    startActivity(i)
                    finish()
                }
                "likeFood" -> {
                    val i = Intent(this, RestaurantActivity::class.java)
                    i.putExtra("userId", Helper().getCurrentUser(applicationContext))
                    i.putExtra("resId", foodLike!!.resId.toString())
                    startActivity(i)
                    finish()
                }
            }
        }
    }
}