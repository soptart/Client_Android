package com.artoo.sopt23.artoo_client_android.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.artoo.sopt23.artoo_client_android.Adapter.HomeTodayRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Data.TodayArtistData
import com.artoo.sopt23.artoo_client_android.Data.TodayArtistProductData
import com.artoo.sopt23.artoo_client_android.Data.TodayMainData

import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_home_artist2.*


class HomeArtist2Fragment : Fragment() {

    var todayArtistProductData: ArrayList<TodayArtistProductData> = arrayListOf(
            TodayArtistProductData(8, "그리움", "2018", "img1.jpg"),
            TodayArtistProductData(8, "그리움", "2018", "img1.jpg")
    )
    var todayArtistData: TodayArtistData = TodayArtistData(1, "김다영", "2019 최고의 작가", "동덕여자대학교", todayArtistProductData)
    lateinit var homeTodayRecyclerViewAdapter: HomeTodayRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(view == null) return inflater.inflate(R.layout.fragment_home_artist2, container, false)
        else return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
        val snapHelper: LinearSnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv_fragment_home_today_artist2_list)
    }

    private fun setRecyclerView() {
        var todayArtistProductData: ArrayList<TodayArtistProductData> = todayArtistData.list
        var todayMainData = TodayMainData(todayArtistData.u_idx, todayArtistData.u_school, todayArtistData.u_name + "작가", todayArtistData.u_description, todayArtistProductData[0].pic_url)

        homeTodayRecyclerViewAdapter = HomeTodayRecyclerViewAdapter(activity!!,todayMainData, todayArtistProductData)
        rv_fragment_home_today_artist2_list.adapter = homeTodayRecyclerViewAdapter
        rv_fragment_home_today_artist2_list.layoutManager = LinearLayoutManager(activity,LinearLayout.HORIZONTAL,false)
    }

    fun updateData(){
        try {
            var todayArtistProductData: ArrayList<TodayArtistProductData> = todayArtistData.list
            var todayMainData = TodayMainData(todayArtistData.u_idx, todayArtistData.u_school, todayArtistData.u_name + "작가", todayArtistData.u_description, todayArtistProductData[0].pic_url)
            homeTodayRecyclerViewAdapter.dataMain = todayMainData
            homeTodayRecyclerViewAdapter.dataListArtistProduct = todayArtistProductData
            homeTodayRecyclerViewAdapter.notifyDataSetChanged()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}