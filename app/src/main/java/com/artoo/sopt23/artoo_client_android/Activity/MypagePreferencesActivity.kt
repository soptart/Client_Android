package com.artoo.sopt23.artoo_client_android.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.artoo.sopt23.artoo_client_android.CustomView.LogoutDialog
import com.artoo.sopt23.artoo_client_android.CustomView.PreparingDialog
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.activity_mypage_preferences.*
import kotlinx.android.synthetic.main.dialog_logout.*
import org.jetbrains.anko.*

class MypagePreferencesActivity : AppCompatActivity() {
    lateinit var btn_mypage_help : LinearLayout
    lateinit var btn_mypage_artoo_service : LinearLayout
    lateinit var btn_mypage_my_info : LinearLayout
    lateinit var btn_mypage_personal_info : LinearLayout
    lateinit var btn_mypage_logout : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_preferences)

        val onClick = View.OnClickListener {v: View? ->

            try {
                when(v!!.id) {
                    R.id.btn_mypage_my_info -> {
                        val intent = Intent(this, MypageMyInfoActivity::class.java)
                        startActivity(intent)
                    }

                    R.id.btn_mypage_help -> {
                        val intent = Intent(this, MypageHelpActivity::class.java)
                        startActivity(intent)
                    }

                    R.id.btn_mypage_artoo_service -> {
                        val intent = Intent(this, MypageServiceActivity::class.java)
                        startActivity(intent)
                    }

                    R.id.btn_mypage_personal_info -> {
                        val intent = Intent(this, MypagePolicyActivity::class.java)
                        startActivity(intent)
                    }

                    R.id.btn_mypage_logout -> {
                        val dialog = LogoutDialog(this@MypagePreferencesActivity)
                        dialog.show()
                        dialog.btn_LogOut_Yes.setOnClickListener {
                            SharedPreferenceController.clearSPC(this)
                            val intent:Intent = Intent(this, LoginActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                        dialog.btn_LogOut_No.setOnClickListener {
                            dialog.cancel()
                        }
                    }
                }
            } catch (e: Exception) {
            }
        }

        btn_mypage_my_info = findViewById(R.id.btn_mypage_my_info)
        btn_mypage_my_info.setOnClickListener(onClick)

        btn_mypage_help = findViewById(R.id.btn_mypage_help)
        btn_mypage_help.setOnClickListener(onClick)

        btn_mypage_artoo_service = findViewById(R.id.btn_mypage_artoo_service)
        btn_mypage_artoo_service.setOnClickListener(onClick)

        btn_mypage_personal_info = findViewById(R.id.btn_mypage_personal_info)
        btn_mypage_personal_info.setOnClickListener(onClick)

        btn_mypage_logout = findViewById(R.id.btn_mypage_logout)
        btn_mypage_logout.setOnClickListener(onClick)
    }

}
