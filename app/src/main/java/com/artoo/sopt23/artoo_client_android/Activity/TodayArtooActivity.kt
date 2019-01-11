package com.artoo.sopt23.artoo_client_android.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.activity_today_artoo.*

class TodayArtooActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_artoo)

        val c_id = intent.getIntExtra("c_id", 0)

        if(c_id == 1) img_today_artoo.setImageResource(R.drawable.contents_1)
        else if(c_id == 2) img_today_artoo.setImageResource(R.drawable.contents_2)
        else{
            Log.i("TodayArtooActivity", "Wrong c_id " + c_id.toString())
        }
    }
}