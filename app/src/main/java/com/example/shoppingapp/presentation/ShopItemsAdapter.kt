package com.example.shoppingapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppingapp.R
import com.example.shoppingapp.domain.ShopItem

class ShopItemsAdapter : ListAdapter<ShopItem, ShopItemsViewHolder>(ShopItemDiffCallback()) {

    /*var shopItemList = listOf<ShopItem>()
        set(value) {
            val callback = ShopListDiffCallback(shopItemList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }*/

    var onShopItemLongClickListener : ((ShopItem) -> Unit)? = null
    var onShopItemClickListener : ((ShopItem) -> Unit)? = null

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
        const val MAX_POOL_SIZE = 10
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemsViewHolder {

        val layout = when(viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown viewType : $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(
            layout,
            parent,
            false
        )
        return ShopItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemsViewHolder, position: Int) {
        val item = getItem(position)
        holder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(item)
            true
        }
        holder.view.setOnClickListener {
            onShopItemClickListener?.invoke(item)
        }
        holder.textViewName.text = item.name
        holder.textViewCount.text = item.count.toString()
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) VIEW_TYPE_ENABLED else VIEW_TYPE_DISABLED
    }
}