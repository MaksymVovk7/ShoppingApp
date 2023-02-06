package com.example.shoppingapp.presentation

import androidx.lifecycle.ViewModel
import com.example.shoppingapp.data.ShopRepositoryImpl
import com.example.shoppingapp.domain.AddShopItemUseCase
import com.example.shoppingapp.domain.EditShopItemUseCase
import com.example.shoppingapp.domain.GetShopItemUseCase
import com.example.shoppingapp.domain.ShopItem

class ShopItemViewModel : ViewModel() {

    private val shopRepository = ShopRepositoryImpl

    private val getShopItemUseCase = GetShopItemUseCase(shopRepository)
    private val editShopItemUseCase = EditShopItemUseCase(shopRepository)
    private val addShopItemUseCase = AddShopItemUseCase(shopRepository)

    fun getShopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItem(shopItemId)
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val validateFields = validateInput(name, count)
        if (validateFields) {
            val shopItem = ShopItem(name, count, true)
            editShopItemUseCase.editShopItem(shopItem)
        }
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val validateFields = validateInput(name, count)
        if (validateFields) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true

        if (name.isBlank())
        //TODO: shop error input name
            result = false

        if (count <= 0)
        //TODO: shop error input name
            result = false

        return result
    }
}