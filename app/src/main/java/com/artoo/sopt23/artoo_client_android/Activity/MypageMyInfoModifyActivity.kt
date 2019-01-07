package com.artoo.sopt23.artoo_client_android.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.activity_mypage_my_info_modify.*

class MypageMyInfoModifyActivity : AppCompatActivity() {


    var key = ""
    var value1: String = ""
    var value2: String? = null
    var value3: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_my_info_modify)

        var title = intent.getStringExtra("title")
        key = intent.getStringExtra("key")

        value1 = intent.getStringExtra("value1")
        if(intent.hasExtra("value2")) value2 = intent.getStringExtra("value2")
        if(intent.hasExtra("value3")) value3 = intent.getStringExtra("value3")

        txt_my_info_modify_title.text = title + " " + "수정"

        my_info_modify_edit_text_1.setText(value1)
        if(value2 != null){
            my_info_modify_edit_text_2.visibility = View.VISIBLE
            my_info_modify_edit_text_2.setText(value2)
        }
        if(value3 != null){
            my_info_modify_edit_text_2.visibility = View.VISIBLE
            my_info_modify_edit_text_2.setText(value3)
        }

        btn_my_info_modify.setOnClickListener {
            if(key == "u_pw"){
                TODO("/users/{u_idx}/myInfo/pw")
            }
            else if(key == "u_account"){
                TODO("u_bank + u_account")
            }
            else{
                TODO("/users/{u_idx}/myInfo")
                TODO("key=value1")
            }
        }
    }
}
