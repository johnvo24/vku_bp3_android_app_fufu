package com.example.fufu.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fufu.ui.main_component.fragment.orders_subFragment.ConfirmFragment
import com.example.fufu.ui.main_component.fragment.orders_subFragment.DeliveredFragment
import com.example.fufu.ui.main_component.fragment.orders_subFragment.DeliveringFragment
import com.example.fufu.ui.main_component.fragment.orders_subFragment.PerformFragment

class OrdersAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ConfirmFragment()
            1 -> PerformFragment()
            2 -> DeliveringFragment()
            else -> {
                DeliveredFragment()
            }
        }
    }
}