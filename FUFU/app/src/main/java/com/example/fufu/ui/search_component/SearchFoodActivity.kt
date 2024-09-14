package com.example.fufu.ui.search_component

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fufu.MainActivity
import com.example.fufu.data.model.FoodCanYouLike
import com.example.fufu.data.model.FoodSearchModel
import com.example.fufu.data.model.HomeFood
import com.example.fufu.data.network.food.ClickItemFoodListener
import com.example.fufu.data.network.food.HomeFoodApi
import com.example.fufu.data.network.food.SearchFoodApi
import com.example.fufu.databinding.ActivitySearchFoodBinding
import com.example.fufu.ui.adapter.FoodHomeListAdapter
import com.example.fufu.ui.adapter.FoodSearchAdapter
import com.example.fufu.ui.detail_component.DetailActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class SearchFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchFoodBinding

    private lateinit var foodSearchAdapter: FoodSearchAdapter
    private lateinit var foodSuggestAdapter: FoodHomeListAdapter

    var foodSearch: List<FoodSearchModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rcView.layoutManager = LinearLayoutManager(this)
        binding.foodSuggest.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        callApi()
        callApiFoodSuggest()

        //binding.middleView.visibility = View.GONE
        //binding.bottomView.visibility = View.VISIBLE
        binding.alertTitle.visibility = View.GONE

        binding.btnCancel.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }

        binding.keyWordSearch.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.equals("")) {
                    binding.bottomView.visibility = View.VISIBLE
                    binding.middleView.visibility = View.GONE
                } else {
                    filterFood(newText)
                }
                return true
            }

        })

    }

    private fun filterFood(keyWord: String?){
        if (keyWord != null) {
            binding.middleView.visibility = View.VISIBLE
            binding.bottomView.visibility = View.GONE
            val filterList = ArrayList<FoodSearchModel>()
            for (i in foodSearch!!) {
                if (i.itemName.lowercase(Locale.ROOT).contains(keyWord)) {
                    filterList.add(i)
                }
            }
            if (filterList.isEmpty()) {
                binding.alertTitle.visibility = View.VISIBLE
                binding.middleView.visibility = View.GONE
            } else {
                foodSearchAdapter.setFilteredList(filterList)
                binding.alertTitle.visibility = View.GONE
            }
        }
    }

    // food đề xuất
    private fun callApiFoodSuggest() {
        HomeFoodApi.homeFoodApi.getDataFood().enqueue(object : Callback<List<HomeFood>>{
            override fun onResponse(
                call: Call<List<HomeFood>>,
                response: Response<List<HomeFood>>
            ) {
                val foodList = response.body()
                if (foodList != null) {
                    foodSuggestAdapter = FoodHomeListAdapter(foodList, object : ClickItemFoodListener {
                        override fun onClickItemFood(food: FoodSearchModel) {
                            TODO("Not yet implemented")
                        }

                        override fun onClickItemFoodAround(food: HomeFood) {
                            Toast.makeText(applicationContext, food.itemName, Toast.LENGTH_SHORT).show()
                        }

                        override fun onClickItemFoodCanLike(food: FoodCanYouLike) {
                            TODO("Not yet implemented")
                        }

                    })
                    binding.foodSuggest.adapter = foodSuggestAdapter
                }
            }

            override fun onFailure(call: Call<List<HomeFood>>, t: Throwable) {
                Toast.makeText(applicationContext, "Call Failed", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun callApi() {
        SearchFoodApi.searchFoodApi.getDataSearch().enqueue(object : Callback<List<FoodSearchModel>>{
            override fun onResponse(
                call: Call<List<FoodSearchModel>>,
                response: Response<List<FoodSearchModel>>
            ) {
                foodSearch = response.body()
                if (foodSearch != null) {
                    foodSearchAdapter = FoodSearchAdapter(foodSearch!!, object : ClickItemFoodListener{
                        override fun onClickItemFood(food: FoodSearchModel) {
                            onClickItem(food)
                        }

                        override fun onClickItemFoodAround(food: HomeFood) {
                            TODO("Not yet implemented")
                        }

                        override fun onClickItemFoodCanLike(food: FoodCanYouLike) {
                            TODO("Not yet implemented")
                        }

                    })
                    binding.rcView.adapter = foodSearchAdapter
                }
            }

            override fun onFailure(call: Call<List<FoodSearchModel>>, t: Throwable) {
                Toast.makeText(applicationContext, "Call Failed", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun onClickItem(foodClick: FoodSearchModel) {
        val i = Intent(this, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("food", foodClick)
        i.putExtras(bundle)
        i.putExtra("model", "searchFood")
        startActivity(i)
    }

//    private fun onClickItemHome(foodClick: HomeFood) {
//        val i = Intent(this, DetailActivity::class.java)
//        val bundle = Bundle()
//        bundle.putSerializable("foodSearch", foodClick)
//        i.putExtras(bundle)
//        startActivity(i)
//    }
}