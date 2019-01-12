package com.artoo.sopt23.artoo_client_android

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.dialog_fail_apply.*

class DialogFailApplyActivity(context: Context) : Dialog(context), View.OnClickListener {
    override fun onClick(v: View?) {
        try {
            dismiss()
        } catch (e: Exception) {
        }
    }
    lateinit var btn_oops : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_fail_apply)

        btn_oops = findViewById(R.id.btn_oops)
        btn_oops.setOnClickListener(this)
    }
}
