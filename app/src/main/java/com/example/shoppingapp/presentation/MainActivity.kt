package com.example.shoppingapp.presentation

import android.os.Bundle
import android.widget.Toast
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
            shopItemsAdapter.notifyDataSetChanged()
        }
    }

    private fun setUpRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewShopList)
        with(recyclerView) {
            shopItemsAdapter = ShopItemsAdapter()
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

        shopItemsAdapter.onShopItemLongClickListener = {
            shopViewModel.changeStateShopItem(it)
            Toast.makeText(this, "LongClicked $it", Toast.LENGTH_SHORT).show()
        }
        shopItemsAdapter.onShopItemClickListener = {
            Toast.makeText(this, "Clicked $it", Toast.LENGTH_SHORT).show()
        }
    }
}