package com.example.fufu.ui.shop_component

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.example.fufu.R
import com.example.fufu.data.network.RetrofitHelper
import com.example.fufu.data.network.restaurant.MenuApi
import com.example.fufu.databinding.ActivityRestaurantBinding
import com.example.fufu.ui.common.action.HeaderLayoutAction
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RestaurantActivity : AppCompatActivity() {
    lateinit var binding: ActivityRestaurantBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Action of header layout
        HeaderLayoutAction(this)

        //navigation host
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_restaurant_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        bottomNavigationView = binding.bottomApp.bottomNavigation
        bottomNavigationView.selectedItemId = R.id.bottom_navigation_bar_menu
        val navOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setPopExitAnim(R.anim.slide_out_right)
            .build()
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.bottom_navigation_bar_menu ->  {
                    navController.navigate(R.id.restaurantDetailFragment2, null, navOptions)
                    true
                }
                R.id.bottom_navigation_bar_reviews -> {
                    navController.navigate(R.id.reviewsFragment, null, navOptions)
                    true
                }
                R.id.bottom_navigation_bar_images -> {
                    navController.navigate(R.id.galleryFragment, null, navOptions)
                    true
                }
                R.id.bottom_navigation_bar_map -> {
                    navController.navigate(R.id.mapsFragment, null, navOptions)
                    true
                }
                else -> false
            }
        }
        bottomNavigationView.setOnItemReselectedListener {  }
    }
}