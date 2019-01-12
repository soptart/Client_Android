package com.artoo.sopt23.artoo_client_android.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_enter_the_exhibition.*

class EnterTheExhibitionActivity : AppCompatActivity() {
    var d_idx: Int? = null
    var d_title : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_the_exhibition)



        var d_titleImg_url : String = intent.getStringExtra("d_titleImg_url")
//
//        var intent2 = Intent(this, ExhibitionDetailActivity::class.java)
//        intent2.putExtra("d_titleImg_url", d_titleImg_url)
//        Log.d ("Img test ", d_titleImg_url)


        if(intent.hasExtra("d_mainImg_url")) {
            Glide.with(this).load(intent.getStringExtra("d_mainImg_url")).into(iv_enter_the_exhibition)
        }
        if(intent.hasExtra("d_idx")) {
            d_idx = intent.getIntExtra("d_idx", -1)
        }
        if(intent.hasExtra("d_title")) {
            txt_enter_exhibition_title.text = intent.getStringExtra("d_title")
        }
        if(intent.hasExtra("d_shortDetail")) {
            txt_enter_exhibition_detail.text = intent.getStringExtra("d_shortDetail")
        }
        Log.d("position_test", intent.hasExtra("d_shortDetail").toString())
        if(intent.hasExtra("d_sDateNow")) {
            val date_s : String = intent.getStringExtra("d_sDateNow")
            val sds = date_s.split("-")
            txt_enter_ex_sDate_now.text = sds[0]+"."+sds[1]
        }
        if(intent.hasExtra("d_eDateNow")) {
            val date_e : String = intent.getStringExtra("d_eDateNow")
            val sde = date_e.split("-")
            txt_enter_ex_eDate_now.text = sde[0]+"."+sde[1]
        }




        if(intent.hasExtra("d_artworkUser")) {
            var User = intent.getStringArrayListExtra("d_artworkUser")
            User.size

            var name : String =""
            var name2 : String =""

            for(i in 0..User.size-1) {

                if(i==0) {
                    name += User.get(i).toString()
                } else if ( i <= 10 ){
                    name += "\n" +User.get(i).toString()
                } else if ( i == 11 ) {
                    name2 += User.get(i).toString()
                } else if ( i > 11 ) {
                    name2 += "\n" + User.get(i).toString()
                }
            }
            txt_enter_ex_artwork_user.setText(name)
            txt_enter_ex_artwork_user2.setText(name2)
        }




        btn_enter_ex.setOnClickListener {

            try {
                val nextIntent = Intent(this, ExhibitionDetailActivity::class.java)
                nextIntent.putExtra("d_idx" ,d_idx)
                nextIntent.putExtra("d_title", d_title)
                nextIntent.putExtra("d_titleImg_url", d_titleImg_url)

                Log.d("d_idx test : ", d_idx.toString())
                Log.d("d_title test : ", d_title.toString())
                startActivity(nextIntent)
                finish()
            } catch (e: Exception) {
            }

        }
    }
}
