package com.example.shoppingapp.domain

class GetShopItemsUseCase(private val shopRepository: ShopRepository) {

    fun getShopItems(): List<ShopItem> {
        return shopRepository.getShopItems()
    }
}