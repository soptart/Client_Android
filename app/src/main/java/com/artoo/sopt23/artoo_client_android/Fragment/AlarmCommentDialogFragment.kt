package com.artoo.sopt23.artoo_client_android.Fragment

import android.app.Activity
import android.content.Intent.getIntent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artoo.sopt23.artoo_client_android.Activity.AlarmActivity
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.Data.Response.Post.PostCommentResponse
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.fragment_alarm_comment_dialog.*
import kotlinx.android.synthetic.main.fragment_alarm_comment_dialog.view.*
import org.jetbrains.anko.support.v4.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AlarmCommentDialogFragment : DialogFragment() {

    var jsonObject = JSONObject()
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view : View = inflater.inflate(R.layout.fragment_alarm_comment_dialog, container, false)

        view.btn_alarm_comment_dialog_complete.setOnClickListener {
            if(et_fragment_alarm_comment_dialog.text.toString().isNotEmpty()) {
                val getComment = et_fragment_alarm_comment_dialog.text.toString()

                jsonObject.put("p_comment", getComment)
                postCommentResponse()
                Log.d("alarm_comment_dialog_frag", "complete_pushed")
            }
        }

        return view
    }

    private fun postCommentResponse(){
        val token = SharedPreferenceController.getAuthorization(context!!)
        var p_idx : Int = (context as AlarmActivity).intent.getIntExtra("p_idx",-1)
        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
        val postCommentResponse = networkService.postCommentResponse("application/json",token,gsonObject,p_idx)
        postCommentResponse.enqueue(object : Callback<PostCommentResponse> {
            override fun onFailure(call: Call<PostCommentResponse>, t: Throwable) {
                Log.e("*****AlarmCommentDialogFragment::postCommentResponse::", t.toString())
            }
            override fun onResponse(call: Call<PostCommentResponse>, response: Response<PostCommentResponse>) {
                if(response.isSuccessful){

                    toast("후기를 작성했습니다.")
                    Log.d("*****AlarmCommentDialogFragment::postCommentResponse::Success",response.body().toString())
                    onDestroyView()
                }
            }
        })
    }
}
