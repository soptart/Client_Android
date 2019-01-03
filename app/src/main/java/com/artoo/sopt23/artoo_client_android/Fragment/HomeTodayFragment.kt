package com.artoo.sopt23.artoo_client_android.Fragment

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Adapter.HomeArtistFragmentStatePagerAdapter
import com.artoo.sopt23.artoo_client_android.Data.TodayArtistData

import com.artoo.sopt23.artoo_client_android.R
import com.artoo.sopt23.artoo_client_android.R.id.btn_home_first_ticket
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_home_today.*
import kotlinx.android.synthetic.main.fragment_home_today.view.*
import kotlinx.android.synthetic.main.top_navigation_tab_home_artist.*

class HomeTodayFragment : Fragment() {

    lateinit var todayArtistData: ArrayList<TodayArtistData>
    lateinit var inflater: LayoutInflater
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.inflater = inflater
        val view: View = inflater!!.inflate(R.layout.fragment_home_today, container, false)

        configureTopNavigation(view)
        setOnClickListener(view)

        return view
    }

    private fun configureTopNavigation(view: View) {

        //Artist Tab
        view.vp_top_navi_act_frag_pager_home_artist.adapter = HomeArtistFragmentStatePagerAdapter(childFragmentManager, 5, todayArtistData)
        view.tl_top_navi_act_top_menu_home_artist.setupWithViewPager(view.vp_top_navi_act_frag_pager_home_artist)

        val topNaviLayout : View = inflater.inflate(R.layout.top_navigation_tab_home_artist, null, false)
        view.tl_top_navi_act_top_menu_home_artist.getTabAt(4)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_fifth_arti_tab) as RelativeLayout
        view.tl_top_navi_act_top_menu_home_artist.getTabAt(3)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_fourth_arti_tab) as RelativeLayout
        view.tl_top_navi_act_top_menu_home_artist.getTabAt(2)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_third_arti_tab) as RelativeLayout
        view.tl_top_navi_act_top_menu_home_artist.getTabAt(1)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_second_arti_tab) as RelativeLayout
        view.tl_top_navi_act_top_menu_home_artist.getTabAt(0)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_first_arti_tab) as RelativeLayout
        view.tl_top_navi_act_top_menu_home_artist.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                selectedArtistTab(position = tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                selectedArtistTab(position = tab!!.position)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                selectedArtistTab(position = tab!!.position)
            }
        })

    }

    //Artist Tab Layout Setting
    private fun selectedArtistTab(position: Int) {
        if (position == 0) {
            tv_top_navi_first_arti_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_top_navi_second_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_third_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_fourth_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_fifth_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
        } else if (position == 1) {
            tv_top_navi_first_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_second_arti_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_top_navi_third_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_fourth_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_fifth_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
        } else if (position == 2) {
            tv_top_navi_first_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_second_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_third_arti_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_top_navi_fourth_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_fifth_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
        } else if (position == 3) {
            tv_top_navi_first_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_second_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_third_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_fourth_arti_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_top_navi_fifth_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
        } else {
            tv_top_navi_first_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_second_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_third_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_fourth_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_fifth_arti_tab.setTextColor(resources.getColor(R.color.colorEssential))
        }
    }

    private fun setOnClickListener(view: View) {
        view.btn_home_first_ticket.setOnClickListener {
            //startActivity:fullImage
        }
        view.btn_home_second_ticket.setOnClickListener {
            //startActivity:fullImage
        }
    }
}
