package com.artoo.sopt23.artoo_client_android.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.artoo.sopt23.artoo_client_android.Adapter.AlarmBuyRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Data.AlarmBuyData
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_alarm_buy.*
import java.util.ArrayList

class AlarmBuyFragment : Fragment() {

    lateinit var alarmBuyData:ArrayList<AlarmBuyData>
    lateinit var alarmBuyRecyclerViewAdapter: AlarmBuyRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater!!.inflate(R.layout.fragment_alarm_buy, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
    }


    private fun setRecyclerView() {
        alarmBuyRecyclerViewAdapter = AlarmBuyRecyclerViewAdapter(activity!!, alarmBuyData)
        rv_fragment_alarm_buy_list.adapter = alarmBuyRecyclerViewAdapter
        rv_fragment_alarm_buy_list.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL,false)
    }
}