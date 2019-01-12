package com.artoo.sopt23.artoo_client_android.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.artoo.sopt23.artoo_client_android.Activity.ProductDetailActivity
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.Data.CommentData
import com.artoo.sopt23.artoo_client_android.Data.Response.Delete.DeleteProductCommentResponse
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class ProductDetailCommentRecyclerViewAdapter(val ctx: Context, var dataList: ArrayList<CommentData>): RecyclerView.Adapter<ProductDetailCommentRecyclerViewAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailCommentRecyclerViewAdapter.Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_product_detail_comment, parent, false)
        return Holder(view)
    }

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ProductDetailCommentRecyclerViewAdapter.Holder, position: Int) {
        //var originDateFormat: SimpleDateFormat = SimpleDateFormat()
        //originDateFormat.toLocalizedPattern()
        //originDateFormat.toPattern()
        //var targetDateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        holder.username.text = dataList[position].u_name
        var targetDate = dataList[position].c_date.substring(0, 10)
        //var date:Date = originDateFormat.parse(targetDate)
        holder.date.text = targetDate
        holder.comment.text = dataList[position].c_content
        if(dataList[position].auth) holder.delete.visibility = View.VISIBLE
        else holder.delete.visibility = View.GONE

        holder.delete.setOnClickListener {
            try {
                val token = SharedPreferenceController.getAuthorization(ctx)

                val deleteProductCommentResponse: Call<DeleteProductCommentResponse> = networkService.deleteProductCommentResponse(token, SharedPreferenceController.getUserID(ctx), dataList[position].c_idx)
                deleteProductCommentResponse.enqueue(object: Callback<DeleteProductCommentResponse>{
                    override fun onFailure(call: Call<DeleteProductCommentResponse>, t: Throwable) {
                        Log.i("ProductCommentDelete", "Connection Failure")
                    }

                    override fun onResponse(
                        call: Call<DeleteProductCommentResponse>,
                        response: Response<DeleteProductCommentResponse>
                    ) {
                        Toast.makeText(ctx, response.body()!!.message, Toast.LENGTH_SHORT).show()
                        if(response.isSuccessful) (ctx as ProductDetailActivity).getProductCommentData()
                    }
                })
            } catch (e: Exception) {
            }
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val username: TextView = itemView.findViewById(R.id.txt_rv_item_product_detail_comment_username) as TextView
        val date: TextView = itemView.findViewById(R.id.txt_rv_item_product_detail_comment_date) as TextView
        val comment: TextView = itemView.findViewById(R.id.txt_rv_item_product_detail_comment) as TextView
        val delete: TextView = itemView.findViewById(R.id.txt_Rv_item_product_detail_comment_delete) as TextView
    }

}