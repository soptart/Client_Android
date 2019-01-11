package com.artoo.sopt23.artoo_client_android.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_product_zoom.*

class ProductZoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_zoom)

        txt_product_zoom_title.text = intent.getStringExtra("a_name")
        Glide.with(this)
            .load(intent.getStringExtra("pic_url"))
            .into(pv_product_zoom_product)
        pv_product_zoom_product
    }
}
