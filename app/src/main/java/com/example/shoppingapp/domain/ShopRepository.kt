package com.example.shoppingapp.domain

import androidx.lifecycle.LiveData

interface ShopRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(id: Int): ShopItem

    fun getShopItems(): LiveData<List<ShopItem>>
}