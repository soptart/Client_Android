package com.artoo.sopt23.artoo_client_android.CustomView

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.dialog_preparingcard.*

class PreparingDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_preparingcard)
        card_confirm_btn.setOnClickListener { try {
            dismiss()
        } catch (e: Exception) {
        }
        }
    }
}
