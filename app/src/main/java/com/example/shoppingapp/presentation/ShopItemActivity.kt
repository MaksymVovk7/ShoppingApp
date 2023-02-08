package com.example.shoppingapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppingapp.R
import com.example.shoppingapp.domain.ShopItem

class ShopItemActivity : AppCompatActivity() {

    /*private lateinit var shopItemViewModel: ShopItemViewModel

    private lateinit var textInputName: TextInputLayout
    private lateinit var textInputCount: TextInputLayout
    private lateinit var editTextName: EditText
    private lateinit var editTextCount: EditText
    private lateinit var buttonSave: Button*/

    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        parseIntent()
        /*shopItemViewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        initViews()
        addChangeTextListener()
        showLaunchMode()
        setUpObserversViewModel()*/
    }

    /*private fun addChangeTextListener() {

        editTextName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                shopItemViewModel.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) { }

        })

        editTextCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                shopItemViewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(p0: Editable?) { }

        })
    }*/

    private fun showLaunchMode() {
        val fragment = when (screenMode) {
            MODE_ADD -> ShopItemFragment.newInstanceAddItem()
            MODE_EDIT -> ShopItemFragment.newInstanceEditItem(shopItemId)
            else -> throw RuntimeException("Unknown screen mode $screenMode")
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.shop_item_container, fragment)
            .commit()
    }

    /*private fun launchAddMode() {
        buttonSave.setOnClickListener {
            shopItemViewModel.addShopItem(editTextName.text.toString(), editTextCount.text.toString())
        }
    }

    private fun launchEditMode() {
        shopItemViewModel.getShopItem(shopItemId)
        shopItemViewModel.shopItem.observe(this) {
            editTextName.setText(it.name)
            editTextCount.setText(it.count.toString())
        }
        buttonSave.setOnClickListener {
            shopItemViewModel.editShopItem(editTextName.text?.toString(), editTextCount.text?.toString())
        }
    }*/

    /*private fun setUpObserversViewModel() {
        shopItemViewModel.errorInputName.observe(this) {
            val message = if (it) {
                getString(R.string.error_name_message)
            } else {
                null
            }
            textInputName.error = message
        }

        shopItemViewModel.errorInputCount.observe(this) {
            val message = if (it) {
                getString(R.string.error_count_message)
            } else {
                null
            }
            textInputCount.error = message
        }

        shopItemViewModel.closeShopItemScreen.observe(this) {
            finish()
        }
    }*/

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("Param shop item id is absent")
            }
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
        }
    }

    /*private fun initViews() {
        textInputName = findViewById(R.id.textInputLayoutName)
        textInputCount = findViewById(R.id.textInputLayoutCount)
        editTextName = findViewById(R.id.editTextName)
        editTextCount = findViewById(R.id.editTextCount)
        buttonSave = findViewById(R.id.buttonSave)
    }*/

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }
    }
}