package com.example.fufu.ui.shop_component.fragment

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fufu.asset.Helper
import com.example.fufu.data.model.CartItem
import com.example.fufu.databinding.FragmentRestaurantDetailBinding
import com.example.fufu.ui.adapter.RestaurantMenuItemAdapter
import com.example.fufu.ui.cart_component.CartActivity
import com.example.fufu.ui.common.action.IRestaurantAction
import com.example.fufu.ui.shop_component.viewmodel.RestaurantDetailViewModel

class RestaurantDetailFragment : Fragment() {
    private lateinit var restaurantDetailViewModel: RestaurantDetailViewModel
    private lateinit var restaurantDetailBinding: FragmentRestaurantDetailBinding
    //Adapter
    private lateinit var restaurantMenuItemAdapter: RestaurantMenuItemAdapter
    //Variable
    private lateinit var userId: String
    private lateinit var resId: String

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //userId
        userId = activity?.intent?.getStringExtra("userId").toString()
        //resId 
        resId = activity?.intent?.getStringExtra("resId").toString()
        //view model
        restaurantDetailViewModel = ViewModelProvider(this)[RestaurantDetailViewModel::class.java]
        //binding
        restaurantDetailBinding = FragmentRestaurantDetailBinding.inflate(layoutInflater)
        //adapter
        restaurantMenuItemAdapter = RestaurantMenuItemAdapter(emptyList(), emptyList())

        restaurantDetailViewModel.getRestaurantByResId(resId)
        restaurantDetailViewModel.getMenuList(resId)
        restaurantDetailViewModel.getCart(requireContext())

        //restaurant detail
        restaurantDetailViewModel.restaurantLiveData.observe(viewLifecycleOwner) {
            restaurantDetailBinding.restaurant = it
            restaurantDetailBinding.notifyPropertyChanged(this.id)
        }
        //recyclerview for menuList
        restaurantDetailViewModel.menuListLiveData.observe(viewLifecycleOwner) {
            val cart = Helper().getCart(requireContext())
            restaurantMenuItemAdapter = RestaurantMenuItemAdapter(it, cart ?: emptyList())
            restaurantMenuItemAdapter.iRestautantAction = object : IRestaurantAction {
                override fun onAddToCart(cartItem: CartItem) {
                    restaurantDetailViewModel.addCart(cartItem)
                }
                override fun onRemoveFromCart(cartItem: CartItem) {
                    restaurantDetailViewModel.removeCart(cartItem)
                }
            }
            restaurantDetailBinding.rcvRestaurantMenuList.adapter = restaurantMenuItemAdapter
        }
        //cart
        restaurantDetailViewModel.cartLiveData.observe(viewLifecycleOwner) { cart ->
            if(cart != null && cart.isNotEmpty()) {
                restaurantDetailViewModel.saveCart(requireContext(), cart)
                restaurantDetailBinding.cartAmount.text = cart.size.toString()
                restaurantDetailBinding.containerBtnCart.visibility = View.VISIBLE
            } else {
                restaurantDetailViewModel.saveCart(requireContext(), emptyList())
                restaurantDetailBinding.cartAmount.text = "0"
                restaurantDetailBinding.containerBtnCart.visibility = View.GONE
            }
        }
        return restaurantDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        restaurantDetailBinding.btnCart.setOnClickListener {
            startActivity(Intent(context, CartActivity::class.java))
        }
    }

}