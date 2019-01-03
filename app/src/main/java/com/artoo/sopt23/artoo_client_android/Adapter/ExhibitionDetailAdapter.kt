package com.artoo.sopt23.artoo_client_android.Adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Data.ExhibitionDetailData
import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ExhibitionDetailAdapter(val dataList: ArrayList<ExhibitionDetailData>): RecyclerView.Adapter<ExhibitionDetailAdapter.Holder>(){
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val view: View = LayoutInflater.from(context).inflate(R.layout.rv_item_detail_exhibition, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.title.text = dataList[position].title
        holder.writer.text = dataList[position].writer
        holder.type.text = dataList[position].type
        holder.width.text = dataList[position].width.toString()
        holder.height.text = dataList[position].height.toString()
        holder.year.text = dataList[position].year

    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.txt_rv_item_ex_detail_title) as TextView
        val writer: TextView = itemView.findViewById(R.id.txt_rv_item_ex_detail_writer) as TextView
        val type: TextView = itemView.findViewById(R.id.txt_rv_item_ex_detail_type) as TextView
        val width: TextView = itemView.findViewById(R.id.txt_rv_item_ex_detail_width) as TextView
        val height: TextView = itemView.findViewById(R.id.txt_rv_item_ex_detail_height) as TextView
        val year: TextView = itemView.findViewById(R.id.txt_rv_item_ex_detail_year) as TextView

    }

}