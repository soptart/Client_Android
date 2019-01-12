package com.artoo.sopt23.artoo_client_android.Activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Adapter.ThemeRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.Data.ProductOverviewData
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetThemeProductResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.ProductData
import com.artoo.sopt23.artoo_client_android.Fragment.HomeThemeFragment
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

        var t_idx : Int = intent.getIntExtra("t_idx",-1)
        var t_mainTag : String = intent.getStringExtra("t_mainTag")
        var t_subTag : String = intent.getStringExtra("t_subTag")

        val mainTag : TextView = findViewById(R.id.tv_activity_theme_title)
        val subTag : TextView = findViewById(R.id.tv_activity_theme_desc)
        //mainTag.text = t_mainTag
        subTag.text = t_subTag
        val img : ImageView = findViewById(R.id.iv_activity_theme_backimg)

        if(t_idx==1){
            img.setImageResource(R.drawable.theme_big_picture_happy)
            mainTag.text = "우리집에 행복을\n가져다주는 작품들"
        }
        if(t_idx==2){
            img.setImageResource(R.drawable.theme_big_picture_sensitive)
            mainTag.text = "카페에 걸어두면\n인스타 갬성카페 등극!"
        }
        if(t_idx==3){
            img.setImageResource(R.drawable.theme_big_picture_fancy)
            mainTag.text = "파티 분위기 UP!\n더신나고 지르고 레릿고"
        }
        if(t_idx==4){
            img.setImageResource(R.drawable.theme_big_picture_cute)
            mainTag.text = "꿍꼬또 꿍꼬또\n귀여운 그림 꿍꼬또"
        }
        if(t_idx==5){
            img.setImageResource(R.drawable.theme_big_picture_simple)
            mainTag.text = "SIMPLE\nIS THE BEST"
        }
        if(t_idx==6){
            img.setImageResource(R.drawable.theme_big_picture_spring)
            mainTag.text = "눈도 마음도\n산뜻하고 따뜻한 봄"
        }
        if(t_idx==7){
            img.setImageResource(R.drawable.theme_big_picture_summer)
            mainTag.text = "보기만해도\n시원해지는 여름"
        }
        if(t_idx==8){
            img.setImageResource(R.drawable.theme_big_picture_fall)
            mainTag.text = "외롭고 쓸쓸하지만\n가을 감성은 놓칠 수 없어"
        }
        if(t_idx==9){
            img.setImageResource(R.drawable.theme_big_picture_winter)
            mainTag.text = "집순이를 위한\n추운 겨울 나기 작품"
        }
        if(t_idx==10){
            img.setImageResource(R.drawable.theme_big_picture_unfathomable)
            mainTag.text="가끔은 심오한\n감수성에 빠져보자"
        }

        setRecyclerView()
        getThemeProductResponse(t_idx)
        setOnBtnClickListener()
    }

    private fun setOnBtnClickListener(){
        iv_activity_theme_close.setOnClickListener {
            try {
                finish()
            } catch (e: Exception) {
            }
        }
    }

    private fun getThemeProductResponse(theme_id:Int){
        val getThemeProductResponse = networkService.getThemeProductResponse(theme_id)
        getThemeProductResponse.enqueue(object: Callback<GetThemeProductResponse>{
            override fun onFailure(call: Call<GetThemeProductResponse>, t:Throwable) {
                Log.e("theme product fail", t.toString())
            }
            override fun onResponse(call: Call<GetThemeProductResponse>, response: Response<GetThemeProductResponse>) {
                if(response.isSuccessful){
                    val temp:ArrayList<ProductData> = response.body()!!.data
                    if(temp.size>0) {
                        val position = themeRecyclerViewAdapter.itemCount
                        themeRecyclerViewAdapter.dataList.addAll(temp)
                        themeRecyclerViewAdapter.notifyItemInserted(position)
                    }
                }
            }
        })
    }
    private fun setRecyclerView(){
        themeRecyclerViewAdapter = ThemeRecyclerViewAdapter(dataList)
        rv_activity_theme_list.adapter = themeRecyclerViewAdapter
        rv_activity_theme_list.layoutManager = GridLayoutManager(this, 3)
    }
}
