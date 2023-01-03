package com.example.shoppingapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppingapp.domain.ShopItem
import com.example.shoppingapp.domain.ShopRepository

object ShopRepositoryImpl: ShopRepository {

    private val shopListLiveData = MutableLiveData<List<ShopItem>>()

    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateLiveData()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateLiveData()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldItem = getShopItem(shopItem.id)
        shopList.remove(oldItem)
        shopList.add(shopItem)
    }

    override fun getShopItem(id: Int): ShopItem {
        return shopList.find { it.id == id } ?: throw RuntimeException("Elment with id $id not found")
    }

    override fun getShopItems(): LiveData<List<ShopItem>> {
        return shopListLiveData
    }

    private fun updateLiveData() {
        shopListLiveData.value = shopList.toList()
    }
}