package com.artoo.sopt23.artoo_client_android.Adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Activity.OtherUserPageActivity
import com.artoo.sopt23.artoo_client_android.Activity.ProductDetailActivity
import com.artoo.sopt23.artoo_client_android.Data.TodayArtistProductData
import com.artoo.sopt23.artoo_client_android.Data.TodayMainData
import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import org.jetbrains.anko.*

class HomeTodayRecyclerViewAdapter(val ctx: Context, var dataMain: TodayMainData, var dataListArtistProduct:ArrayList<TodayArtistProductData>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 0){
            Log.i("hitag","0")
            val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_home_today_artist_main, parent, false)
            return HolderMain(view)
        }
        else{
            Log.i("hitag","1")
            val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_home_today_artist, parent, false)
            return HolderArtist(view)
        }

    }

    override fun getItemCount(): Int = 1 + dataListArtistProduct.size

    override fun getItemViewType(position: Int): Int {
        if(position == 0){
            return 0
        }
        else {
            return 1
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(position == 0)
        {
            (holder as HolderMain).university.text = dataMain.university
            (holder).name.text = dataMain.name
            (holder).intro.text = dataMain.intro
            var options: RequestOptions = RequestOptions().priority(Priority.HIGH).placeholder(R.drawable.placeholder).centerCrop().diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            Glide.with(ctx)
                .load(dataMain.back_img_url)
                .apply(options)
                .into((holder).backimg)
            (holder).backimg.setOnClickListener {
                try {
                    ctx.startActivity<OtherUserPageActivity>("other_idx" to dataMain.u_idx)
                } catch (e: Exception) {
                }
            }
        } else {
            (holder as HolderArtist).title.text = dataListArtistProduct[position-1].a_name
            Glide.with(ctx)
                .load(dataListArtistProduct[position-1].pic_url)
                .into((holder).img)
            (holder as HolderArtist).img.setOnClickListener {
                try {
                    ctx.startActivity<ProductDetailActivity>("a_idx" to dataListArtistProduct[position - 1].a_idx)
                } catch (e: Exception) {
                }
            }
        }
    }

    inner class HolderMain(itemView: View): RecyclerView.ViewHolder(itemView){
        val university: TextView = itemView.findViewById(R.id.tv_rv_item_home_today_artist_main_university) as TextView
        val name: TextView = itemView.findViewById(R.id.tv_rv_item_home_today_artist_main_name) as TextView
        val intro: TextView = itemView.findViewById(R.id.tv_rv_item_home_today_artist_main_introduction) as TextView
        val backimg: ImageView = itemView.findViewById(R.id.iv_rv_item_home_today_artist_main_product) as ImageView
    }

    inner class HolderArtist(itemView: View): RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.tv_rv_item_home_today_artist_name) as TextView
        val img: ImageView = itemView.findViewById(R.id.iv_rv_item_home_today_artist_product) as ImageView
    }
}