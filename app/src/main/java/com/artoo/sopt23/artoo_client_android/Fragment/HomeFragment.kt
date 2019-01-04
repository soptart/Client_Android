package com.artoo.sopt23.artoo_client_android.Fragment

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.artoo.sopt23.artoo_client_android.Adapter.HomeFragmentStatePagerAdapter
import com.artoo.sopt23.artoo_client_android.Data.TodayArtistData

import com.artoo.sopt23.artoo_client_android.R
import com.artoo.sopt23.artoo_client_android.R.id.tl_top_navi_act_top_menu
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.top_navigation_tab_home.*
import kotlinx.android.synthetic.main.top_navigation_tab_home_artist.*
import java.text.FieldPosition

class HomeFragment : Fragment() {
    lateinit var inflater: LayoutInflater
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.inflater = inflater

        var view: View = inflater.inflate(R.layout.fragment_home, container, false)

        configureTopNavigation(view)
        return view
    }

    private fun configureTopNavigation(view: View) {

        //Home Tab
        view.vp_top_navi_act_frag_pager.offscreenPageLimit = 2
        view.vp_top_navi_act_frag_pager.adapter = HomeFragmentStatePagerAdapter(childFragmentManager, 2)
        view.tl_top_navi_act_top_menu.setupWithViewPager(view.vp_top_navi_act_frag_pager)

        val topNaviLayout : View = inflater.inflate(R.layout.top_navigation_tab_home, null, false)
        view.tl_top_navi_act_top_menu.getTabAt(1)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_home_theme_tab) as RelativeLayout
        view.tl_top_navi_act_top_menu.getTabAt(0)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_home_today_tab) as RelativeLayout
        view.tl_top_navi_act_top_menu.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                selectedHomeTab(position = tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                selectedHomeTab(position = tab!!.position)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                selectedHomeTab(position = tab!!.position)
            }
        })

    }

    //Home Tab Indicator Setting
    private fun selectedHomeTab(position: Int) {
        if (position == 0) {
            tv_top_navi_home_today_tab.setTextColor(resources.getColor(R.color.colorMainBlack))
            tv_top_navi_home_theme_tab.setTextColor(resources.getColor(R.color.colorMainGrey))
        } else {
            tv_top_navi_home_today_tab.setTextColor(resources.getColor(R.color.colorMainGrey))
            tv_top_navi_home_theme_tab.setTextColor(resources.getColor(R.color.colorMainBlack))
        }
    }

}
