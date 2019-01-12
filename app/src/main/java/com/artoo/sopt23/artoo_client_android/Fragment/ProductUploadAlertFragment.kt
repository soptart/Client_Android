package com.artoo.sopt23.artoo_client_android.Fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_product_upload_alert.view.*

class ProductUploadAlertFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view : View = inflater!!.inflate(R.layout.fragment_product_upload_alert, container, false)

        view.btn_product_upload_alert_confirm.setOnClickListener {
            try {
                onDestroyView()
            } catch (e: Exception) {
            }
        }

        return view
    }
}
