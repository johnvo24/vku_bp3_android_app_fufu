package com.example.fufu

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fufu.asset.Helper
import com.example.fufu.databinding.ActivityMainBinding
import com.example.fufu.ui.info_component.InfoActivity
import com.example.fufu.ui.search_component.SearchFoodActivity
import com.example.fufu.ui.shop_component.RestaurantActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.search.SearchView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //components
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //navigation host
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        //bottom navigation handle
        bottomNavigationView = binding.bottomApp.bottomNavigation
        bottomNavigationView.selectedItemId = R.id.bottom_navigation_bar_home
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_navigation_bar_fufu -> {
                    val navOptions = NavOptions.Builder()
                        .setEnterAnim(R.anim.slide_in_right)
                        .setExitAnim(R.anim.slide_out_left)
                        .setPopEnterAnim(R.anim.slide_in_left)
                        .setPopExitAnim(R.anim.slide_out_right)
                        .build()

                    navController.navigate(R.id.fuFuFragment, null, navOptions)
                    true
                }
                R.id.bottom_navigation_bar_home -> {
                    val navOptions = NavOptions.Builder()
                        .setEnterAnim(R.anim.slide_in_right)
                        .setExitAnim(R.anim.slide_out_left)
                        .setPopEnterAnim(R.anim.slide_in_left)
                        .setPopExitAnim(R.anim.slide_out_right)
                        .build()

                    navController.navigate(R.id.homeFragment, null, navOptions)
                    true
                }
                R.id.bottom_navigation_bar_orders -> {
                    val navOptions = NavOptions.Builder()
                        .setEnterAnim(R.anim.slide_in_right)
                        .setExitAnim(R.anim.slide_out_left)
                        .setPopEnterAnim(R.anim.slide_in_left)
                        .setPopExitAnim(R.anim.slide_out_right)
                        .build()

                    navController.navigate(R.id.ordersFragment, null, navOptions)
                    true
                }
                else -> false
            }
        }
        bottomNavigationView.setOnNavigationItemReselectedListener {  }

        //startActivity(Intent(this, RestaurantActivity::class.java))

        //search item food
        binding.topApp.searchBar.setOnSearchClickListener {
            val intent = Intent(this, SearchFoodActivity::class.java)
            startActivity(intent)
        }
        //btn go to ui user
        binding.topApp.btnAvatar.setOnClickListener {
            val i = Intent(this, InfoActivity::class.java)
            startActivity(i)
        }

        //SharedPreferences
//        val sharedPref = this.getSharedPreferences("currentUser", MODE_PRIVATE)
//        sharedPref.edit().putString("userId", "").apply()
//        sharedPref.edit().putString("userRole", "0").apply()
//        sharedPref.edit().putString("resId", "").apply()
    }
}