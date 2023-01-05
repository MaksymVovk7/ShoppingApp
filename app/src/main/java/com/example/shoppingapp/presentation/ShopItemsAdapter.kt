package com.example.shoppingapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.shoppingapp.R
import com.example.shoppingapp.domain.ShopItem

class ShopItemsAdapter : RecyclerView.Adapter<ShopItemsAdapter.ShopItemsViewHolder>() {

    var shopItemList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onShopItemLongClickListener : ((ShopItem) -> Unit)? = null
    var onShopItemClickListener : ((ShopItem) -> Unit)? = null

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
        const val MAX_POOL_SIZE = 10
    }

    class ShopItemsViewHolder(val view: View) : ViewHolder(view) {
        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val textViewCount = view.findViewById<TextView>(R.id.textViewCount)

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

    override fun onBindViewHolder(holder: ShopItemsAdapter.ShopItemsViewHolder, position: Int) {
        val item = shopItemList[position]
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

    override fun getItemCount(): Int {
        return shopItemList.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = shopItemList[position]
        return if (item.enabled) VIEW_TYPE_ENABLED else VIEW_TYPE_DISABLED
    }
}