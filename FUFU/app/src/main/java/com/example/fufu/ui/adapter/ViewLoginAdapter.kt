package com.example.fufu.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fufu.ui.log_component.fragment.SignInFragment
import com.example.fufu.ui.log_component.fragment.SignUpFragment

class ViewLoginAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            SignInFragment()
        } else {
            SignUpFragment()
        }
    }
}