package com.artoo.sopt23.artoo_client_android.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.activity_enter_the_exhibition.*

class EnterTheExhibitionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_the_exhibition)

        btn_enter_ex.setOnClickListener {
            val nextIntent = Intent(this, ExhibitionDetailActivity::class.java)
            startActivity(nextIntent)
        }
    }
}
