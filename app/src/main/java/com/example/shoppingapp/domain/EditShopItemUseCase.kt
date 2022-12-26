package com.example.shoppingapp.domain

class EditShopItemUseCase(private val shopRepository: ShopRepository) {

    fun editShopItem(shopItem: ShopItem) {
        shopRepository.editShopItem(shopItem)
    }
}