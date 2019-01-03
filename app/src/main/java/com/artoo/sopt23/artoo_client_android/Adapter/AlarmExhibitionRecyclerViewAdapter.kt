package com.artoo.sopt23.artoo_client_android.Adapter

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Data.AlarmExhibitionData
import com.artoo.sopt23.artoo_client_android.Fragment.AlarmExhibitionCancelDialogFragment
import com.artoo.sopt23.artoo_client_android.R

class AlarmExhibitionRecyclerViewAdapter(val ctx: Context,val dataList: ArrayList<AlarmExhibitionData>): RecyclerView.Adapter<AlarmExhibitionRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_alarm_exhibition, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.date.text = dataList[position].date
        holder.title.text = dataList[position].title
        holder.date_start.text = dataList[position].date_start
        holder.date_end.text = dataList[position].date_end
        holder.artist.text = dataList[position].artist
        holder.product_title.text = dataList[position].product_title

        holder.btn_cancle.setOnClickListener {
            val cancel_dialog = AlarmExhibitionCancelDialogFragment()
            cancel_dialog.show((ctx as FragmentActivity).supportFragmentManager, cancel_dialog.tag)
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val date: TextView = itemView.findViewById(R.id.tv_rv_item_alarm_exhibition_date) as TextView
        val title: TextView = itemView.findViewById(R.id.tv_rv_item_alarm_exhibition_title) as TextView
        val date_start: TextView = itemView.findViewById(R.id.tv_rv_item_alarm_exhibition_date_start) as TextView
        val date_end: TextView = itemView.findViewById(R.id.tv_rv_item_alarm_exhibition_date_end) as TextView
        val artist: TextView = itemView.findViewById(R.id.tv_rv_item_alarm_exhibition_artist) as TextView
        val product_title: TextView = itemView.findViewById(R.id.tv_rv_item_alarm_exhibition_product_title) as TextView
        val btn_cancle: Button = itemView.findViewById(R.id.btn_rv_item_alarm_exhibition_cancel) as Button
    }

}
