package com.artoo.sopt23.artoo_client_android.Fragment

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artoo.sopt23.artoo_client_android.Activity.AlarmActivity
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.Data.Response.Delete.DeleteExhibitionResponse
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_alarm_exhibition_cancel_dialog.view.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlarmExhibitionCancelDialogFragment() : DialogFragment() {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view : View = inflater.inflate(R.layout.fragment_alarm_exhibition_cancel_dialog, container, false)

        view.btn_alarm_exhibition_cancel_dialog_no.setOnClickListener {
            try {
                Log.d("alarm_cancel_no_frag", "no_pushed")
                onDestroyView()
            } catch (e: Exception) {
            }
        }
        view.btn_alarm_exhibition_cancel_dialog_yes.setOnClickListener {
            try {
                deleteExhibitionResponse()
                Log.d("alarm_cancel_yes_frag", "cancel_pushed")
                onDestroyView()
            } catch (e: Exception) {
            }
        }

        return view
    }

    private fun deleteExhibitionResponse(){
        val token = SharedPreferenceController.getAuthorization(context!!)
        //val dc_idx : Int = (context as FragmentActivity).intent.getIntExtra("dc_idx",-1)
        //val u_idx : Int = (context as FragmentActivity).intent.getIntExtra("user_idx",-1)
        val deleteExhibitionResponse = networkService.deleteExhibitionResponse("application/json",token, AlarmExhibitionFragment.instance.dc_idx, AlarmExhibitionFragment.instance.u_idx)
        deleteExhibitionResponse.enqueue(object : Callback<DeleteExhibitionResponse> {
            override fun onFailure(call: Call<DeleteExhibitionResponse>, t: Throwable) {
                Log.e("*****AlarmExhibitionCancelDialogFragment::deleteExhibitionResponse::Failed", t.toString())
            }
            override fun onResponse(call: Call<DeleteExhibitionResponse>, response: Response<DeleteExhibitionResponse>) {
                if(response.isSuccessful){
                    toast("전시가 취소되었습니다!")
                    Log.d("*****AlarmCommentDialogFragment::postCommentResponse::Success",response.body().toString())
                }
            }
        })
    }
}
