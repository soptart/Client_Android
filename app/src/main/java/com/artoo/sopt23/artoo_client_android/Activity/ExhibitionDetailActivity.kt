package com.artoo.sopt23.artoo_client_android.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Adapter.ExhibitionDetailAdapter
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.Data.ExhibitionDetailData
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetDetailExhibitionResponse
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_exhibition_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExhibitionDetailActivity : AppCompatActivity() {

    lateinit var exhibitionDetailAdapter: ExhibitionDetailAdapter

    lateinit var getDetailExhibition : ArrayList<ExhibitionDetailData>

    var user_idx = -1
    var user_token = ""


    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exhibition_detail)

        user_idx = SharedPreferenceController.getUserID(this@ExhibitionDetailActivity)
        user_token = SharedPreferenceController.getAuthorization(this@ExhibitionDetailActivity)

        val txt_ex_current_num : TextView = findViewById(R.id.txt_ex_current_num)

//        if(intent.hasExtra("current")) {
//            txt_ex_current_num.text = intent.getStringExtra("current")
//            Log.d("current num test : ", intent.getStringExtra("current"))
//        }

        btn_exhibition_exit.setOnClickListener {
            finish()
        }

        val d_idx : Int = intent.getIntExtra("d_idx", -1)
        Log.d("d_idx", d_idx.toString())


        var snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rv_ex_detail)

        if(intent.hasExtra("d_titleImg_url")) {
            Glide.with(this).load(intent.getStringExtra("d_titleImg_url")).into(iv_detail_ex_title)

        }



        getDetailExhibition(d_idx)

    }

    fun getDetailExhibition(d_idx : Int) {
        val getDetailExhibitionResponse = networkService.getDetailExhibitionResponse(user_token,d_idx)
        getDetailExhibitionResponse.enqueue(object :  Callback<GetDetailExhibitionResponse> {
            override fun onFailure(call: Call<GetDetailExhibitionResponse>?, t: Throwable?) {
                Log.d("Detail_Exhibition", "Detail Exhibition Fail")
            }

            override fun onResponse(call: Call<GetDetailExhibitionResponse>?, response: Response<GetDetailExhibitionResponse>?) {

                if(response!!.isSuccessful) {
                    getDetailExhibition = ArrayList<ExhibitionDetailData>()

                    getDetailExhibition = response.body()!!.data

                    txt_ex_all_num.text = response.body()!!.data.size.toString()


                    exhibitionDetailAdapter = ExhibitionDetailAdapter(getDetailExhibition)
                    rv_ex_detail.adapter = exhibitionDetailAdapter
                    rv_ex_detail.layoutManager = LinearLayoutManager(this@ExhibitionDetailActivity,LinearLayoutManager.HORIZONTAL,false)

                }

            }

        })
    }

}
