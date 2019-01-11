package com.artoo.sopt23.artoo_client_android

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Activity.ApplyExhibitionActivity
import kotlinx.android.synthetic.main.dialog_apply_exhibition.*
import kotlin.jvm.java

class DialogApplyExhibitionActivity(context: Context) : Dialog(context), View.OnClickListener {
    override fun onClick(v: View?) {
        dismiss()
    }
    lateinit var btn_apply_done : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_apply_exhibition)

        var intent = Intent(context, ApplyExhibitionActivity::class.java)


        if(intent.hasExtra("d_title")) {
            s_dialog_title.text = intent.getStringExtra("d_title")
            Log.d("s_dialog test : ", intent.getStringExtra("d_title"))
        }
        if(intent.hasExtra("d_tid_subTitletle")) {
            s_dialog_sub_title.text = intent.getStringExtra("d_subTitle")
            Log.d("s_dialog test : ", intent.getStringExtra("d_subTitle"))
        }

        s_dialog_u_name.text = ApplyExhibitionActivity.instance.u_Name
        s_dialog_a_name.text = ApplyExhibitionActivity.instance.a_Name
//        if(intent.hasExtra("u_name")) {
//            s_dialog_u_name.text = intent.getStringExtra("u_name")
//            Log.d("s_dialog test : ", intent.getStringExtra("u_name"))
//        }
//        if(intent.hasExtra("a_name")) {
//            s_dialog_a_name.text = intent.getStringExtra("u_name")
//            Log.d("s_dialog test : ", intent.getStringExtra("u_name"))
//        }

        btn_apply_done = findViewById(R.id.btn_apply_done)
        btn_apply_done.setOnClickListener(this)


    }
}