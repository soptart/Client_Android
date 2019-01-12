package com.artoo.sopt23.artoo_client_android.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Adapter.AlarmBuyRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Adapter.AlarmExhibitionRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Adapter.AlarmFragmentStatePagerAdapter
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.Data.AlarmBuyData
import com.artoo.sopt23.artoo_client_android.Data.AlarmExhibitionData
import com.artoo.sopt23.artoo_client_android.Data.AlarmSellData
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetAlarmBuyResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetAlarmExhibitionResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetAlarmSellResponse
import com.artoo.sopt23.artoo_client_android.Fragment.AlarmBuyFragment
import com.artoo.sopt23.artoo_client_android.Fragment.AlarmExhibitionFragment
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.activity_alarm.*
import kotlinx.android.synthetic.main.top_navigation_tab_alarm.*
import org.jetbrains.anko.find
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class AlarmActivity : AppCompatActivity() {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    lateinit var tv_top_navi_alarm_buy_tab: TextView
    lateinit var tv_top_navi_alarm_sell_tab: TextView
    lateinit var tv_top_navi_alarm_exhibition_tab: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        configureTopNavigation()

        btn_alarm_x.setOnClickListener {
            try {
                finish()
            } catch (e: Exception) {
            }
        }

    }

    private fun configureTopNavigation() {
        vp_top_navi_alarm_act_frag_pager.adapter = AlarmFragmentStatePagerAdapter(
            supportFragmentManager, 3)
        tl_top_navi_alarm_act_top_menu.setupWithViewPager(vp_top_navi_alarm_act_frag_pager)

        val topNaviLayout: View = this.layoutInflater.inflate(R.layout.top_navigation_tab_alarm, null, false)

        tv_top_navi_alarm_buy_tab = topNaviLayout.findViewById(R.id.tv_top_navi_alarm_buy_tab)
        tv_top_navi_alarm_sell_tab = topNaviLayout.findViewById(R.id.tv_top_navi_alarm_sell_tab)
        tv_top_navi_alarm_exhibition_tab = topNaviLayout.findViewById(R.id.tv_top_navi_alarm_exhibition_tab)

        tl_top_navi_alarm_act_top_menu.getTabAt(0)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_alarm_buy_tab) as RelativeLayout
        tl_top_navi_alarm_act_top_menu.getTabAt(1)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_alarm_sell_tab) as RelativeLayout
        tl_top_navi_alarm_act_top_menu.getTabAt(2)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_alarm_exhibition_tab) as RelativeLayout
        tl_top_navi_alarm_act_top_menu.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                selectedAlarmTab(position = tab!!.position)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                selectedAlarmTab(position = tab!!.position)
            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                selectedAlarmTab(position = tab!!.position)
            }
        })
    }

    //Home Tab Indicator Setting
    private fun selectedAlarmTab(position: Int) {
        if (position == 0) {
            tv_top_navi_alarm_buy_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_top_navi_alarm_sell_tab.setTextColor(resources.getColor(R.color.colorMainBlack))
            tv_top_navi_alarm_exhibition_tab.setTextColor(resources.getColor(R.color.colorMainBlack))
        } else if(position == 1) {
            tv_top_navi_alarm_buy_tab.setTextColor(resources.getColor(R.color.colorMainBlack))
            tv_top_navi_alarm_sell_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_top_navi_alarm_exhibition_tab.setTextColor(resources.getColor(R.color.colorMainBlack))
        } else {
            tv_top_navi_alarm_buy_tab.setTextColor(resources.getColor(R.color.colorMainBlack))
            tv_top_navi_alarm_sell_tab.setTextColor(resources.getColor(R.color.colorMainBlack))
            tv_top_navi_alarm_exhibition_tab.setTextColor(resources.getColor(R.color.colorEssential))
        }
    }
}
