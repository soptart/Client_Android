package com.artoo.sopt23.artoo_client_android.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import com.artoo.sopt23.artoo_client_android.Data.ApplyExMyProductData
import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import org.jetbrains.anko.sdk25.coroutines.onCheckedChange

class ApplyExMyProductAdapter (val dataList: ArrayList<ApplyExMyProductData>): RecyclerView.Adapter<ApplyExMyProductAdapter.Holder>(){
    lateinit var context: Context
    var selectedRadioBtn: RadioButton? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val view: View = LayoutInflater.from(context).inflate(R.layout.rv_item_apply_exhibition_my_product, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        Glide.with(context).load(dataList[position].my_product_img_url).into(holder.my_product_img)
        holder.radioButton.onCheckedChange { buttonView, isChecked ->
            if(!isChecked){
                if(selectedRadioBtn == buttonView) selectedRadioBtn = null
            }
            else{
                if(selectedRadioBtn == null){
                    selectedRadioBtn = buttonView as RadioButton
                }
                else{
                    selectedRadioBtn!!.isChecked = false
                    selectedRadioBtn = buttonView as RadioButton
                }
            }
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val my_product_img : ImageView = itemView.findViewById<View>(R.id.img_ex_my_product) as ImageView
        var radioButton : RadioButton = itemView.findViewById(R.id.radio_btn_apply_ex)


    }

}