package com.artoo.sopt23.artoo_client_android.CustomView

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.view.Window
import com.artoo.sopt23.artoo_client_android.Activity.LoginActivity
import com.artoo.sopt23.artoo_client_android.Activity.MypagePreferencesActivity
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.R
import com.artoo.sopt23.artoo_client_android.R.id.parent
import kotlinx.android.synthetic.main.dialog_logout.*

class LogoutDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_logout)
    }
}