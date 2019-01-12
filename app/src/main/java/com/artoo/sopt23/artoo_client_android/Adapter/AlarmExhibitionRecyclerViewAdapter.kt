package com.artoo.sopt23.artoo_client_android.Adapter

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Activity.AlarmActivity
import com.artoo.sopt23.artoo_client_android.Data.AlarmExhibitionData
import com.artoo.sopt23.artoo_client_android.Fragment.AlarmExhibitionCancelDialogFragment
import com.artoo.sopt23.artoo_client_android.Fragment.AlarmExhibitionFragment
import com.artoo.sopt23.artoo_client_android.R
import java.text.SimpleDateFormat

class AlarmExhibitionRecyclerViewAdapter(val ctx: Context, var parentFragment:AlarmExhibitionFragment, var dataList: ArrayList<AlarmExhibitionData>): RecyclerView.Adapter<AlarmExhibitionRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_alarm_exhibition, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.date.text = dataList[position].dc_date
        holder.title.text = dataList[position].display.d_title

        var date_s : String = dataList[position].display.d_sDateNow
        val seperate_date_s = date_s.split("-")
        holder.date_start.text = seperate_date_s[0]+"."+seperate_date_s[1]+"."+seperate_date_s[2]

        var date_e : String = dataList[position].display.d_eDateNow
        val seperate_date_e = date_e.split("-")
        holder.date_end.text = seperate_date_e[0]+"."+seperate_date_e[1]+"."+seperate_date_e[2]

        holder.artist.text = dataList[position].u_name
        holder.product_title.text = dataList[position].a_name

        holder.cancel_button.setOnClickListener {
            try {
                val cancel_dialog = AlarmExhibitionCancelDialogFragment()
                cancel_dialog.show((ctx as AlarmActivity).supportFragmentManager, cancel_dialog.tag)
                (ctx as AlarmActivity).supportFragmentManager.executePendingTransactions()
                cancel_dialog.dialog.setOnDismissListener(object : DialogInterface.OnDismissListener{
                    override fun onDismiss(p0: DialogInterface?) {
                        parentFragment.getAlarmExhibitionResponse()
                    }
                })
                //var intent = Intent((ctx as FragmentActivity), AlarmExhibitionCancelDialogFragment::class.java)
                //intent.putExtra("dc_idx", dataList[position].dc_idx)
                //intent.putExtra("user_idx", dataList[position].u_idx)
                AlarmExhibitionFragment.instance.u_idx = dataList[position].u_idx
                AlarmExhibitionFragment.instance.dc_idx = dataList[position].dc_idx
            } catch (e: Exception) {
            }
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val date: TextView = itemView.findViewById(R.id.tv_rv_item_alarm_exhibition_date) as TextView
        val title: TextView = itemView.findViewById(R.id.tv_rv_item_alarm_exhibition_title) as TextView
        val date_start: TextView = itemView.findViewById(R.id.tv_rv_item_alarm_exhibition_date_start) as TextView
        val date_end: TextView = itemView.findViewById(R.id.tv_rv_item_alarm_exhibition_date_end) as TextView
        val artist: TextView = itemView.findViewById(R.id.tv_rv_item_alarm_exhibition_artist) as TextView
        val product_title: TextView = itemView.findViewById(R.id.tv_rv_item_alarm_exhibition_product_title) as TextView
        val cancel_button: Button = itemView.findViewById(R.id.btn_rv_item_alarm_exhibition_cancel) as Button
    }
}
