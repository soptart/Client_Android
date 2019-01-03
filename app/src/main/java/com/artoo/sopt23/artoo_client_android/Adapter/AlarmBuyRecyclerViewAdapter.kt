package com.artoo.sopt23.artoo_client_android.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Data.AlarmBuyData
import com.artoo.sopt23.artoo_client_android.Fragment.AlarmCommentDialogFragment
import com.artoo.sopt23.artoo_client_android.Fragment.AlarmRefundDialogFragment
import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import android.support.v4.app.FragmentActivity
import com.bumptech.glide.request.RequestOptions


class AlarmBuyRecyclerViewAdapter(val ctx: Context, val dataListBuy: ArrayList<AlarmBuyData>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemViewType(position: Int): Int {
        if(dataListBuy[position].type == false){
            return 0
        }
        else {
            return 1
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var options: RequestOptions = RequestOptions().centerCrop()
        if(dataListBuy[position].type == false)
        {
            (holder as HolderDirect).date.text = dataListBuy[position].date
            Glide.with(ctx)
                    .load(dataListBuy[position].img_url)
                    .apply(options)
                    .into((holder).img_url)
            (holder).title.text = dataListBuy[position].title
            (holder).artist.text = dataListBuy[position].artist
            (holder).seller_name.text = dataListBuy[position].seller_name
            (holder).seller_number.text = dataListBuy[position].seller_number
            (holder).seller_address.text = dataListBuy[position].seller_address
            (holder).btn_comment.setOnClickListener {
                val comment_dialog = AlarmCommentDialogFragment()
                comment_dialog.show((ctx as FragmentActivity).supportFragmentManager, comment_dialog.tag)
            }
        }
        else {
            (holder as HolderDelivery).date.text = dataListBuy[position].date
            Glide.with(ctx)
                    .load(dataListBuy[position].img_url)
                    .apply(options)
                    .into((holder).img_url)
            (holder).title.text = dataListBuy[position].title
            (holder).artist.text = dataListBuy[position].artist
            (holder).btn_refund.setOnClickListener {
                val refund_dialog = AlarmRefundDialogFragment()
                refund_dialog.show((ctx as FragmentActivity).supportFragmentManager, refund_dialog.tag)
            }
            (holder).btn_comment.setOnClickListener {
                val comment_dialog = AlarmCommentDialogFragment()
                comment_dialog.show((ctx as FragmentActivity).supportFragmentManager, comment_dialog.tag)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 0){
            val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_alarm_buy_direct, parent, false)
            return HolderDirect(view)
        }
        else{
            val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_alarm_buy_delivery, parent, false)
            return HolderDelivery(view)
        }
    }

    override fun getItemCount(): Int = dataListBuy.size

    inner class HolderDirect(itemView: View): RecyclerView.ViewHolder(itemView){
        val date : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_direct_date) as TextView
        val img_url : ImageView = itemView.findViewById(R.id.iv_rv_item_alarm_buy_direct_product) as ImageView
        val title : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_direct_title) as TextView
        val artist : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_direct_artist) as TextView
        val seller_name : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_direct_seller_name) as TextView
        val seller_number : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_direct_seller_number) as TextView
        val seller_address : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_direct_seller_address) as TextView
        val btn_comment:Button = itemView.findViewById(R.id.btn_rv_item_alarm_buy_direct_comment) as Button
    }

    inner class HolderDelivery(itemView: View): RecyclerView.ViewHolder(itemView){
        val date : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_delivery_date) as TextView
        val img_url : ImageView = itemView.findViewById(R.id.iv_rv_item_alarm_buy_delivery_product) as ImageView
        val title : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_delivery_title) as TextView
        val artist : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_delivery_artist) as TextView
        val btn_refund:Button = itemView.findViewById(R.id.btn_rv_item_alarm_buy_delivery_refund) as Button
        val btn_comment:Button = itemView.findViewById(R.id.btn_rv_item_alarm_buy_delivery_comment) as Button
    }
}