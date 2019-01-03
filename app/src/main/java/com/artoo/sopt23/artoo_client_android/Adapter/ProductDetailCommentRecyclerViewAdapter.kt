package com.artoo.sopt23.artoo_client_android.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Data.CommentData
import com.artoo.sopt23.artoo_client_android.R

class ProductDetailCommentRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<CommentData>): RecyclerView.Adapter<ProductDetailCommentRecyclerViewAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailCommentRecyclerViewAdapter.Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_product_detail_comment, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ProductDetailCommentRecyclerViewAdapter.Holder, position: Int) {
        holder.username.text = dataList[position].username
        holder.date.text = dataList[position].date
        holder.comment.text = dataList[position].comment
        holder.delete.setOnClickListener {
            dataList.removeAt(position)
            notifyDataSetChanged()
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val username: TextView = itemView.findViewById(R.id.txt_rv_item_product_detail_comment_username) as TextView
        val date: TextView = itemView.findViewById(R.id.txt_rv_item_product_detail_comment_date) as TextView
        val comment: TextView = itemView.findViewById(R.id.txt_rv_item_product_detail_comment) as TextView
        val delete: TextView = itemView.findViewById(R.id.txt_Rv_item_product_detail_comment_delete) as TextView
    }

}