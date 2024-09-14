    package com.example.fufu.ui.adapter

    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.lifecycle.LiveData
    import androidx.recyclerview.widget.RecyclerView
    import com.example.fufu.data.model.CartItem
    import com.example.fufu.data.model.Item
    import com.example.fufu.databinding.RestaurantMenuItemLayoutBinding
    import com.example.fufu.ui.common.action.IRestaurantAction
    import com.example.fufu.ui.shop_component.viewmodel.RestaurantDetailViewModel

    class RestaurantMenuItemAdapter(private val menuList: List<Item>, private val cart: List<CartItem>) : RecyclerView.Adapter<RestaurantMenuItemAdapter.ViewHolder>() {
        var iRestautantAction: IRestaurantAction? = null

        class ViewHolder(binding: RestaurantMenuItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
            private val bindingItem = binding
            fun bind(item: Item, iRestaurantAction: IRestaurantAction?, cart: List<CartItem>) {
                bindingItem.item = item
                if(cart.find { it.item.itemId == item.itemId } != null) {
                    bindingItem.btnAddCart.visibility = View.GONE
                    bindingItem.btnRemoveCart.visibility = View.VISIBLE
                } else {
                    bindingItem.btnAddCart.visibility = View.VISIBLE
                    bindingItem.btnRemoveCart.visibility = View.GONE
                }
                bindingItem.btnAddCart.setOnClickListener {
                    iRestaurantAction?.onAddToCart(CartItem(item, 1))
                    bindingItem.btnAddCart.visibility = View.GONE
                    bindingItem.btnRemoveCart.visibility = View.VISIBLE
                }
                bindingItem.btnRemoveCart.setOnClickListener {
                    iRestaurantAction?.onRemoveFromCart(CartItem(item, 1))
                    bindingItem.btnAddCart.visibility = View.VISIBLE
                    bindingItem.btnRemoveCart.visibility = View.GONE
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = RestaurantMenuItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(menuList[position], iRestautantAction, cart)
        }

        override fun getItemCount(): Int {
            return menuList.size
        }
    }