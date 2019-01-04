package com.artoo.sopt23.artoo_client_android.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.activity_mypage_my_info_modify.*

class MypageMyInfoModifyActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_my_info_modify)

        val txt_my_info_modify_title: TextView = findViewById(R.id.txt_my_info_modify_title) as TextView

        // 이름 변경
        my_info_modify_edit_text_2.visibility = View.INVISIBLE
        my_info_modify_edit_text_3.visibility = View.INVISIBLE
        my_info_modify_view_1.visibility = View.INVISIBLE
        my_info_modify_view_2.visibility = View.INVISIBLE
        my_info_modify_view_3.visibility = View.INVISIBLE

        // 이메일 변경
        if (intent.hasExtra("email")) {
            txt_my_info_modify_title.text = intent.getStringExtra("email")
            my_info_modify_edit_text_1.setHint("yjc8966@artoo.com")

        } else {

        }
        // 비밀번호 변경
        if (intent.hasExtra("pw")) {
            txt_my_info_modify_title.text = intent.getStringExtra("pw")

            my_info_modify_univ_edit_text.visibility = View.GONE

            my_info_modify_edit_text_1.visibility = View.VISIBLE
            my_info_modify_edit_text_1.setHint("기존 비밀번호")
            my_info_modify_edit_text_2.visibility = View.VISIBLE
            my_info_modify_edit_text_2.setHint("새로운 비밀번호 (8자 이상)")
            my_info_modify_edit_text_3.visibility = View.VISIBLE
            my_info_modify_edit_text_3.setHint("새로운 비밀번호 재입력")
            my_info_modify_view_1.visibility = View.VISIBLE
            my_info_modify_view_2.visibility = View.VISIBLE

            my_info_modify_univ.visibility = View.GONE
            my_info_modify_account_arrow.visibility = View.GONE

        } else {

        }
        // 연락처 변경
        if (intent.hasExtra("contact")) {
            txt_my_info_modify_title.text = intent.getStringExtra("contact")
        } else {

        }
        // 주소 변경
        if (intent.hasExtra("address")) {
            txt_my_info_modify_title.text = intent.getStringExtra("address")
        } else {

        }
        // 대학교 변경
        if (intent.hasExtra("univ")) {
            txt_my_info_modify_title.text = intent.getStringExtra("univ")

            my_info_modify_univ_edit_text.visibility = View.VISIBLE

            my_info_modify_edit_layout.visibility = View.VISIBLE

            my_info_modify_edit_text_1.visibility = View.INVISIBLE
            my_info_modify_edit_text_2.visibility = View.INVISIBLE
            my_info_modify_edit_text_3.visibility = View.INVISIBLE
            my_info_modify_view_1.visibility = View.INVISIBLE
            my_info_modify_view_2.visibility = View.INVISIBLE

            my_info_modify_univ.visibility = View.VISIBLE
            my_info_modify_account_arrow.visibility = View.GONE

        } else {

        }
        // 계좌 정보 변경
        if (intent.hasExtra("account")) {
            txt_my_info_modify_title.text = intent.getStringExtra("account")

            my_info_modify_edit_text_2.visibility = View.VISIBLE
            my_info_modify_edit_text_3.visibility = View.INVISIBLE
            my_info_modify_view_1.visibility = View.VISIBLE
            my_info_modify_view_2.visibility = View.INVISIBLE
            my_info_modify_view_3.visibility = View.INVISIBLE
            my_info_modify_univ.visibility = View.GONE
            my_info_modify_account_arrow.visibility = View.VISIBLE

        } else {

        }
    }
}
