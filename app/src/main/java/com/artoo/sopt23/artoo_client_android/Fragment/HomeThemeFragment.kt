package com.artoo.sopt23.artoo_client_android.Fragment

import android.content.Context
import android.content.Intent
import android.graphics.BlurMaskFilter
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artoo.sopt23.artoo_client_android.Adapter.HomeThemeRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Data.ProductOverviewData
import com.artoo.sopt23.artoo_client_android.Data.ThemeData

import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import kotlinx.android.synthetic.main.fragment_home_theme.*
import kotlinx.android.synthetic.main.rv_item_home_theme.*
import org.jetbrains.anko.coroutines.experimental.asReference
import kotlin.collections.ArrayList
import android.os.Build
import com.bumptech.glide.request.transition.Transition
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.util.Log
import android.view.animation.Transformation
import com.artoo.sopt23.artoo_client_android.Activity.ThemeActivity
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetThemesResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.ProductData
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.ThemeListData
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.ThemeTagData
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.request.Request
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_home_theme.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeThemeFragment : Fragment() {
    lateinit var homeThemeRecyclerViewAdapter: HomeThemeRecyclerViewAdapter
    lateinit var themeTagData: ArrayList<ThemeTagData>
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater!!.inflate(R.layout.fragment_home_theme, container, false)
        getThemesResponse()

        return view
    }

    private fun getThemesResponse(){
        val getThemesResponse = networkService.getThemesResponse()
        getThemesResponse.enqueue(object: Callback<GetThemesResponse> {
            override fun onFailure(call: Call<GetThemesResponse>, t:Throwable) {
                Log.e("themes fail", t.toString())
            }
            override fun onResponse(call: Call<GetThemesResponse>, response: Response<GetThemesResponse>) {
                if(response.isSuccessful){
                    themeTagData = response.body()!!.data
                    setRecyclerView()
                    setOnClickListener()
                }

            }
        })
    }

    private fun setRecyclerView() {
        Log.v("HomeThemeFragment::setRecyclerView", themeTagData.toString())
        var themeListData:ArrayList<ThemeListData> = themeTagData[0].list
        homeThemeRecyclerViewAdapter = HomeThemeRecyclerViewAdapter(themeListData)
        rv_home_theme_recommend_product.adapter = homeThemeRecyclerViewAdapter
        rv_home_theme_recommend_product.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setOnClickListener(){
        var intent = Intent(context, ThemeActivity::class.java)
        iv_home_theme_theme_category_0.setOnClickListener {
            try {
                intent.putExtra("t_idx",themeTagData[0].t_idx)
                intent.putExtra("t_mainTag",themeTagData[0].t_mainTag)
                intent.putExtra("t_subTag",themeTagData[0].t_subTag)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
        iv_home_theme_theme_category_1.setOnClickListener {
            try {
                intent.putExtra("t_idx",themeTagData[1].t_idx)
                intent.putExtra("t_mainTag",themeTagData[1].t_mainTag)
                intent.putExtra("t_subTag",themeTagData[1].t_subTag)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
        iv_home_theme_theme_category_2.setOnClickListener {
            try {
                intent.putExtra("t_idx",themeTagData[2].t_idx)
                intent.putExtra("t_mainTag",themeTagData[2].t_mainTag)
                intent.putExtra("t_subTag",themeTagData[2].t_subTag)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
        iv_home_theme_theme_category_3.setOnClickListener {
            try {
                intent.putExtra("t_idx",themeTagData[3].t_idx)
                intent.putExtra("t_mainTag",themeTagData[3].t_mainTag)
                intent.putExtra("t_subTag",themeTagData[3].t_subTag)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
        iv_home_theme_theme_category_4.setOnClickListener {
            try {
                intent.putExtra("t_idx",themeTagData[4].t_idx)
                intent.putExtra("t_mainTag",themeTagData[4].t_mainTag)
                intent.putExtra("t_subTag",themeTagData[4].t_subTag)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
        iv_home_theme_theme_category_5.setOnClickListener {
            try {
                intent.putExtra("t_idx",themeTagData[9].t_idx)
                intent.putExtra("t_mainTag",themeTagData[9].t_mainTag)
                intent.putExtra("t_subTag",themeTagData[9].t_subTag)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
        iv_home_theme_theme_category_6.setOnClickListener {
            try {
                intent.putExtra("t_idx",themeTagData[5].t_idx)
                intent.putExtra("t_mainTag",themeTagData[5].t_mainTag)
                intent.putExtra("t_subTag",themeTagData[5].t_subTag)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
        iv_home_theme_theme_category_7.setOnClickListener {
            try {
                intent.putExtra("t_idx",themeTagData[6].t_idx)
                intent.putExtra("t_mainTag",themeTagData[6].t_mainTag)
                intent.putExtra("t_subTag",themeTagData[6].t_subTag)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
        iv_home_theme_theme_category_8.setOnClickListener {
            try {
                intent.putExtra("t_idx",themeTagData[7].t_idx)
                intent.putExtra("t_mainTag",themeTagData[7].t_mainTag)
                intent.putExtra("t_subTag",themeTagData[7].t_subTag)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
        iv_home_theme_theme_category_9.setOnClickListener {
            try {
                startActivity(intent)
                intent.putExtra("t_idx",themeTagData[8].t_idx)
                intent.putExtra("t_mainTag",themeTagData[8].t_mainTag)
                intent.putExtra("t_subTag",themeTagData[8].t_subTag)
            } catch (e: Exception) {
            }
        }
        iv_home_theme_recommend_all.setOnClickListener{
            try {
                intent.putExtra("t_idx",themeTagData[0].t_idx)
                intent.putExtra("t_mainTag",themeTagData[0].t_mainTag)
                intent.putExtra("t_subTag",themeTagData[0].t_subTag)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
        iv_home_theme_theme_title_1.setOnClickListener{
            try {
                intent.putExtra("t_idx",themeTagData[1].t_idx)
                intent.putExtra("t_mainTag",themeTagData[1].t_mainTag)
                intent.putExtra("t_subTag",themeTagData[1].t_subTag)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
        iv_home_theme_theme_title_2.setOnClickListener{
            try {
                intent.putExtra("t_idx",themeTagData[2].t_idx)
                intent.putExtra("t_mainTag",themeTagData[2].t_mainTag)
                intent.putExtra("t_subTag",themeTagData[2].t_subTag)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
        iv_home_theme_theme_title_3.setOnClickListener{
            try {
                intent.putExtra("t_idx",themeTagData[3].t_idx)
                intent.putExtra("t_mainTag",themeTagData[3].t_mainTag)
                intent.putExtra("t_subTag",themeTagData[3].t_subTag)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
        iv_home_theme_theme_title_4.setOnClickListener{
            try {
                intent.putExtra("t_idx",themeTagData[4].t_idx)
                intent.putExtra("t_mainTag",themeTagData[4].t_mainTag)
                intent.putExtra("t_subTag",themeTagData[4].t_subTag)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
        iv_home_theme_theme_title_5.setOnClickListener{
            try {
                intent.putExtra("t_idx",themeTagData[9].t_idx)
                intent.putExtra("t_mainTag",themeTagData[9].t_mainTag)
                intent.putExtra("t_subTag",themeTagData[9].t_subTag)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
        iv_home_theme_theme_title_6.setOnClickListener{
            try {
                intent.putExtra("t_idx",themeTagData[5].t_idx)
                intent.putExtra("t_mainTag",themeTagData[5].t_mainTag)
                intent.putExtra("t_subTag",themeTagData[5].t_subTag)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
        iv_home_theme_theme_title_7.setOnClickListener{
            try {
                intent.putExtra("t_idx",themeTagData[6].t_idx)
                intent.putExtra("t_mainTag",themeTagData[6].t_mainTag)
                intent.putExtra("t_subTag",themeTagData[6].t_subTag)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
        iv_home_theme_theme_title_8.setOnClickListener{
            try {
                intent.putExtra("t_idx",themeTagData[7].t_idx)
                intent.putExtra("t_mainTag",themeTagData[7].t_mainTag)
                intent.putExtra("t_subTag",themeTagData[7].t_subTag)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
        iv_home_theme_theme_title_9.setOnClickListener{
            try {
                intent.putExtra("t_idx",themeTagData[8].t_idx)
                intent.putExtra("t_mainTag",themeTagData[8].t_mainTag)
                intent.putExtra("t_subTag",themeTagData[8].t_subTag)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
    }
}
