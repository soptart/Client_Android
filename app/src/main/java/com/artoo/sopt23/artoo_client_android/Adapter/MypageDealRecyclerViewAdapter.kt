package com.artoo.sopt23.artoo_client_android.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Data.MypageDealData
import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class MypageDealRecyclerViewAdapter(val dataList: ArrayList<MypageDealData>): RecyclerView.Adapter<MypageDealRecyclerViewAdapter.Holder>(){
    lateinit var ctx: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        ctx = parent.context
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_mypage_deal, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var options: RequestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(20))

        Glide.with(ctx)
            .load(dataList[position].a_pic_url)
            .apply(options)
            .into(holder.product_img)

        holder.product_title.text = dataList[position].a_name
        if (dataList[position].buyer) {
            holder.tv_product_buyer.text = "판매자: "
        }
        holder.product_buyer.text = dataList[position].u_name
        var product_price = String.format("%,d", dataList[position].a_price)
        holder.product_price.text = product_price + "원"
        holder.time.text = dataList[position].p_date

        if (dataList[position].p_state != 10 && dataList[position].p_state != 20 && dataList[position].buyer) {
            holder.status_img.setImageResource(R.drawable.my_payment_complete)
        }
        else if (dataList[position].p_state != 10 && dataList[position].p_state != 20 && !dataList[position].buyer) {
            holder.status_img.setImageResource(R.drawable.my_sale_complete)
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){

        val product_img: ImageView = itemView.findViewById(R.id.iv_mypage_deal_product_image)
        val product_title: TextView = itemView.findViewById(R.id.tv_mypage_deal_product_title)
        val product_buyer: TextView = itemView.findViewById(R.id.tv_mypage_deal_product_buyer)
        val product_price: TextView = itemView.findViewById(R.id.tv_mypage_deal_product_price)
        val time: TextView = itemView.findViewById(R.id.tv_mypage_deal_time)
        val status_img: ImageView = itemView.findViewById(R.id.iv_mypage_deal_status)

        val tv_product_buyer: TextView = itemView.findViewById(R.id.tv_mypage_deal_left1)
    }
}