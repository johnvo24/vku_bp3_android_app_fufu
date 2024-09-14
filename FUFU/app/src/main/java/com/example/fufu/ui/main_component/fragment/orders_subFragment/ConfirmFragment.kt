package com.example.fufu.ui.main_component.fragment.orders_subFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fufu.R
import com.example.fufu.data.model.FoodOrders
import com.example.fufu.ui.adapter.FoodOrdersAdapter

class ConfirmFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var foodListOrders: List<FoodOrders>
    private lateinit var foodOrdersAdapter: FoodOrdersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rcViewOrders)
        foodListOrders = getListFoodOrders()
        foodOrdersAdapter = FoodOrdersAdapter(foodListOrders)

        recyclerView.adapter = foodOrdersAdapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun getListFoodOrders(): List<FoodOrders> {
        val listFood: ArrayList<FoodOrders> = ArrayList()
        listFood.add(FoodOrders(R.drawable.food_test, "Bún Bò Huế", "Quán: Thằng Bờm",
            2, 40000))
        listFood.add(FoodOrders(R.drawable.food_test, "Bún Bò Huế", "Quán: Thằng Bờm",
            2, 40000))
        listFood.add(FoodOrders(R.drawable.food_test, "Bún Bò Huế", "Quán: Thằng Bờm",
            2, 40000))
        listFood.add(FoodOrders(R.drawable.food_test, "Bún Bò Huế", "Quán: Thằng Bờm",
            2, 40000))

        return listFood
    }
}