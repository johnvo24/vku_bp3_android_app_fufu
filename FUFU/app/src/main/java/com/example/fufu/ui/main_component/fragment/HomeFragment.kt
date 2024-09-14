package com.example.fufu.ui.main_component.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fufu.R
import com.example.fufu.data.model.FoodCanYouLike
import com.example.fufu.data.model.HomeFood
import com.example.fufu.data.network.food.HomeFoodApi
import com.example.fufu.ui.adapter.FoodHomeListAdapter
import com.example.fufu.ui.adapter.FoodHomeYouLikeAdapter
import com.example.fufu.ui.main_component.viewmodel.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.ImageButton
import com.example.fufu.asset.Helper
import com.example.fufu.data.model.FoodSearchModel
import com.example.fufu.data.network.food.ClickItemFoodListener
import com.example.fufu.databinding.FragmentHomeBinding
import com.example.fufu.ui.detail_component.DetailActivity
import com.example.fufu.ui.shop_component.RestaurantActivity

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeBinding: FragmentHomeBinding
    //btn
    private lateinit var btnDeals: ImageButton
    private lateinit var btnShop: ImageButton
    private lateinit var btnFavorite: ImageButton
    private lateinit var btnTravel: ImageButton

    companion object {
        fun newInstance() = HomeFragment()
    }


    private lateinit var rcView: RecyclerView
    private lateinit var rcViewYouLike: RecyclerView

    private lateinit var foodAdapter: FoodHomeListAdapter
    private lateinit var foodYouLikeAdapter: FoodHomeYouLikeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //view model
        //binding
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)
        //live data
        //adapter
        //button
        btnDeals = homeBinding.btnDeals
        btnShop = homeBinding.btnShop
        btnFavorite = homeBinding.btnFavorite
        btnTravel = homeBinding.btnTravel
        //navigation

        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcView = view.findViewById(R.id.recycler_view_food_around_you_list)
        rcViewYouLike = view.findViewById(R.id.recycler_view_maybe_you_like_list)

        rcView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        rcViewYouLike.layoutManager = GridLayoutManager(context, 3)

        callApiFoodAround()
        callApiYouLike()

        btnShop.setOnClickListener {
            if(Helper().checkUserRole(requireContext())) {
                val intent = Intent(activity, RestaurantActivity::class.java)
                intent.putExtra("userId", Helper().getCurrentUser(requireContext()))
                intent.putExtra("resId", Helper().getCurrentResId(requireContext()))
                startActivity(intent)
            } else {
                
            }
        }
    }

    private fun callApiYouLike() {
        HomeFoodApi.homeFoodYouLikeApi.getDataFoodYouLike().enqueue(object : Callback<List<FoodCanYouLike>>{
            override fun onResponse(
                call: Call<List<FoodCanYouLike>>,
                response: Response<List<FoodCanYouLike>>
            ) {
                val foodListYouLike = response.body()
                if (foodListYouLike != null) {
                    foodYouLikeAdapter = FoodHomeYouLikeAdapter(foodListYouLike, object : ClickItemFoodListener {
                        override fun onClickItemFood(food: FoodSearchModel) {
                            TODO("Not yet implemented")
                        }

                        override fun onClickItemFoodAround(food: HomeFood) {
                            TODO("Not yet implemented")
                        }

                        override fun onClickItemFoodCanLike(food: FoodCanYouLike) {
                            onClickItemFoodLike(food)
                        }

                    })
                    rcViewYouLike.adapter = foodYouLikeAdapter
                }
            }

            override fun onFailure(call: Call<List<FoodCanYouLike>>, t: Throwable) {
                Toast.makeText(context, "Call Failed", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun callApiFoodAround() {
        HomeFoodApi.homeFoodApi.getDataFood().enqueue(object : Callback<List<HomeFood>>{
            override fun onResponse(
                call: Call<List<HomeFood>>,
                response: Response<List<HomeFood>>
            ) {
                val foodList = response.body()
                if (foodList != null) {
                    foodAdapter = FoodHomeListAdapter(foodList, object : ClickItemFoodListener {
                        override fun onClickItemFood(food: FoodSearchModel) {
                            TODO("Not yet implemented")
                        }

                        override fun onClickItemFoodAround(food: HomeFood) {
                            onClickItemFoodAroundYou(food)
                        }

                        override fun onClickItemFoodCanLike(food: FoodCanYouLike) {
                            TODO("Not yet implemented")
                        }

                    })
                    rcView.adapter = foodAdapter
                }
            }

            override fun onFailure(call: Call<List<HomeFood>>, t: Throwable) {
                Toast.makeText(context, "Call Failed", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun onClickItemFoodLike(foodClick: FoodCanYouLike) {
        val i = Intent(context, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("food", foodClick)
        i.putExtras(bundle)
        i.putExtra("model", "likeFood")
        startActivity(i)
    }

    private fun onClickItemFoodAroundYou(foodClick: HomeFood) {
        val i = Intent(context, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("food", foodClick)
        i.putExtras(bundle)
        i.putExtra("model", "homeFood")
        startActivity(i)
    }
}