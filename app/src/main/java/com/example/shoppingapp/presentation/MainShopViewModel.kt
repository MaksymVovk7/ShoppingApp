package com.example.shoppingapp.presentation

import androidx.lifecycle.ViewModel
import com.example.shoppingapp.data.ShopRepositoryImpl
import com.example.shoppingapp.domain.DeleteShopItemUseCase
import com.example.shoppingapp.domain.EditShopItemUseCase
import com.example.shoppingapp.domain.GetShopItemsUseCase
import com.example.shoppingapp.domain.ShopItem

class MainShopViewModel: ViewModel() {

    private val shopRepository = ShopRepositoryImpl

    private val getShopItemsUseCase = GetShopItemsUseCase(shopRepository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(shopRepository)
    private val editShopItemUseCase = EditShopItemUseCase(shopRepository)

    val shopListLiveData = getShopItemsUseCase.getShopItems()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun editShopItem(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}