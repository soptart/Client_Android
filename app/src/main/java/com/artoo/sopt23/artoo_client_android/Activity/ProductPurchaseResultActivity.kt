package com.artoo.sopt23.artoo_client_android.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.activity_product_purchase_result.*
import org.jetbrains.anko.startActivity

class ProductPurchaseResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_purchase_result)

        tv_product_purchase_result_product_name.text = intent.getStringExtra("artname")
        tv_product_purchase_result_product_price.text = intent.getStringExtra("price")

        tv_product_purchase_result_name.text = intent.getStringExtra("value1")
        tv_product_purchase_result_phone.text = intent.getStringExtra("value2")
        tv_product_purchase_result_account.text = intent.getStringExtra("value3")

        tv_product_purchase_result_viewmore.setOnClickListener {
            try {
                finish()
            } catch (e: Exception) {
            }
        }
        tv_product_purchase_result_checkpurchaselist.setOnClickListener {
            try {
                startActivity<AlarmActivity>()
            } catch (e: Exception) {
            }
        }
    }
}
