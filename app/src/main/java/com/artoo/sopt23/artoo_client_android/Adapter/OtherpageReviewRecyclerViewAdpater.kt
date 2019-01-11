package com.artoo.sopt23.artoo_client_android.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Data.OtherpageReviewData
import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class OtherpageReviewRecyclerViewAdapter(val dataList: ArrayList<OtherpageReviewData>): RecyclerView.Adapter<OtherpageReviewRecyclerViewAdapter.Holder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val view: View = LayoutInflater.from(context).inflate(R.layout.rv_item_mypage_review, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var options: RequestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(50))

        Glide.with(context)
                .load(dataList[position].a_pic_url)
                .apply(options)
                .into(holder.product_img)

        holder.product_title.text = dataList[position].a_name
        holder.product_buyer.text = dataList[position].u_name
        holder.content.text = dataList[position].p_comment
        holder.time.text = dataList[position].p_date
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val product_title: TextView = itemView.findViewById(R.id.tv_mypage_review_product_title) as TextView
        val product_buyer: TextView = itemView.findViewById(R.id.tv_mypage_review_buyer) as TextView
        val product_img: ImageView = itemView.findViewById(R.id.iv_mypage_review_product_image)
        val content: TextView = itemView.findViewById(R.id.tv_mypage_review_content) as TextView
        val time: TextView = itemView.findViewById(R.id.tv_mypage_review_time) as TextView
    }
}
