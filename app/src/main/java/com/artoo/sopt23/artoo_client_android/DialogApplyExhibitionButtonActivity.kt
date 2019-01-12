package com.artoo.sopt23.artoo_client_android

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView

class DialogApplyExhibitionButtonActivity(context: Context) : Dialog(context), View.OnClickListener {
    override fun onClick(v: View?) {
        try {
            dismiss()
        } catch (e: Exception) {
        }
    }
    lateinit var btn_oops_button : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_apply_exhibition_button)

        btn_oops_button = findViewById(R.id.btn_oops_button)
        btn_oops_button.setOnClickListener(this)
    }
}
