package com.example.shoppingapp.domain

class DeleteShopItemUseCase(private val shopRepository: ShopRepository) {

    fun deleteShopItem(shopItem: ShopItem) {
        shopRepository.deleteShopItem(shopItem)
    }
}