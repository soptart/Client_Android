package com.artoo.sopt23.artoo_client_android.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.artoo.sopt23.artoo_client_android.Adapter.AlarmBuyRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.Data.AlarmBuyData
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetAlarmBuyResponse
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_alarm_buy.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class AlarmBuyFragment : Fragment() {

    var p_idx: Int = -1
    companion object {
        var instance : AlarmBuyFragment = AlarmBuyFragment()
    }

    var dataListBuy = ArrayList<AlarmBuyData>()
    lateinit var alarmBuyRecyclerViewAdapter: AlarmBuyRecyclerViewAdapter

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_alarm_buy, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
        getAlarmBuyResponse()
    }

    private fun setRecyclerView() {
        alarmBuyRecyclerViewAdapter = AlarmBuyRecyclerViewAdapter(activity!!, dataListBuy)
        rv_fragment_alarm_buy_list.adapter = alarmBuyRecyclerViewAdapter
        rv_fragment_alarm_buy_list.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL,false) as RecyclerView.LayoutManager?
    }

    private fun getAlarmBuyResponse() {
        val token = SharedPreferenceController.getAuthorization(context!!)
        val user_idx = SharedPreferenceController.getUserID(context!!)
        val getAlarmBuyResponse = networkService.getAlarmBuyResponse("application/json",token,user_idx)
        getAlarmBuyResponse.enqueue(object : Callback<GetAlarmBuyResponse> {
            override fun onFailure(call: Call<GetAlarmBuyResponse>, t: Throwable) {
                Log.e("*****AlarmActivity::getAla98[prmBuyResponse::", t.toString())
            }
            override fun onResponse(call: Call<GetAlarmBuyResponse>, response: Response<GetAlarmBuyResponse>) {
                if(response.isSuccessful){
                    dataListBuy = response.body()!!.data
                    Log.d("*****AlarmActivity::getAlarmBuyResponse::Success", dataListBuy.toString())
                    alarmBuyRecyclerViewAdapter.dataListBuy = dataListBuy
                    alarmBuyRecyclerViewAdapter.notifyDataSetChanged()
                }
                else {
                    Log.d("*****AlarmActivity::getAlarmBuyResponse::Failed::", response.toString())
                }
            }
        })
    }
}