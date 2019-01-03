package com.artoo.sopt23.artoo_client_android.Fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_alarm_comment_dialog.view.*


class AlarmCommentDialogFragment : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view : View = inflater.inflate(R.layout.fragment_alarm_comment_dialog, container, false)

        view.btn_alarm_comment_dialog_complete.setOnClickListener {
            Log.d("alarm_comment_dialog_frag", "complete_pushed")
            onDestroyView()
        }

        return view
    }
}
