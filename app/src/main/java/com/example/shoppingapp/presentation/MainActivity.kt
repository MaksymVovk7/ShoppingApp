package com.example.shoppingapp.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishedListener {

    private lateinit var shopViewModel: MainShopViewModel
    private lateinit var shopItemsAdapter: ShopItemsAdapter

    private var shopItemContainer: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shopItemContainer = findViewById(R.id.shop_item_container)
        setUpRecyclerView()
        shopViewModel = ViewModelProvider(this)[MainShopViewModel::class.java]
        shopViewModel.shopListLiveData.observe(this) {
            shopItemsAdapter.submitList(it)
        }

        val buttonAddShopItem = findViewById<FloatingActionButton>(R.id.buttonAddShopItem)
        buttonAddShopItem.setOnClickListener {
            if (isOnePaneMode()) {
                val intent = ShopItemActivity.newIntentAddItem(this)
                startActivity(intent)
            } else {
                launchFragment(ShopItemFragment.newInstanceAddItem())
            }
        }
    }

    private fun isOnePaneMode(): Boolean {
        return shopItemContainer == null
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container, fragment)
            .addToBackStack(null)
            .commit()
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
            if (isOnePaneMode()) {
                val intent = ShopItemActivity.newIntentEditItem(this, it.id)
                startActivity(intent)
            } else {
                launchFragment(ShopItemFragment.newInstanceEditItem(it.id))
            }
        }
    }

    private fun setUpLongClickListener() {
        shopItemsAdapter.onShopItemLongClickListener = {
            shopViewModel.changeStateShopItem(it)
        }
    }

    override fun onEditingFinished() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }
}