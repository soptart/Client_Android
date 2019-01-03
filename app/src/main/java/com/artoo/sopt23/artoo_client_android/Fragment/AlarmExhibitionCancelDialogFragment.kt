package com.artoo.sopt23.artoo_client_android.Fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_alarm_exhibition_cancel_dialog.view.*

class AlarmExhibitionCancelDialogFragment : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view : View = inflater.inflate(R.layout.fragment_alarm_exhibition_cancel_dialog, container, false)

        view.btn_alarm_exhibition_cancel_dialog_no.setOnClickListener {
            Log.d("alarm_cancel_no_frag", "no_pushed")
            onDestroyView()
        }
        view.btn_alarm_exhibition_cancel_dialog_yes.setOnClickListener {
            Log.d("alarm_cancel_yes_frag", "cancel_pushed")
            onDestroyView()
        }

        return view
    }
}
