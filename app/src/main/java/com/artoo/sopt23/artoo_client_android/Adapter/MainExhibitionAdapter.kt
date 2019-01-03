package com.artoo.sopt23.artoo_client_android.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.artoo.sopt23.artoo_client_android.Activity.EnterTheExhibitionActivity
import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MainExhibitionAdapter (val main_ex : ArrayList<String>): RecyclerView.Adapter<MainExhibitionAdapter.ViewHolder>() {

    lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        context = parent.context
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_main_exhibition, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var options : RequestOptions = RequestOptions().placeholder(R.drawable.pic3)
        Glide.with(context).load(main_ex.get(position)).apply(options).into(holder.main_ex_img)
        Log.d("asd" , main_ex.get(position))

        holder.main_ex_img.setOnClickListener {
            //var intent = Intent(context,EnterTheExhibitionActivity::class.java)
//            //intent.putExtra("",)
//            context.startActivity(intent)

            context.startActivity(Intent(context,
                EnterTheExhibitionActivity::class.java))
        }


    }

    override fun getItemCount() = main_ex.size


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val main_ex_img : ImageView = itemView.findViewById<View>(R.id.img_exhibition_list) as ImageView
    }

}