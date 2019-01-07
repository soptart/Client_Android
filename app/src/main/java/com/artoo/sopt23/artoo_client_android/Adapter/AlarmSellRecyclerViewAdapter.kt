package com.artoo.sopt23.artoo_client_android.Adapter

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Data.AlarmSellData
import com.artoo.sopt23.artoo_client_android.Fragment.AlarmDealCancelDialogFragment
import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class AlarmSellRecyclerViewAdapter(val ctx: Context, var dataListSell: ArrayList<AlarmSellData>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemViewType(position: Int): Int {
        if(dataListSell[position].p_isDelivery == 0){
            return 0
        }
        else {
            return 1
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var options: RequestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(20))

        if(getItemViewType(position) == 0) { //판매 직거래
            (holder as HolderDirect).date.text = dataListSell[position].p_date
            Glide.with(ctx)
                .load(dataListSell[position].a_pic_url)
                .apply(options)
                .into((holder).img_url)
            (holder).title.text = dataListSell[position].a_name
            (holder).artist.text = dataListSell[position].a_u_name
            (holder).artoo_name.text = dataListSell[position].u_name
            (holder).artoo_number.text = dataListSell[position].u_phone
            (holder).artoo_address.text = dataListSell[position].u_address
        }

        else { //판매 택배
            (holder as HolderDelivery).date.text = dataListSell[position].p_date
            Glide.with(ctx)
                .load(dataListSell[position].a_pic_url)
                .apply(options)
                .into((holder).img_url)
            (holder).title.text = dataListSell[position].a_name
            (holder).artist.text = dataListSell[position].a_u_name
            (holder).artoo_name.text = dataListSell[position].u_name
            (holder).artoo_number.text = dataListSell[position].u_phone
            (holder).artoo_address.text = dataListSell[position].u_address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 0){
            val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_alarm_sell_direct, parent, false)
            return HolderDirect(view)
        }
        else{
            val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_alarm_sell_delivery, parent, false)
            return HolderDelivery(view)
        }
    }

    override fun getItemCount(): Int = dataListSell.size

    inner class HolderDirect(itemView: View): RecyclerView.ViewHolder(itemView){
        val date : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_sell_direct_date) as TextView
        val img_url : ImageView = itemView.findViewById(R.id.iv_rv_item_alarm_sell_direct_product) as ImageView
        val title : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_sell_direct_title) as TextView
        val artist : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_sell_direct_artist) as TextView
        val artoo_name : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_sell_direct_artoo_name) as TextView
        val artoo_number : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_sell_direct_artoo_number) as TextView
        val artoo_address : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_sell_direct_artoo_address) as TextView
    }

    inner class HolderDelivery(itemView: View): RecyclerView.ViewHolder(itemView){
        val date : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_sell_delivery_date) as TextView
        val img_url : ImageView = itemView.findViewById(R.id.iv_rv_item_alarm_sell_delivery_product) as ImageView
        val title : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_sell_delivery_title) as TextView
        val artist : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_sell_delivery_artist) as TextView
        val artoo_name : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_sell_delivery_artoo_name) as TextView
        val artoo_number : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_sell_delivery_artoo_number) as TextView
        val artoo_address : TextView = itemView.findViewById(R.id.tv_rv_item_alarm_sell_delivery_artoo_address) as TextView
    }
}