package com.artoo.sopt23.artoo_client_android.Adapter


import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Activity.EnterTheExhibitionActivity
import com.artoo.sopt23.artoo_client_android.Activity.ExhibitionDetailActivity
import com.artoo.sopt23.artoo_client_android.Data.ExhibitionDetailData
import com.artoo.sopt23.artoo_client_android.R
import com.artoo.sopt23.artoo_client_android.R.id.txt_ex_current_num
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class ExhibitionDetailAdapter(val dataList: ArrayList<ExhibitionDetailData>): RecyclerView.Adapter<ExhibitionDetailAdapter.Holder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val view: View = LayoutInflater.from(context).inflate(R.layout.rv_item_detail_exhibition, parent, false)
        return Holder(view)

    }

    override fun getItemCount(): Int = dataList.size


    override fun onBindViewHolder(holder: Holder, position: Int) {



        Glide.with(context).load(dataList.get(position).pic_url).into(holder.pic_url)

        holder.d_title.text = dataList[position].a_name.replace("\\n", "\r\n")
        holder.u_name.text = dataList[position].u_name
        holder.a_form.text = dataList[position].a_form
        holder.a_width.text = dataList[position].a_width.toString()
        holder.a_height.text = dataList[position].a_height.toString()
        holder.a_year.text = dataList[position].a_year


    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val pic_url: ImageView = itemView.findViewById<View>(R.id.img_rv_item_ex_detail) as ImageView
        val d_title: TextView = itemView.findViewById(R.id.txt_rv_item_ex_detail_title) as TextView
        val u_name: TextView = itemView.findViewById(R.id.txt_rv_item_ex_detail_writer) as TextView
        val a_form: TextView = itemView.findViewById(R.id.txt_rv_item_ex_detail_type) as TextView
        val a_width: TextView = itemView.findViewById(R.id.txt_rv_item_ex_detail_width) as TextView
        val a_height: TextView = itemView.findViewById(R.id.txt_rv_item_ex_detail_height) as TextView
        val a_year: TextView = itemView.findViewById(R.id.txt_rv_item_ex_detail_year) as TextView

    }


}