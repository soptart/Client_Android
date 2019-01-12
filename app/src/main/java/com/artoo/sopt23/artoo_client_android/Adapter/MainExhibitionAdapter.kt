package com.artoo.sopt23.artoo_client_android.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Activity.EnterTheExhibitionActivity
import com.artoo.sopt23.artoo_client_android.Data.MainExhibitionData
import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.w3c.dom.Text

class MainExhibitionAdapter(val main_ex: ArrayList<MainExhibitionData>) : RecyclerView.Adapter<MainExhibitionAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_main_exhibition, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context).load(main_ex.get(position).d_repImg_url).into(holder.main_ex_img)
        holder.exhibition_title.text = main_ex[position].d_title.replace("\\n", "\r\n")
        holder.d_longDetail.text = main_ex[position].d_longDetail.replace("\\n", "\r\n")
        Log.d("asdf", main_ex[position].d_longDetail)


        holder.main_ex_click.setOnClickListener {

            try {
                var intent = Intent(context, EnterTheExhibitionActivity::class.java)
                intent.putExtra("d_repImg_url", main_ex.get(position).d_repImg_url)
                intent.putExtra("d_idx", main_ex.get(position).d_idx)
                intent.putExtra("d_mainImg_url", main_ex.get(position).d_mainImg_url)
                intent.putExtra("d_shortDetail", main_ex.get(position).d_shortDetail.replace("\\n", "\r\n"))
                intent.putExtra("d_sDateNow", main_ex.get(position).d_sDateNow)
                intent.putExtra("d_eDateNow", main_ex.get(position).d_eDateNow)
                intent.putStringArrayListExtra("d_artworkUser", main_ex.get(position).d_artworkUser)
                intent.putExtra("d_title", main_ex.get(position).d_title.replace("\\n", "\r\n"))
                intent.putExtra("d_longDetail", main_ex.get(position).d_longDetail.replace("\\n", "\r\n"))
                intent.putExtra("d_titleImg_url", main_ex.get(position).d_titleImg_url)
                context.startActivity(intent)
            } catch (e: Exception) {
            }
        }


    }

    override fun getItemCount() = main_ex.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val main_ex_img: ImageView = itemView.findViewById<View>(R.id.img_exhibition_list) as ImageView
        val exhibition_title : TextView = itemView.findViewById<View>(R.id.exhibition_title) as TextView
        val d_longDetail: TextView = itemView.findViewById<View>(R.id.txt_main_exhibition_long_detail) as TextView
        val main_ex_click : LinearLayout = itemView.findViewById<View>(R.id.main_ex_click) as LinearLayout
    }

}