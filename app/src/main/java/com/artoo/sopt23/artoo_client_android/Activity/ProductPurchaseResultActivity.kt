package com.artoo.sopt23.artoo_client_android.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.activity_product_purchase_result.*

class ProductPurchaseResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_purchase_result)

        result_artwork_name.text = intent.getStringExtra("artname")
        result_artwork_price.text = intent.getStringExtra("price")
        val ispost = intent.getBooleanExtra("isPost", false)
        if(!ispost){
            result_key1.text = "이름"
            result_key2.text = "전화번호"
            result_key3.text = "계좌번호"

            result_value1.text = intent.getStringExtra("value1")
            result_value2.text = intent.getStringExtra("value2")
            result_value3.text = intent.getStringExtra("value3")
        }

        txt_viewmore_btn.setOnClickListener {
            finish()
        }
        txt_checkpurchaselist.setOnClickListener {
            // 구매리스트 액티비티가 뭔지 몰라서 냅둠. 채워주세요~
        }
    }
}
