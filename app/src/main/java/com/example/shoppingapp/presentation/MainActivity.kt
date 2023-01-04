package com.example.shoppingapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var shopViewModel: MainShopViewModel
    private lateinit var shopItemsAdapter: ShopItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        shopViewModel = ViewModelProvider(this)[MainShopViewModel::class.java]
        shopViewModel.shopListLiveData.observe(this) {
            shopItemsAdapter.shopItemList = it
        }
    }

    private fun setUpRecyclerView() {
        shopItemsAdapter = ShopItemsAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewShopList)
        with(recyclerView) {
            adapter = shopItemsAdapter
            recycledViewPool.setMaxRecycledViews(
                ShopItemsAdapter.VIEW_TYPE_ENABLED,
                ShopItemsAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopItemsAdapter.VIEW_TYPE_DISABLED,
                ShopItemsAdapter.MAX_POOL_SIZE
            )
        }

    }
}