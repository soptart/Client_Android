package com.artoo.sopt23.artoo_client_android.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.Data.Response.Post.PostLoginResponse
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    val jsonObject = JSONObject()
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setOnClickListener()

        if(SharedPreferenceController.getUserID(this@LoginActivity) != -1){
            startActivity<MainActivity>()
            finish()
        }
    }

    private fun setOnClickListener() {
        btn_login.setOnClickListener {

            try {
                if (et_login_email.text.toString().isNotEmpty() && et_login_password.text.toString().isNotEmpty()) {

                    val input_email = et_login_email.text.toString()
                    val input_pw = et_login_password.text.toString()


                    jsonObject.put("u_email", input_email)
                    jsonObject.put("u_pw", input_pw)

                    getLoginResponse()
                }
            } catch (e: Exception) {
            }
        }
        btn_login_join.setOnClickListener {
            try {
                val intent = Intent(this, JoinActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
    }

    private fun getLoginResponse() {
        Log.d("*****LoginActivity::json::", jsonObject.toString())

        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
        Log.d("*****JoinActivity::gson::", gsonObject.toString())

        val postSignUpResponse: Call<PostLoginResponse> = networkService.postLoginResponse("application/json", gsonObject)
        postSignUpResponse.enqueue(object : Callback<PostLoginResponse> {
            override fun onFailure(call: Call<PostLoginResponse>, t: Throwable) {
                Log.e("*****User_Login_Failed", t.toString())
            }
            override fun onResponse(call: Call<PostLoginResponse>, response: Response<PostLoginResponse>) {

                    if (response.isSuccessful) {
                        if (response.body()!!.status == 400) {
                            toast("아이디와 비밀번호를 확인해주세요")
                        } else {

                            response.body()!!.status
                            val token = response.body()!!.data.token
                            //저번 시간에 배웠던 SharedPreference에 토큰을 저장!
                            SharedPreferenceController.setAuthorization(this@LoginActivity, token)
                            toast("앗투 하투!♡")

                            val u_id = response.body()!!.data.userIdx
                            SharedPreferenceController.setUserID(this@LoginActivity, u_id)
                            startActivity<MainActivity>()
                            finish()
                        }
                    }

            }
        })
    }
}
