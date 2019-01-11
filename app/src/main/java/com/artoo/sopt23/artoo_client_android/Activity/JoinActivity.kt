package com.artoo.sopt23.artoo_client_android.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.artoo.sopt23.artoo_client_android.Data.Response.Post.PostJoinResponse
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_join.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Patterns
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetDuplicateEmailResponse
import java.util.regex.Pattern

class JoinActivity : AppCompatActivity() {

    var jsonObject = JSONObject()
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        setOnClickListener()
    }

    private fun setOnClickListener() {

        btn_join_next1.setOnClickListener {

            val input_name: String = et_join_name.text.toString()
            val input_email: String = et_join_email.text.toString()
            val input_password: String = et_join_password.text.toString()

            var check = 1

            if (input_name.length == 0) {
                join_check_name.visibility = View.VISIBLE
                join_check_name.setText("이름을 입력해주세요!")
                check = check * 0
            } else {
                join_check_name.visibility = View.INVISIBLE
                jsonObject.put("u_name", input_name)
                check = check * 1
            }


            if (android.util.Patterns.EMAIL_ADDRESS.matcher(input_email).matches()) {
                check = check * 1
            } else if (input_email.length == 0) {
                join_check_email.visibility = View.VISIBLE
                join_check_email.setText("이메일을 입력해주세요!")
                check = check * 0
            } else {
                join_check_email.visibility = View.VISIBLE
                join_check_email.setText("유효한 이메일이 아닙니다!")
                check = check * 0
            }

            if (input_password.length == 0) {
                join_check_pw.visibility = View.VISIBLE
                join_check_pw.setText("비밀번호를 입력해주세요!")
                check = check * 0
            } else if (input_password.length < 8) {
                join_check_pw.visibility = View.VISIBLE
                join_check_pw.setText("8자리 이상으로 설정해주세요.")
                check = check * 0
            } else {
                join_check_pw.visibility = View.INVISIBLE
                jsonObject.put("u_pw", input_password)
                check = check * 1
            }

            Log.d("*****JoinActivity::json::", jsonObject.toString())

            if (check == 1) {
                val getDuplicationEmailResponse:Call<GetDuplicateEmailResponse> = networkService.getDuplicateEmailResponse(input_email)
                getDuplicationEmailResponse.enqueue(object: Callback<GetDuplicateEmailResponse>{
                    override fun onFailure(call: Call<GetDuplicateEmailResponse>, t: Throwable) {
                        Log.i("JoinActivity", "CommentFailure")
                    }

                    override fun onResponse(call: Call<GetDuplicateEmailResponse>, response: Response<GetDuplicateEmailResponse>) {
                        if(response.isSuccessful && response.body()!!.status == 200) {
                            jsonObject.put("u_email", input_email)
                            join_check_email.visibility = View.INVISIBLE
                            ll_join_first.visibility = View.GONE
                            ll_join_second.visibility = View.VISIBLE

                            join_check_name.visibility = View.INVISIBLE
                            join_check_email.visibility = View.INVISIBLE
                            join_check_pw.visibility = View.INVISIBLE
                        }
                        else{
                            join_check_email.visibility = View.VISIBLE
                            join_check_email.setText("같은 이메일이 존재합니다!")
                            check = check * 0
                        }
                    }
                })
            }
        }

        btn_join_next2.setOnClickListener {

            val input_phone: String = et_join_phone.text.toString()
            val input_university: String = et_join_university.text.toString() + "대학교"

            var check = 1



            if(Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}\$",input_phone)) {
                join_check_phone.visibility = View.INVISIBLE
                jsonObject.put("u_phone", input_phone)
                check = check * 1
            } else if(input_phone.length == 0) {
                join_check_phone.visibility = View.VISIBLE
                join_check_phone.setText("연락처를 입력해주세요!")
                check = check * 0
            } else {
                join_check_phone.visibility = View.VISIBLE
                join_check_phone.setText("유효한 연락처가 아닙니다!")
                check = check * 0
            }


            jsonObject.put("u_school", input_university)
            check = check * 1


            Log.d("*****JoinActivity::json::", jsonObject.toString())

            if(check == 1) {
                ll_join_second.visibility = View.GONE
                ll_join_third.visibility = View.VISIBLE
            }

        }

        btn_join_bank.setOnClickListener {
            ll_join_bank.visibility = View.VISIBLE
        }
        btn_join_bank_kb.setOnClickListener {
            tv_join_bank.setText(btn_join_bank_kb.getText().toString())
            jsonObject.put("u_bank", tv_join_bank.text.toString())
            ll_join_bank.visibility = View.GONE
        }
        btn_join_bank_keb.setOnClickListener {
            tv_join_bank.setText(btn_join_bank_keb.getText().toString())
            jsonObject.put("u_bank", tv_join_bank.text.toString())
            ll_join_bank.visibility = View.GONE
        }
        btn_join_bank_shin.setOnClickListener {
            tv_join_bank.setText(btn_join_bank_shin.getText().toString())
            jsonObject.put("u_bank", tv_join_bank.text.toString())
            ll_join_bank.visibility = View.GONE
        }
        btn_join_bank_wr.setOnClickListener {
            tv_join_bank.setText(btn_join_bank_wr.getText().toString())
            jsonObject.put("u_bank", tv_join_bank.text.toString())
            ll_join_bank.visibility = View.GONE
        }
        btn_join_bank_nh.setOnClickListener {
            tv_join_bank.setText(btn_join_bank_nh.getText().toString())
            jsonObject.put("u_bank", tv_join_bank.text.toString())
            ll_join_bank.visibility = View.GONE
        }
        btn_join_bank_ibk.setOnClickListener {
            tv_join_bank.setText(btn_join_bank_ibk.getText().toString())
            jsonObject.put("u_bank", tv_join_bank.text.toString())
            ll_join_bank.visibility = View.GONE
        }
        btn_join_bank_kdb.setOnClickListener {
            tv_join_bank.setText(btn_join_bank_kdb.getText().toString())
            jsonObject.put("u_bank", tv_join_bank.text.toString())
            ll_join_bank.visibility = View.GONE
        }
        btn_join_bank_ct.setOnClickListener {
            tv_join_bank.setText(btn_join_bank_ct.getText().toString())
            jsonObject.put("u_account", tv_join_bank.text.toString())
            ll_join_bank.visibility = View.GONE
        }
        btn_join_bank_sc.setOnClickListener {
            tv_join_bank.setText(btn_join_bank_sc.getText().toString())
            jsonObject.put("u_account", tv_join_bank.text.toString())
            ll_join_bank.visibility = View.GONE
        }
        btn_join_bank_bmk.setOnClickListener {
            tv_join_bank.setText(btn_join_bank_bmk.getText().toString())
            jsonObject.put("u_bank", tv_join_bank.text.toString())
            ll_join_bank.visibility = View.GONE
        }
        btn_join_bank_dgb.setOnClickListener {
            tv_join_bank.setText(btn_join_bank_dgb.getText().toString())
            jsonObject.put("u_account", tv_join_bank.text.toString())
            ll_join_bank.visibility = View.GONE
        }
        btn_join_bank_bnk.setOnClickListener {
            tv_join_bank.setText(btn_join_bank_bnk.getText().toString())
            jsonObject.put("u_account", tv_join_bank.text.toString())
            ll_join_bank.visibility = View.GONE
        }
        btn_join_bank_sh.setOnClickListener {
            tv_join_bank.setText(btn_join_bank_sh.getText().toString())
            jsonObject.put("u_account", tv_join_bank.text.toString())
            ll_join_bank.visibility = View.GONE
        }
        btn_join_bank_gj.setOnClickListener {
            tv_join_bank.setText(btn_join_bank_gj.getText().toString())
            jsonObject.put("u_bank", tv_join_bank.text.toString())
            ll_join_bank.visibility = View.GONE
        }
        btn_join_bank_jb.setOnClickListener {
            tv_join_bank.setText(btn_join_bank_jb.getText().toString())
            jsonObject.put("u_bank", tv_join_bank.text.toString())
            ll_join_bank.visibility = View.GONE
        }
        btn_join_bank_close.setOnClickListener {
            ll_join_bank.visibility = View.GONE
        }

        btn_join_success.setOnClickListener {

            val input_account: String = et_join_account.text.toString()
            jsonObject.put("u_account", input_account)

            Log.d("*****JoinActivity::json::", jsonObject.toString())

            getJoinResponse()
        }
    }

    private fun getJoinResponse() {

        Log.d("*****JoinActivity::json::", jsonObject.toString())

        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
        Log.d("*****JoinActivity::gson::", gsonObject.toString())

        val postSignUpResponse: Call<PostJoinResponse> = networkService.postJoinResponse("application/json", gsonObject)
        postSignUpResponse.enqueue(object : Callback<PostJoinResponse> {
            override fun onFailure(call: Call<PostJoinResponse>, t: Throwable) {
                Log.e("New_user_Join_Failed", t.toString())
            }

            override fun onResponse(call: Call<PostJoinResponse>, response: Response<PostJoinResponse>) {
                if (response.isSuccessful) {

                    toast(response.body()!!.message)
                    Log.d("*****JoinActivity::", response.body().toString())

                    finish()
                }
            }
        })
    }
}