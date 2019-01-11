package com.artoo.sopt23.artoo_client_android.Fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.artoo.sopt23.artoo_client_android.Adapter.AlarmExhibitionRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.Data.AlarmExhibitionData
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetAlarmExhibitionResponse
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_alarm_exhibition.*
import kotlinx.android.synthetic.main.rv_item_alarm_exhibition.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class AlarmExhibitionFragment : Fragment() {

    var u_idx: Int = -1
    var dc_idx : Int = -1

    companion object {
        var instance: AlarmExhibitionFragment = AlarmExhibitionFragment()
    }

    var dataListExhibition = ArrayList<AlarmExhibitionData>()

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }
    lateinit var alarmExhibitionRecyclerViewAdapter: AlarmExhibitionRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View =  inflater.inflate(R.layout.fragment_alarm_exhibition, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
    }

    override fun onResume() {
        super.onResume()

        getAlarmExhibitionResponse()
    }
    private fun setRecyclerView() {
        alarmExhibitionRecyclerViewAdapter = AlarmExhibitionRecyclerViewAdapter(activity!!,this, dataListExhibition)
        rv_fragment_alarm_exhibition_list.adapter = alarmExhibitionRecyclerViewAdapter
        rv_fragment_alarm_exhibition_list.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL,false)
    }

    fun getAlarmExhibitionResponse() {
        val token = SharedPreferenceController.getAuthorization(context!!)
        val user_idx = SharedPreferenceController.getUserID(context!!)
        val getAlarmBuyResponse = networkService.getAlarmExhibitionResponse("application/json",token,user_idx)
        getAlarmBuyResponse.enqueue(object : Callback<GetAlarmExhibitionResponse> {
            override fun onFailure(call: Call<GetAlarmExhibitionResponse>, t: Throwable) {
                Log.e("*****AlarmActivity::getAlarmExhibitionResponse::", t.toString())
            }
            override fun onResponse(call: Call<GetAlarmExhibitionResponse>, response: Response<GetAlarmExhibitionResponse>) {
                if(response.isSuccessful){
                    dataListExhibition = response.body()!!.data
                    Log.d("*****AlarmActivity::getAlarmExhibitionResponse::Success", dataListExhibition.toString())
                    alarmExhibitionRecyclerViewAdapter.dataList = dataListExhibition
                    alarmExhibitionRecyclerViewAdapter.notifyDataSetChanged()
                }
                else {
                    Log.d("*****AlarmActivity::getAlarmExhibitionResponse::Failed::", response.toString())
                }
            }
        })
    }
}
