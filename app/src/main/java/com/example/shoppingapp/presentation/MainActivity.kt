package com.example.shoppingapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.shoppingapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var shopViewModel: MainShopViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shopViewModel = ViewModelProvider(this)[MainShopViewModel::class.java]

    }
}