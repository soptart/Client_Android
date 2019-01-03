package com.artoo.sopt23.artoo_client_android.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.artoo.sopt23.artoo_client_android.Activity.MainActivity
import com.artoo.sopt23.artoo_client_android.Adapter.HomeTodayRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Data.TodayArtistData
import com.artoo.sopt23.artoo_client_android.Data.TodayArtistProductData
import com.artoo.sopt23.artoo_client_android.Data.TodayMainData

import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_home_artist1.*


class HomeArtist1Fragment : Fragment() {

    lateinit var todayArtistData: TodayArtistData
    lateinit var homeTodayRecyclerViewAdapter: HomeTodayRecyclerViewAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_artist1, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
        val snapHelper: LinearSnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv_fragment_home_today_artist1_list)
    }

    private fun setRecyclerView() {

        Log.v("*****HomeArtistFrag1::setRecyclerView::1::", todayArtistData.toString())

        var todayArtistProductData: ArrayList<TodayArtistProductData> = todayArtistData.list
        var todayMainData = TodayMainData(todayArtistData.u_school, todayArtistData.u_name + "작가", todayArtistData.u_description, todayArtistProductData[0].pic_url)

        homeTodayRecyclerViewAdapter = HomeTodayRecyclerViewAdapter(activity!!, todayMainData, todayArtistProductData)

        rv_fragment_home_today_artist1_list.adapter = homeTodayRecyclerViewAdapter
        rv_fragment_home_today_artist1_list.layoutManager = LinearLayoutManager(activity,LinearLayout.HORIZONTAL,false)
    }
}