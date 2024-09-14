package com.example.fufu.ui.log_component

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.fufu.databinding.ActivitySignInSignUpBinding
import com.example.fufu.ui.adapter.ViewLoginAdapter
import com.google.android.material.tabs.TabLayout

class SignInSignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInSignUpBinding

    private lateinit var loginAdapter: ViewLoginAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginAdapter = ViewLoginAdapter(supportFragmentManager, lifecycle)

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Sign In"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Sign Up"))

        binding.viewPager2.adapter = loginAdapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })
    }
}