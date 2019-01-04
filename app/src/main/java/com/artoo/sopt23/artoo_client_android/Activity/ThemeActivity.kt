package com.artoo.sopt23.artoo_client_android.Activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.artoo.sopt23.artoo_client_android.Adapter.ThemeRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.Data.ProductOverviewData
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetThemeProductResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.ProductData
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_theme.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThemeActivity : AppCompatActivity() {

    val jsonObject = JSONObject()
    lateinit var themeRecyclerViewAdapter: ThemeRecyclerViewAdapter
    val dataList:ArrayList<ProductData> by lazy{
        ArrayList<ProductData>()
    }
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme)

        setOnBtnClickListener()

        setRecyclerView()

        var t_idx = intent.getIntExtra("t_idx",-1)
        getThemeProductResponse(1)
    }

    private fun setOnBtnClickListener(){
        iv_activity_theme_close.setOnClickListener {
            finish()
        }
    }
    private fun setRecyclerView(){
        themeRecyclerViewAdapter = ThemeRecyclerViewAdapter(dataList)
        rv_activity_theme_list.adapter = themeRecyclerViewAdapter
        rv_activity_theme_list.layoutManager = GridLayoutManager(this, 3)
    }

    private fun getThemeProductResponse(theme_id:Int){
        val getThemeProductResponse = networkService.getThemeProductResponse()
        getThemeProductResponse.enqueue(object: Callback<GetThemeProductResponse>{
            override fun onFailure(call: Call<GetThemeProductResponse>, t:Throwable) {
                Log.e("theme product fail", t.toString())
            }

            override fun onResponse(call: Call<GetThemeProductResponse>, response: Response<GetThemeProductResponse>) {
                if(response.isSuccessful){
                    val temp:ArrayList<ProductData> = response.body()!!.data
                    if(temp.size>0){
                        val position = themeRecyclerViewAdapter.itemCount
                        themeRecyclerViewAdapter.dataList.addAll(temp)
                        themeRecyclerViewAdapter.notifyItemInserted(position)
                    }
                }
            }
        })
    }
}
