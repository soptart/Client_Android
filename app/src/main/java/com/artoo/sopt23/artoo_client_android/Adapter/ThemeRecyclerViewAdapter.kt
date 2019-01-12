package com.artoo.sopt23.artoo_client_android.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.artoo.sopt23.artoo_client_android.Activity.ProductDetailActivity
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetThemeProductResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.ProductData
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThemeRecyclerViewAdapter(val dataList: ArrayList<ProductData>): RecyclerView.Adapter<ThemeRecyclerViewAdapter.Holder>(){

    lateinit var ctx: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        ctx = parent.context
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_theme, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var options: RequestOptions = RequestOptions().centerCrop()

        Glide.with(ctx)
                .load(dataList[position].pic_url)
                .apply(options)
                .into(holder.img_product)

        holder.img_product.setOnClickListener {
            try {
                ctx.startActivity<ProductDetailActivity>("a_idx" to dataList[position].a_idx)
            } catch (e: Exception) {
            }
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val img_product = itemView.findViewById(R.id.iv_rv_item_theme_detail) as ImageView
    }
}