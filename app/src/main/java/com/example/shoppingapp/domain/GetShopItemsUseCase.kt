package com.example.shoppingapp.domain

import androidx.lifecycle.LiveData

class GetShopItemsUseCase(private val shopRepository: ShopRepository) {

    fun getShopItems(): LiveData<List<ShopItem>> {
        return shopRepository.getShopItems()
    }
}