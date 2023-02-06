package com.example.shoppingapp.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.shoppingapp.domain.ShopItem

class ShopListDiffCallback(
    private val oldItemsList : List<ShopItem>,
    private val newItemsList : List<ShopItem>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldItemsList.size
    }

    override fun getNewListSize(): Int {
        return newItemsList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldShopItem = oldItemsList[oldItemPosition]
        val newShopItem = newItemsList[newItemPosition]
        return oldShopItem.id == newShopItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldShopItem = oldItemsList[oldItemPosition]
        val newShopItem = newItemsList[newItemPosition]
        return oldShopItem == newShopItem
    }
}