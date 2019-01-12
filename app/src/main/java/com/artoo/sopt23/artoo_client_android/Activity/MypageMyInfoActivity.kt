package com.artoo.sopt23.artoo_client_android.Activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.Data.MypageMyInfoData
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetMypageMyInfoResponse
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.activity_mypage_my_info.*
import org.jetbrains.anko.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageMyInfoActivity : AppCompatActivity() {
    val REQUEST_CODE_MyInfo_MODIFY = 1

    lateinit var btn_my_info_my_name : LinearLayout
    lateinit var btn_my_info_my_email : LinearLayout
    lateinit var btn_my_info_my_pw : LinearLayout
    lateinit var btn_my_info_my_contact : LinearLayout
    lateinit var btn_my_info_my_address : LinearLayout
    lateinit var btn_my_info_my_univ : LinearLayout
    lateinit var btn_my_info_my_account : LinearLayout
    lateinit var btn_my_info_delete : LinearLayout

    var mypageMyInfoData: MypageMyInfoData = MypageMyInfoData(-1, "username@email.com",
        "아투대학교", "010-0000-0000", -1, "서울특별시 성북구 비둘기",
        "홍길동", "신한은행", "110-436-678660", "안녕하세요 아투입니다!")

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_my_info)
        getMypageMyInfo()

        val onClick = View.OnClickListener { v: View? ->
            try {
                val intent = Intent(this@MypageMyInfoActivity, MypageMyInfoModifyActivity::class.java)

                when(v!!.id) {
                    R.id.btn_my_info_my_name -> startActivityForResult<MypageMyInfoModifyActivity>(REQUEST_CODE_MyInfo_MODIFY,"title" to "이름", "key" to "u_name", "value1" to mypageMyInfoData.u_name)
                    R.id.btn_my_info_my_email -> startActivityForResult<MypageMyInfoModifyActivity>(REQUEST_CODE_MyInfo_MODIFY, "title" to "이메일", "key" to "u_email", "value1" to mypageMyInfoData.u_email)
                    R.id.btn_my_info_my_pw -> startActivityForResult<MypageMyInfoModifyActivity>(REQUEST_CODE_MyInfo_MODIFY, "title" to "비밀번호", "key" to "u_pw", "value1" to "", "value2" to "", "value3" to "")
                    R.id.btn_my_info_my_contact -> startActivityForResult<MypageMyInfoModifyActivity>(REQUEST_CODE_MyInfo_MODIFY, "title" to "연락처", "key" to "u_phone", "value1" to mypageMyInfoData.u_phone)
                    // R.id.btn_my_info_my_address -> startActivityForResult<MypageMyInfoModifyActivity>(REQUEST_CODE_MyInfo_MODIFY, "title" to "주소", "key" to "u_address", "value1" to mypageMyInfoData.u_address)
                    R.id.btn_my_info_my_univ -> startActivityForResult<MypageMyInfoModifyActivity>(REQUEST_CODE_MyInfo_MODIFY, "title" to "학교", "key" to "u_school", "value1" to mypageMyInfoData.u_school)
                    R.id.btn_my_info_my_account -> startActivityForResult<MypageMyInfoModifyActivity>(REQUEST_CODE_MyInfo_MODIFY, "title" to "계좌", "key" to "u_account", "value1" to mypageMyInfoData.u_bank, "value2" to mypageMyInfoData.u_account)
                    R.id.btn_my_info_delete -> startActivity<WithdrawalActivity>()
                }
            } catch (e: Exception) {
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

        // btn_my_info_my_address = findViewById(R.id.btn_my_info_my_address)
        // btn_my_info_my_address.setOnClickListener(onClick)

        btn_my_info_my_univ = findViewById(R.id.btn_my_info_my_univ)
        btn_my_info_my_univ.setOnClickListener(onClick)

        btn_my_info_my_account = findViewById(R.id.btn_my_info_my_account)
        btn_my_info_my_account.setOnClickListener(onClick)

        btn_my_info_delete = findViewById(R.id.btn_my_info_delete)
        btn_my_info_delete.setOnClickListener(onClick)
    }

    fun getMypageMyInfo(){
        val getMypageMyInfoData: Call<GetMypageMyInfoResponse> = networkService.getMypageMyInfoResponse(SharedPreferenceController.getAuthorization(this), SharedPreferenceController.getUserID(this))
        getMypageMyInfoData.enqueue(object: Callback<GetMypageMyInfoResponse>{
            override fun onFailure(call: Call<GetMypageMyInfoResponse>, t: Throwable) {
                Log.i("MypageMyInfoActivity", "Get Connection Failed")
            }

            override fun onResponse(call: Call<GetMypageMyInfoResponse>, response: Response<GetMypageMyInfoResponse>) {
                if(response.isSuccessful){
                    mypageMyInfoData = response.body()!!.data
                    txt_my_info_my_name.text = mypageMyInfoData.u_name
                    txt_my_info_my_email.text = mypageMyInfoData.u_email
                    txt_my_info_my_contact.text = mypageMyInfoData.u_phone
                    // txt_my_info_my_address.text = mypageMyInfoData.u_address
                    txt_my_info_my_univ.text = mypageMyInfoData.u_school
                    txt_my_info_my_account.text = mypageMyInfoData.u_bank + " " + mypageMyInfoData.u_account
                }
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_MyInfo_MODIFY){
            if(resultCode == Activity.RESULT_OK) getMypageMyInfo()
        }
    }
}