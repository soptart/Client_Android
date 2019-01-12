package com.artoo.sopt23.artoo_client_android.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.activity_withdrawal.*
import org.jetbrains.anko.toast

class WithdrawalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdrawal)
        btn_withdrawal.setOnClickListener {
            try {
                toast("서비스 구현중입니다ㅠㅠ")
                finish()
            } catch (e: Exception) {
            }
        }
    }
}
