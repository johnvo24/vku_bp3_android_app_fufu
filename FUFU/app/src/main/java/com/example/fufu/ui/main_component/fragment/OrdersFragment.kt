package com.example.fufu.ui.main_component.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.fufu.R
import com.example.fufu.ui.adapter.OrdersAdapter
import com.example.fufu.ui.main_component.viewmodel.OrdersViewModel
import com.google.android.material.tabs.TabLayout

class OrdersFragment : Fragment() {

    companion object {
        fun newInstance() = OrdersFragment()
    }

    private lateinit var viewModel: OrdersViewModel

    private lateinit var ordersAdapter: OrdersAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayout = view.findViewById(R.id.tabLayoutOrders)
        viewPager2 = view.findViewById(R.id.viewPager2Orders)

        ordersAdapter = OrdersAdapter(childFragmentManager, lifecycle)

        tabLayout.addTab(tabLayout.newTab().setText("Chờ xác nhận"))
        tabLayout.addTab(tabLayout.newTab().setText("Đang thực hiện"))
        tabLayout.addTab(tabLayout.newTab().setText("Đang giao"))
        tabLayout.addTab(tabLayout.newTab().setText("Đã giao"))

        viewPager2.adapter = ordersAdapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }

}