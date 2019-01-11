package com.artoo.sopt23.artoo_client_android.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Activity.ApplyExhibitionActivity
import com.artoo.sopt23.artoo_client_android.Data.ApplyExhibitionData
import com.artoo.sopt23.artoo_client_android.Data.ExhibitionDisplayData
import com.artoo.sopt23.artoo_client_android.R
import org.jetbrains.anko.sdk25.coroutines.onCheckedChange


class ApplyExhibitionAdapter(val dataList: ArrayList<ExhibitionDisplayData>): RecyclerView.Adapter<ApplyExhibitionAdapter.Holder>(){
    lateinit var context: Context

    var selectedRadioBtn: RadioButton? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val view: View = LayoutInflater.from(context).inflate(R.layout.rv_item_apply_exhibition_radio, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.d_title.text = dataList[position].d_title
        holder.d_subTitle.text = dataList[position].d_subTitle
        holder.d_shortDetail.text = dataList[position].d_shortDetail

        var intent = Intent(context, ApplyExhibitionActivity::class.java)
        intent.putExtra("d_title", dataList.get(position).d_title)
        intent.putExtra("d_subTitle", dataList.get(position).d_subTitle)


        holder.radioButton.onCheckedChange { buttonView, isChecked ->
            //            var data : ApplyExhibitionActivity = context as ApplyExhibitionActivity

            if(!isChecked){
                if(selectedRadioBtn == buttonView) selectedRadioBtn = null
                ApplyExhibitionActivity.instance.d_idx_check = true
                ApplyExhibitionActivity.instance.Check()
//                d_idx = -1
//                intent.putExtra("d_idx", dataList[position].d_idx)
//                Log.d("first_d_idx", dataList[position].d_idx.toString())

            }
            else{
                if(selectedRadioBtn == null){
                    selectedRadioBtn = buttonView as RadioButton
//                    d_idx = -1
//                    data.d_idx = dataList[position].d_idx
                    ApplyExhibitionActivity.instance.d_idx = dataList[position].d_idx
                    ApplyExhibitionActivity.instance.d_idx_check = true
                    ApplyExhibitionActivity.instance.Check()
//                    Log.d("second_d_idx", dataList[position].d_idx.toString())
                }
                else{
                    selectedRadioBtn!!.isChecked = false
                    selectedRadioBtn = buttonView as RadioButton
                    //이 부분이 체크 안되있으면 체크해주는 부분이라면
//                    data.d_idx = dataList[position].d_idx
//                    d_idx = -1
                    ApplyExhibitionActivity.instance.d_idx = dataList[position].d_idx
                    ApplyExhibitionActivity.instance.d_idx_check = true
                    ApplyExhibitionActivity.instance.Check()
//                    Log.d("third_ d_idx ", dataList[position].d_idx.toString())
                }
            }
        }

    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val d_title: TextView = itemView.findViewById(R.id.txt_apply_ex_title) as TextView
        val d_subTitle: TextView = itemView.findViewById(R.id.txt_apply_ex_type) as TextView
        val d_shortDetail: TextView = itemView.findViewById(R.id.txt_apply_ex_desc) as TextView
        val radioButton: RadioButton = itemView.findViewById(R.id.radio_btn_apply_ex_type) as RadioButton

    }
}