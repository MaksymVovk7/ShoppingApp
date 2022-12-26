package com.example.shoppingapp.domain

class GetShopItemUseCase(private val shopRepository: ShopRepository) {

    fun getShopItem(id: Int): ShopItem {
        return shopRepository.getShopItem(id)
    }
}