package com.artoo.sopt23.artoo_client_android.Adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
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
import android.support.v4.app.FragmentManager
import android.util.Log
import android.widget.ImageButton
import com.artoo.sopt23.artoo_client_android.Activity.AlarmActivity
import com.artoo.sopt23.artoo_client_android.Fragment.AlarmBuyFragment
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import org.jetbrains.anko.backgroundDrawable
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.find


class AlarmBuyRecyclerViewAdapter(val ctx: Context, var dataListBuy: ArrayList<AlarmBuyData>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemViewType(position: Int): Int {
        if(dataListBuy[position].p_isPay == 0 && dataListBuy[position].p_isDelivery == 0) {
            return 1  // 결제전 직거래
        }
        else if(dataListBuy[position].p_isPay == 0 && dataListBuy[position].p_isDelivery == 1) {
            return 2  // 결제전 택배
        }
        else if(dataListBuy[position].p_isPay == 1 && dataListBuy[position].p_isDelivery == 0) {
            return 3  // 결제완료 직거래
        }
        else if(dataListBuy[position].p_isPay ==1 && dataListBuy[position].p_isDelivery == 1){
            return 4 // 결제완료 택배
        }
        else{
            Log.i("***alarmBuyRecyclerViewAdapter::getItemViewType***","")
            return -1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 1){
            val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_alarm_buy_pay_direct, parent, false)
            return HolderPayDirect(view)
        }
        else if(viewType == 2){
            val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_alarm_buy_pay_delivery, parent, false)
            return HolderPayDelivery(view)
        }
        else if(viewType == 3){
            val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_alarm_buy_direct, parent, false)
            return HolderDirect(view)
        }
        else if(viewType == 4 ){
            val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_alarm_buy_delivery, parent, false)
            return HolderDelivery(view)
        }
        else{
            return error(Log.d("***alarmBuyRecyclerViewAdapter::onCreatViewHolder***",""))
        }
    }

    override fun getItemCount(): Int = dataListBuy.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var options: RequestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(20))

        // 결제전 직거래
        if(getItemViewType(position) == 1) {
            (holder as HolderPayDirect).date.text = dataListBuy[position].p_date
            Glide.with(ctx)
                .load(dataListBuy[position].a_pic_url)
                .apply(options)
                .into((holder).img_url)
            (holder).title.text = dataListBuy[position].a_name
            (holder).artist.text = dataListBuy[position].a_u_name
            val product_price_s:String = String.format("%,d",dataListBuy[position].a_price)
            (holder).price.text = product_price_s+"원"
            (holder).bank_account.text = dataListBuy[position].u_bank+" "+dataListBuy[position].u_account+" "+dataListBuy[position].u_name
        }

        // 결제전 택배
        else if(getItemViewType(position) == 2) {
            (holder as HolderPayDelivery).date.text = dataListBuy[position].p_date
            Glide.with(ctx)
                .load(dataListBuy[position].a_pic_url)
                .apply(options)
                .into((holder).img_url)
            (holder).title.text = dataListBuy[position].a_name
            (holder).artist.text = dataListBuy[position].a_u_name
            val product_price_s: String = String.format("%,d", dataListBuy[position].a_price)
            (holder).price.text = product_price_s + "원"
            (holder).bank_account.text = dataListBuy[position].u_bank + " " + dataListBuy[position].u_account + " " + dataListBuy[position].u_name
        }

        //결제완료 직거래
        else if(getItemViewType(position) == 3){
            (holder as HolderDirect).date.text = dataListBuy[position].p_date
            Glide.with(ctx)
                .load(dataListBuy[position].a_pic_url)
                .apply(options)
                .into((holder).img_url)
            (holder).title.text = dataListBuy[position].a_name
            (holder).artist.text = dataListBuy[position].a_u_name
            (holder).seller_name.text = dataListBuy[position].u_name
            (holder).seller_number.text = dataListBuy[position].u_phone
            (holder).seller_address.text = dataListBuy[position].u_address
            (holder).btn_comment.setOnClickListener {
                try {
                    val comment_dialog = AlarmCommentDialogFragment()
                    comment_dialog.show((ctx as AlarmActivity).supportFragmentManager, comment_dialog.tag)

                    AlarmBuyFragment.instance.p_idx = dataListBuy[position].p_idx
                } catch (e: Exception) {
                }
            }
            Log.d("*****AlarmBuyRecyclerViewAdapter::결제완료 직거래::", dataListBuy[position].c_isComment.toString())
            if (dataListBuy[position].c_isComment) {
                (holder).btn_direct_review_inactive.isEnabled = false
                (holder).btn_direct_review_inactive.setBackgroundResource(R.drawable.alarm_long_review_gray)
            }
        }

        //결제완료 택배
        else if(getItemViewType(position) == 4) {
            (holder as HolderDelivery).date.text = dataListBuy[position].p_date
            Glide.with(ctx)
                .load(dataListBuy[position].a_pic_url)
                .apply(options)
                .into((holder).img_url)
            (holder).title.text = dataListBuy[position].a_name
            (holder).artist.text = dataListBuy[position].a_u_name
            (holder).btn_refund.setOnClickListener {
                try {
                    val refund_dialog = AlarmRefundDialogFragment()
                    refund_dialog.show((ctx as FragmentActivity).supportFragmentManager, refund_dialog.tag)
                } catch (e: Exception) {
                }
            }
            (holder).btn_comment.setOnClickListener {
                try {
                    val comment_dialog = AlarmCommentDialogFragment()
                    comment_dialog.show((ctx as AlarmActivity).supportFragmentManager, comment_dialog.tag)

                    AlarmBuyFragment.instance.p_idx = dataListBuy[position].p_idx
                } catch (e: Exception) {
                }
            }
            Log.d("*****AlarmBuyRecyclerViewAdapter::결제완료 택배::", dataListBuy[position].c_isComment.toString())
            if (dataListBuy[position].c_isComment) {
                (holder).btn_delivery_refund_inactive.isEnabled = false
                (holder).btn_delivery_review_inactive.isEnabled = false
                (holder).btn_delivery_refund_inactive.setBackgroundResource(R.drawable.alarm_refund_gray)
                (holder).btn_delivery_review_inactive.setBackgroundResource(R.drawable.alarm_review_gray)
            }
        }
    }

    inner class HolderPayDirect(itemView: View): RecyclerView.ViewHolder(itemView){
        val date : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_pay_direct_date) as TextView
        val img_url : ImageView = itemView.findViewById(R.id.iv_rv_item_alarm_buy_pay_direct_product) as ImageView
        val title : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_pay_direct_title) as TextView
        val artist : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_pay_direct_artist) as TextView
        val price : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_pay_direct_price) as TextView
        val bank_account : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_pay_direct_bankAccount) as TextView
    }

    inner class HolderPayDelivery(itemView: View): RecyclerView.ViewHolder(itemView){
        val date : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_pay_delivery_date) as TextView
        val img_url : ImageView = itemView.findViewById(R.id.iv_rv_item_alarm_buy_pay_delivery_product) as ImageView
        val title : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_pay_delivery_title) as TextView
        val artist : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_pay_delivery_artist) as TextView
        val price : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_pay_delivery_price) as TextView
        val bank_account : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_pay_delivery_bankAccount) as TextView
    }

    inner class HolderDirect(itemView: View): RecyclerView.ViewHolder(itemView){
        val date : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_direct_date) as TextView
        val img_url : ImageView = itemView.findViewById(R.id.iv_rv_item_alarm_buy_direct_product) as ImageView
        val title : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_direct_title) as TextView
        val artist : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_direct_artist) as TextView
        val seller_name : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_direct_seller_name) as TextView
        val seller_number : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_direct_seller_number) as TextView
        val seller_address : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_direct_seller_address) as TextView
        val btn_comment:ImageButton = itemView.findViewById(R.id.btn_rv_item_alarm_buy_direct_comment) as ImageButton
        val btn_direct_review_inactive: ImageButton = itemView.findViewById(R.id.btn_rv_item_alarm_buy_direct_comment) as ImageButton
    }

    inner class HolderDelivery(itemView: View): RecyclerView.ViewHolder(itemView){
        val date : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_delivery_date) as TextView
        val img_url : ImageView = itemView.findViewById(R.id.iv_rv_item_alarm_buy_delivery_product) as ImageView
        val title : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_delivery_title) as TextView
        val artist : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_buy_delivery_artist) as TextView
        val btn_refund:ImageButton = itemView.findViewById(R.id.btn_rv_item_alarm_buy_delivery_refund) as ImageButton
        val btn_comment: ImageButton = itemView.findViewById(R.id.btn_rv_item_alarm_buy_delivery_comment) as ImageButton
        val btn_delivery_refund_inactive: ImageButton = itemView.findViewById(R.id.btn_rv_item_alarm_buy_delivery_refund) as ImageButton
        val btn_delivery_review_inactive: ImageButton = itemView.findViewById(R.id.btn_rv_item_alarm_buy_delivery_comment) as ImageButton
    }
}