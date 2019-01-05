package com.artoo.sopt23.artoo_client_android.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetThemesResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.ThemeListData
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.ThemeTagData
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeThemeRecyclerViewAdapter(val dataList: ArrayList<ThemeListData>): RecyclerView.Adapter<HomeThemeRecyclerViewAdapter.Holder>(){
    lateinit var ctx: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        ctx = parent.context
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_home_theme, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
                .load(dataList[position].pic_url)
                .into(holder.img_product)
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val img_product: ImageView = itemView.findViewById(R.id.img_rv_item_home_theme)
    }
}