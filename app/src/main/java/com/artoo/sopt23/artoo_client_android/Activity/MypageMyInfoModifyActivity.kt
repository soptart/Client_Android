package com.artoo.sopt23.artoo_client_android.Activity

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.Data.Response.Put.PutMypageMyInfoResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Put.PutMypageMyInfoPWResponse
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_mypage_my_info_modify.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageMyInfoModifyActivity : AppCompatActivity() {


    var key = ""
    var value1: String = ""
    var value2: String? = null
    var value3: String? = null

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_my_info_modify)

        var title:String = intent.getStringExtra("title")
        key = intent.getStringExtra("key")

        value1 = intent.getStringExtra("value1")
        if(intent.hasExtra("value2")) value2 = intent.getStringExtra("value2")
        if(intent.hasExtra("value3")) value3 = intent.getStringExtra("value3")

        txt_my_info_modify_title.text = title + " " + "수정"

        if(key == "u_school"){ // 학교
            my_info_modify_edit_text_1.visibility = View.GONE
            my_info_modify_univ_edit_text.visibility = View.VISIBLE
            my_info_modify_univ.visibility = View.VISIBLE
            if(value1.endsWith("대학교")) value1 = value1.substring(0, value1.indexOf("대학교"))
            my_info_modify_univ_edit_text.setText(value1)
        }
        else {
            my_info_modify_edit_text_1.setText(value1)
            my_info_modify_edit_text_1.hint = title
        }

        if(value2 != null){
            my_info_modify_edit_text_2.visibility = View.VISIBLE
            my_info_modify_edit_text_2.setText(value2)
            if(value3 != null){ // 비밀번호
                my_info_modify_edit_text_3.visibility = View.VISIBLE
                my_info_modify_edit_text_3.setText(value3)
                my_info_modify_edit_text_1.hint = "기존 비밀번호"
                my_info_modify_edit_text_2.hint = "새로운 비밀번호(8자 이상)"
                my_info_modify_edit_text_3.hint = "새로운 비밀번호 확인"
            }
            else{ // 계좌
                my_info_modify_edit_text_1.isFocusable = false
                my_info_modify_edit_text_1.hint = "은행"
                my_info_modify_edit_text_2.hint = "계좌번호(-없이 입력해주세요)"
                my_info_modify_edit_text_1.setOnClickListener {
                    try {
                        ll_mypage_myinfo_modify_bank.visibility = View.VISIBLE
                    } catch (e: Exception) {
                    }
                }
                btn_join_bank_kb.setOnClickListener {
                    try {
                        my_info_modify_edit_text_1.setText(btn_join_bank_kb.getText().toString())
                        ll_mypage_myinfo_modify_bank.visibility = View.GONE
                    } catch (e: Exception) {
                    }
                }
                btn_join_bank_keb.setOnClickListener {
                    try {
                        my_info_modify_edit_text_1.setText(btn_join_bank_keb.getText().toString())
                        ll_mypage_myinfo_modify_bank.visibility = View.GONE
                    } catch (e: Exception) {
                    }
                }
                btn_join_bank_shin.setOnClickListener {
                    try {
                        my_info_modify_edit_text_1.setText(btn_join_bank_shin.getText().toString())
                        ll_mypage_myinfo_modify_bank.visibility = View.GONE
                    } catch (e: Exception) {
                    }
                }
                btn_join_bank_wr.setOnClickListener {
                    try {
                        my_info_modify_edit_text_1.setText(btn_join_bank_wr.getText().toString())
                        ll_mypage_myinfo_modify_bank.visibility = View.GONE
                    } catch (e: Exception) {
                    }
                }
                btn_join_bank_nh.setOnClickListener {
                    try {
                        my_info_modify_edit_text_1.setText(btn_join_bank_nh.getText().toString())
                        ll_mypage_myinfo_modify_bank.visibility = View.GONE
                    } catch (e: Exception) {
                    }
                }
                btn_join_bank_ibk.setOnClickListener {
                    try {
                        my_info_modify_edit_text_1.setText(btn_join_bank_ibk.getText().toString())
                        ll_mypage_myinfo_modify_bank.visibility = View.GONE
                    } catch (e: Exception) {
                    }
                }
                btn_join_bank_kdb.setOnClickListener {
                    try {
                        my_info_modify_edit_text_1.setText(btn_join_bank_kdb.getText().toString())
                        ll_mypage_myinfo_modify_bank.visibility = View.GONE
                    } catch (e: Exception) {
                    }
                }
                btn_join_bank_ct.setOnClickListener {
                    try {
                        my_info_modify_edit_text_1.setText(btn_join_bank_ct.getText().toString())
                        ll_mypage_myinfo_modify_bank.visibility = View.GONE
                    } catch (e: Exception) {
                    }
                }
                btn_join_bank_sc.setOnClickListener {
                    try {
                        my_info_modify_edit_text_1.setText(btn_join_bank_sc.getText().toString())
                        ll_mypage_myinfo_modify_bank.visibility = View.GONE
                    } catch (e: Exception) {
                    }
                }
                btn_join_bank_bmk.setOnClickListener {
                    try {
                        my_info_modify_edit_text_1.setText(btn_join_bank_bmk.getText().toString())
                        ll_mypage_myinfo_modify_bank.visibility = View.GONE
                    } catch (e: Exception) {
                    }
                }
                btn_join_bank_dgb.setOnClickListener {
                    try {
                        my_info_modify_edit_text_1.setText(btn_join_bank_dgb.getText().toString())
                        ll_mypage_myinfo_modify_bank.visibility = View.GONE
                    } catch (e: Exception) {
                    }
                }
                btn_join_bank_bnk.setOnClickListener {
                    try {
                        my_info_modify_edit_text_1.setText(btn_join_bank_bnk.getText().toString())
                        ll_mypage_myinfo_modify_bank.visibility = View.GONE
                    } catch (e: Exception) {
                    }
                }
                btn_join_bank_sh.setOnClickListener {
                    try {
                        my_info_modify_edit_text_1.setText(btn_join_bank_sh.getText().toString())
                        ll_mypage_myinfo_modify_bank.visibility = View.GONE
                    } catch (e: Exception) {
                    }
                }
                btn_join_bank_gj.setOnClickListener {
                    try {
                        my_info_modify_edit_text_1.setText(btn_join_bank_gj.getText().toString())
                        ll_mypage_myinfo_modify_bank.visibility = View.GONE
                    } catch (e: Exception) {
                    }
                }
                btn_join_bank_jb.setOnClickListener {
                    try {
                        my_info_modify_edit_text_1.setText(btn_join_bank_jb.getText().toString())
                        ll_mypage_myinfo_modify_bank.visibility = View.GONE
                    } catch (e: Exception) {
                    }
                }
                btn_mypage_myinfo_modify_bank_close.setOnClickListener {
                    try {
                        ll_mypage_myinfo_modify_bank.visibility = View.GONE
                    } catch (e: Exception) {
                    }
                }
            }
        }

        btn_my_info_modify.setOnClickListener {
            try {
                var jsonObject = JSONObject()
                val u_idx = SharedPreferenceController.getUserID(this)
                if(key == "u_pw"){
                    value1 = my_info_modify_edit_text_1.text.toString()
                    value2 = my_info_modify_edit_text_2.text.toString()
                    value3 = my_info_modify_edit_text_3.text.toString()
                    if(value2 == value3 && value2 != "") {
                        jsonObject.put("u_pw_current", value1)
                        jsonObject.put("u_pw_new", value2)
                        jsonObject.put("u_pw_check", value3)
                        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
                        val putMypageMyInfoPWResponse: Call<PutMypageMyInfoPWResponse> =
                            networkService.putMypageMyInfoPWResponse(
                                "application/json", SharedPreferenceController.getAuthorization(this), u_idx, gsonObject
                            )
                        putMypageMyInfoPWResponse.enqueue(object : Callback<PutMypageMyInfoPWResponse> {
                            override fun onFailure(call: Call<PutMypageMyInfoPWResponse>, t: Throwable) {
                                Log.d("MypageMyInfoModifyActivity", t.toString())
                            }

                            override fun onResponse(
                                call: Call<PutMypageMyInfoPWResponse>,
                                response: Response<PutMypageMyInfoPWResponse>
                            ) {
                                Toast.makeText(
                                    this@MypageMyInfoModifyActivity,
                                    response.body()!!.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                                if (response.isSuccessful) {
                                    setResult(Activity.RESULT_OK)
                                    finish()
                                }
                            }
                        })
                    }
                    else if(value2 != value3){
                        toast("New passwords must equal")
                    }
                    else{
                        toast("No Empty Field")
                    }
                }
                else {
                    var cancelFlag = false
                    if (key == "u_account") {
                        value1 = my_info_modify_edit_text_1.text.toString()
                        value2 = my_info_modify_edit_text_2.text.toString()
                        jsonObject.put("u_bank", value1)
                        jsonObject.put("u_account", value2)
                        if(value1 == "" || value2 == "") cancelFlag = true
                    } else {
                        if (key == "u_school") value1 = my_info_modify_univ_edit_text.text.toString() + "대학교"
                        else value1 = my_info_modify_edit_text_1.text.toString()
                        jsonObject.put(key, value1)
                        if(value1 == "") cancelFlag = true
                        if(key == "u_email" && !android.util.Patterns.EMAIL_ADDRESS.matcher(value1).matches()) cancelFlag = true
                    }

                    if(!cancelFlag) {
                        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
                        val putMypageMyInfoResponse: Call<PutMypageMyInfoResponse> = networkService.putMypageMyInfoResponse(
                            "application/json", SharedPreferenceController.getAuthorization(this), u_idx, gsonObject
                        )
                        putMypageMyInfoResponse.enqueue(object : Callback<PutMypageMyInfoResponse> {
                            override fun onFailure(call: Call<PutMypageMyInfoResponse>, t: Throwable) {
                                Log.d("MypageMyInfoModifyActivity", t.toString())
                            }

                            override fun onResponse(
                                call: Call<PutMypageMyInfoResponse>,
                                response: Response<PutMypageMyInfoResponse>
                            ) {
                                Toast.makeText(
                                    this@MypageMyInfoModifyActivity,
                                    response.body()!!.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                                if (response.isSuccessful) {
                                    setResult(Activity.RESULT_OK)
                                    finish()
                                }
                            }
                        })
                    }
                    else{
                        toast("No Empty Field").show()
                    }
                }
            } catch (e: Exception) {
            }
        }
    }
}