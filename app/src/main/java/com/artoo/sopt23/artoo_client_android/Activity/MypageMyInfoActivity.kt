package com.artoo.sopt23.artoo_client_android.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.artoo.sopt23.artoo_client_android.R

class MypageMyInfoActivity : AppCompatActivity() {
    lateinit var btn_my_info_my_name : LinearLayout
    lateinit var btn_my_info_my_email : LinearLayout
    lateinit var btn_my_info_my_pw : LinearLayout
    lateinit var btn_my_info_my_contact : LinearLayout
    lateinit var btn_my_info_my_address : LinearLayout
    lateinit var btn_my_info_my_univ : LinearLayout
    lateinit var btn_my_info_my_account : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_my_info)



        val onClick = View.OnClickListener { v: View? ->
            val intent = Intent(this@MypageMyInfoActivity, MypageMyInfoModifyActivity::class.java)

            when(v!!.id) {
                R.id.btn_my_info_my_name -> {
                    startActivity(intent)
                }

                R.id.btn_my_info_my_email -> {
                    intent.putExtra("email", "이메일 변경")

                    startActivity(intent)
                }

                R.id.btn_my_info_my_pw -> {
                    intent.putExtra("pw", "비밀번호 변경")
                    startActivity(intent)
                }

                R.id.btn_my_info_my_contact -> {
                    intent.putExtra("contact", "연락처 변경")
                    startActivity(intent)
                }

                R.id.btn_my_info_my_address -> {
                    intent.putExtra("address", "주소 변경")
                    startActivity(intent)
                }

                R.id.btn_my_info_my_univ -> {
                    intent.putExtra("univ", "대학교 변경")
                    startActivity(intent)
                }

                R.id.btn_my_info_my_account -> {
                    intent.putExtra("account", "계좌 정보 변경")
                    startActivity(intent)
                }
            }
        }

        btn_my_info_my_name = findViewById(R.id.btn_my_info_my_name)
        btn_my_info_my_name.setOnClickListener(onClick)

        btn_my_info_my_email = findViewById(R.id.btn_my_info_my_email)
        btn_my_info_my_email.setOnClickListener(onClick)

        btn_my_info_my_pw = findViewById(R.id.btn_my_info_my_pw)
        btn_my_info_my_pw.setOnClickListener(onClick)

        btn_my_info_my_contact = findViewById(R.id.btn_my_info_my_contact)
        btn_my_info_my_contact.setOnClickListener(onClick)

        btn_my_info_my_address = findViewById(R.id.btn_my_info_my_address)
        btn_my_info_my_address.setOnClickListener(onClick)

        btn_my_info_my_univ = findViewById(R.id.btn_my_info_my_univ)
        btn_my_info_my_univ.setOnClickListener(onClick)

        btn_my_info_my_account = findViewById(R.id.btn_my_info_my_account)
        btn_my_info_my_account.setOnClickListener(onClick)
    }

}