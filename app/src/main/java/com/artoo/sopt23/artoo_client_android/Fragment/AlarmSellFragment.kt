package com.artoo.sopt23.artoo_client_android.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.artoo.sopt23.artoo_client_android.Adapter.AlarmSellRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.Data.AlarmSellData
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetAlarmSellResponse
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_alarm_sell.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class AlarmSellFragment : Fragment() {

    var dataListSell = ArrayList<AlarmSellData>()
    lateinit var alarmSellRecyclerViewAdapter: AlarmSellRecyclerViewAdapter

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_alarm_sell, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
        getAlarmSellResponse()
    }

    private fun setRecyclerView() {
        alarmSellRecyclerViewAdapter = AlarmSellRecyclerViewAdapter(activity!!, dataListSell)
        rv_fragment_alarm_sell_list.adapter = alarmSellRecyclerViewAdapter
        rv_fragment_alarm_sell_list.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL,false)
    }

    private fun getAlarmSellResponse() {
        val token = SharedPreferenceController.getAuthorization(context!!)
        val user_idx = SharedPreferenceController.getUserID(context!!)
        val getAlarmSellResponse = networkService.getAlarmSellResponse("application/json",token,user_idx)
        getAlarmSellResponse.enqueue(object : Callback<GetAlarmSellResponse> {
            override fun onFailure(call: Call<GetAlarmSellResponse>, t: Throwable) {
                Log.e("*****AlarmActivity::getAlarmSellResponse::", t.toString())
            }
            override fun onResponse(call: Call<GetAlarmSellResponse>, response: Response<GetAlarmSellResponse>) {
                if(response.isSuccessful){
                    dataListSell = response.body()!!.data
                    Log.d("*****AlarmActivity::getAlarmBuyResponse::Success", dataListSell.toString())
                    alarmSellRecyclerViewAdapter.dataListSell = dataListSell
                    alarmSellRecyclerViewAdapter.notifyDataSetChanged()
                }
                else {
                    Log.d("*****AlarmActivity::getAlarmSellResponse::Failed::", response.toString())
                }
            }
        })
    }

}