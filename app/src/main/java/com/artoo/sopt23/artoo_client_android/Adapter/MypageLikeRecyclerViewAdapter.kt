package com.artoo.sopt23.artoo_client_android.Adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Activity.ProductDetailActivity
import com.artoo.sopt23.artoo_client_android.Data.MypageLikeData
import com.artoo.sopt23.artoo_client_android.Data.MypageProductData
import com.artoo.sopt23.artoo_client_android.Data.MypageReviewData
import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import org.jetbrains.anko.*

class MypageLikeRecyclerViewAdapter(var dataList: ArrayList<MypageLikeData>): RecyclerView.Adapter<MypageLikeRecyclerViewAdapter.Holder>(){
    lateinit var ctx: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        ctx = parent.context
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_mypage_product, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        var options: RequestOptions = RequestOptions().placeholder(R.drawable.placeholder)
        Glide.with(ctx)
            .load(dataList[position].a_url)
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
        val img_product = itemView.findViewById(R.id.img_rv_item_mypage_product) as ImageView
    }
}
