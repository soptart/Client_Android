package com.artoo.sopt23.artoo_client_android.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.artoo.sopt23.artoo_client_android.Adapter.AlarmSellRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Data.AlarmSellData
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_alarm_sell.*
import java.util.ArrayList

class AlarmSellFragment : Fragment() {
    lateinit var alarmSellRecyclerViewAdapter: AlarmSellRecyclerViewAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_alarm_sell, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        var dataListSell: ArrayList<AlarmSellData> = ArrayList()
        dataListSell.add(AlarmSellData(true,"","","","","","",""))
        dataListSell.add(AlarmSellData(false,"","","","","","",""))


        alarmSellRecyclerViewAdapter = AlarmSellRecyclerViewAdapter(activity!!,dataListSell)
        rv_fragment_alarm_sell_list.adapter = alarmSellRecyclerViewAdapter
        rv_fragment_alarm_sell_list.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL,false)
    }
}
