package com.example.shoppingapp.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R

class ShopItemsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val textViewName = view.findViewById<TextView>(R.id.textViewName)
    val textViewCount = view.findViewById<TextView>(R.id.textViewCount)

}