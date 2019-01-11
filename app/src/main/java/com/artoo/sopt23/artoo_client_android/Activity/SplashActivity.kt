package com.artoo.sopt23.artoo_client_android.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val source = findViewById<ImageView>(R.id.splash_img)
        Glide.with(this).load(R.drawable.artoo_android_splash).into(source)

        val handler = Handler()
        handler.postDelayed({
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }, 3800)



    }
}
