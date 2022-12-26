package com.example.shoppingapp.domain

class AddShopItemUseCase(private val shopRepository: ShopRepository) {

    fun addShopItem(shopItem: ShopItem) {
        shopRepository.addShopItem(shopItem)
    }
}