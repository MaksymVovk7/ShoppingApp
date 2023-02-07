package com.example.shoppingapp.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var shopViewModel: MainShopViewModel
    private lateinit var shopItemsAdapter: ShopItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        shopViewModel = ViewModelProvider(this)[MainShopViewModel::class.java]
        shopViewModel.shopListLiveData.observe(this) {
            shopItemsAdapter.submitList(it)
        }

        val buttonAddShopItem = findViewById<FloatingActionButton>(R.id.buttonAddShopItem)
        buttonAddShopItem.setOnClickListener {
            val intent = ShopItemActivity.newIntentAddItem(this)
            startActivity(intent)
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

        setUpLongClickListener()
        setUpClickListener()
        setUpSwipeListener(recyclerView)
    }

    private fun setUpSwipeListener(recyclerView: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopItemsAdapter.currentList[viewHolder.adapterPosition]
                shopViewModel.deleteShopItem(item)
            }

        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun setUpClickListener() {
        shopItemsAdapter.onShopItemClickListener = {
            //Toast.makeText(this, "Clicked $it", Toast.LENGTH_SHORT).show()
            val intent = ShopItemActivity.newIntentEditItem(this, it.id)
            startActivity(intent)
        }
    }

    private fun setUpLongClickListener() {
        shopItemsAdapter.onShopItemLongClickListener = {
            shopViewModel.changeStateShopItem(it)
            Toast.makeText(this, "LongClicked $it", Toast.LENGTH_SHORT).show()
        }
    }
}