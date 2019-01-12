package com.artoo.sopt23.artoo_client_android.Activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Adapter.ApplyExMyProductAdapter
import com.artoo.sopt23.artoo_client_android.Adapter.ApplyExhibitionAdapter
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.Data.ApplyExhibitionData
import com.artoo.sopt23.artoo_client_android.Data.ExhibitionArtworkData
import com.artoo.sopt23.artoo_client_android.Data.ExhibitionDisplayData
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetApplyExhibitionResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Post.PostApplyExhibitionResponse
import com.artoo.sopt23.artoo_client_android.DialogApplyExhibitionActivity
import com.artoo.sopt23.artoo_client_android.DialogApplyExhibitionButtonActivity
import com.artoo.sopt23.artoo_client_android.DialogFailApplyActivity
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.activity_apply_exhibition.*
import org.jetbrains.anko.sdk25.coroutines.onTouch
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplyExhibitionActivity : AppCompatActivity() {

    var a_idx: Int = -1
    var d_idx: Int = -1

    var a_idx_check : Boolean = false
    var d_idx_check : Boolean = false

    var u_Name : String =""
    var a_Name : String =""
    var d_title : String=""
    var d_sub_title : String =""

    companion object {
        lateinit var instance: ApplyExhibitionActivity
    }

    var applyExMyProductAdapter: ApplyExMyProductAdapter? = null
    var applyExhibitionAdapter: ApplyExhibitionAdapter? = null

    lateinit var getDisplayExhibition: ArrayList<ExhibitionDisplayData>
    lateinit var getArtworkExhibition: ArrayList<ExhibitionArtworkData>

    lateinit var btn_apply_exhibition : TextView

    var user_idx = -1
    var user_token = ""



    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_exhibition)

        btn_apply_exhibition = findViewById(R.id.btn_apply_exhibition)

        user_idx = SharedPreferenceController.getUserID(this@ApplyExhibitionActivity)
        user_token = SharedPreferenceController.getAuthorization(this@ApplyExhibitionActivity)

        instance = this


        // 전시 신청서 뷰 보여주기
        getApplyExhibitionView(user_token, user_idx)
        Log.d("user_idx", user_idx.toString())


        // 신청하기 버튼을 누르면 post

        if(btn_apply_exhibition.isClickable == false) {
            btn_apply_exhibition.onTouch { v, event ->
                failBtn()
            }
        }

        btn_apply_exhibition.setOnClickListener {

            try {
                Log.d("test d_idx", d_idx.toString())
                Log.d("test a_idx", a_idx.toString())

                postApplyExhibition()
            } catch (e: Exception) {
            }
        }

        Check()
    }

    // 전시 신청서 GET 통신

    fun getApplyExhibitionView(user_token: String, user_idx: Int) {


        val getApplyExhibitionResponse = networkService.getApplyExhibitionResponse(user_token, user_idx)
        getApplyExhibitionResponse.enqueue(object : Callback<GetApplyExhibitionResponse> {
            override fun onFailure(call: Call<GetApplyExhibitionResponse>?, t: Throwable?) {
                Log.d("getApplyExhibition", "apply Exhibition View fail")
            }

            override fun onResponse(call: Call<GetApplyExhibitionResponse>?, response: Response<GetApplyExhibitionResponse>?) {
                if (response!!.isSuccessful) {
                    Log.d("asdf", response.body()!!.toString())

//                    Check(a_idx, d_idx)

                    // 통신 성공시 display 뿌려줌
                    getDisplayExhibition = ArrayList<ExhibitionDisplayData>()
                    getArtworkExhibition = ArrayList<ExhibitionArtworkData>()


                    ex_now_s_date.setText(response.body()!!.data.displays[0].d_sDateNow)
                    ex_now_e_date.setText(response.body()!!.data.displays[0].d_eDateNow)
                    ex_apply_s_date.setText(response.body()!!.data.displays[0].d_sDateApply)
                    ex_apply_e_date.setText(response.body()!!.data.displays[0].d_eDateApply)

                    getDisplayExhibition = response.body()!!.data.displays
                    Log.d("e_network", response.body()!!.data.displays.size.toString())

                    applyExhibitionAdapter = ApplyExhibitionAdapter(getDisplayExhibition)
                    rv_apply_ex_radio.adapter = applyExhibitionAdapter
                    rv_apply_ex_radio.layoutManager = LinearLayoutManager(this@ApplyExhibitionActivity)


                    // 만약 내 작품이 없을 경우 작품 업로드 텍스트 뿌려줌
                    if (response.body()!!.data.artworks.size == 0) {

                        Log.d("exhibition 404", response.body()!!.data.artworks.size.toString())
                        exhibition_404.visibility = View.VISIBLE
                        rv_apply_ex_my_product.visibility = View.GONE

                    } else {

                        getArtworkExhibition = response.body()!!.data.artworks
                        Log.d("e_network", response.body()!!.data.artworks.size.toString())


                        applyExMyProductAdapter = ApplyExMyProductAdapter(getArtworkExhibition)
                        rv_apply_ex_my_product.adapter = applyExMyProductAdapter
                        rv_apply_ex_my_product.layoutManager = LinearLayoutManager(this@ApplyExhibitionActivity, LinearLayoutManager.HORIZONTAL, false)
                    }


                }
            }

        })
    }

    //전시 신청하기 POST 통신

    fun postApplyExhibition() {

        var applyExhibition = ApplyExhibitionData(a_idx, d_idx, user_idx)
        val postApplyExhibitionResponse = networkService.postApplyExhibitionResponse(user_token, user_idx, applyExhibition)
        postApplyExhibitionResponse.enqueue(object : Callback<PostApplyExhibitionResponse> {
            override fun onFailure(call: Call<PostApplyExhibitionResponse>?, t: Throwable?) {
                Log.d("fail", "why...!!!!")
                failCreate()
            }

            override fun onResponse(call: Call<PostApplyExhibitionResponse>?, response: Response<PostApplyExhibitionResponse>?) {
                Log.d("why", "why...!!!!")

                if (response!!.isSuccessful) {

                    // 전시 신청 성공
                    Log.d("post test", "successful")

                    u_Name = response.body()!!.data.uName
                    a_Name = response.body()!!.data.aName
                    d_title = response.body()!!.data.dTitle
                    d_sub_title = response.body()!!.data.dSubTitle

                    successfulDialog()


                }
            }

        })
    }

    // 다이얼로그 띄우기
    fun successfulDialog() {

        var applyDialog = DialogApplyExhibitionActivity(this)
        applyDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        applyDialog.setCanceledOnTouchOutside(false)
        applyDialog.show()

        applyDialog.setOnDismissListener {
            finish()
        }

    }

    fun failCreate() {
        var oopsDialog = DialogFailApplyActivity(this)
        oopsDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        oopsDialog.setCanceledOnTouchOutside(false)
        oopsDialog.show()
    }

    fun failBtn() {
        // 조건
        if(btn_apply_exhibition.isClickable == false) {
            var oopsBtnDialog = DialogApplyExhibitionButtonActivity(this)
            oopsBtnDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            oopsBtnDialog.setCanceledOnTouchOutside(false)
            oopsBtnDialog.show()
        }

    }

    //     버튼 활성/ 비활성화 검사
    fun Check () {


        if(a_idx_check == true && d_idx_check == true) {
            btn_apply_exhibition.isClickable = true
            btn_apply_exhibition.setTextColor(Color.parseColor("#ffffff"))
        } else {
            btn_apply_exhibition.isClickable = false
            btn_apply_exhibition.setTextColor(Color.parseColor("#bababa"))


        }

    }

}