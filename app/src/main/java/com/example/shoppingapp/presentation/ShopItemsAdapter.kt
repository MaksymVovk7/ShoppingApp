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

    class ShopItemsViewHolder(view: View) : ViewHolder(view) {
        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val textViewCount = view.findViewById<TextView>(R.id.textViewCount)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_shop_disabled,
            parent,
            false
        )
        return ShopItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemsViewHolder, position: Int) {
        val item = shopItemList[position]
        holder.textViewName.text = item.name
        holder.textViewCount.text = item.count.toString()
    }

    override fun getItemCount(): Int {
        return shopItemList.size
    }
}