package com.artoo.sopt23.artoo_client_android.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.View
import android.widget.RelativeLayout
import com.artoo.sopt23.artoo_client_android.Adapter.AlarmFragmentStatePagerAdapter
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.activity_alarm.*
import kotlinx.android.synthetic.main.top_navigation_tab_alarm.*
import org.jetbrains.anko.find
import java.util.ArrayList

class AlarmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        configureTopNavigation()
    }
    private fun configureTopNavigation() {
        vp_top_navi_alarm_act_frag_pager.adapter = AlarmFragmentStatePagerAdapter(
                supportFragmentManager,
                3
        )

// ViewPager와 Tablayout을 엮어줍니다!
        tl_top_navi_alarm_act_top_menu.setupWithViewPager(vp_top_navi_alarm_act_frag_pager)
//TabLayout에 붙일 layout을 찾아준 다음
        val topNaviLayout: View = this.layoutInflater.inflate(R.layout.top_navigation_tab_alarm, null, false)
//탭 하나하나 TabLayout에 연결시켜줍니다.
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
