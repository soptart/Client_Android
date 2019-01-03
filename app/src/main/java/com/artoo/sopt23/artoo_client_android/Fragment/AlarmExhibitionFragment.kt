package com.artoo.sopt23.artoo_client_android.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.artoo.sopt23.artoo_client_android.Adapter.AlarmExhibitionRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Data.AlarmExhibitionData
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_alarm_exhibition.*
import java.util.ArrayList


class AlarmExhibitionFragment : Fragment() {
    lateinit var alarmExhibitionRecyclerViewAdapter: AlarmExhibitionRecyclerViewAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_alarm_exhibition, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        var dataList: ArrayList<AlarmExhibitionData> = ArrayList()
        dataList.add(AlarmExhibitionData("","","","","",""))


        alarmExhibitionRecyclerViewAdapter = AlarmExhibitionRecyclerViewAdapter(activity!!,dataList)
        rv_fragment_alarm_exhibition_list.adapter = alarmExhibitionRecyclerViewAdapter
        rv_fragment_alarm_exhibition_list.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL,false)
    }

}
