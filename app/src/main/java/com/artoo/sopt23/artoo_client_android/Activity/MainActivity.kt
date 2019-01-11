package com.artoo.sopt23.artoo_client_android.Activity

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import com.artoo.sopt23.artoo_client_android.Adapter.MainFragmentStatePagerAdapter
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetTodayArtistResponse
import com.artoo.sopt23.artoo_client_android.Data.TodayArtistData
import com.artoo.sopt23.artoo_client_android.Data.TodayArtistProductData
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_navigation_tab.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureBottomNavigation()
    }

    private fun configureBottomNavigation() {

        vp_bottom_navi_act_frag_pager.offscreenPageLimit=4
        vp_bottom_navi_act_frag_pager.adapter = MainFragmentStatePagerAdapter(
            supportFragmentManager,
            4
        ) //vp_bottom_navi_act_frag_pager.offscreenPageLimit = 3


        // ViewPager와 Tablayout을 엮어줍니다!
        tl_bottom_navi_act_bottom_menu.setupWithViewPager(vp_bottom_navi_act_frag_pager)
        //TabLayout에 붙일 layout을 찾아준 다음
        val bottomNaviLayout: View = this.layoutInflater.inflate(R.layout.bottom_navigation_tab, null, false)
        //탭 하나하나 TabLayout에 연결시켜줍니다.
        tl_bottom_navi_act_bottom_menu.getTabAt(0)!!.customView =
                bottomNaviLayout.findViewById(R.id.btn_bottom_navi_home_tab) as RelativeLayout
        tl_bottom_navi_act_bottom_menu.getTabAt(1)!!.customView =
                bottomNaviLayout.findViewById(R.id.btn_bottom_navi_product_tab) as RelativeLayout
        tl_bottom_navi_act_bottom_menu.getTabAt(2)!!.customView =
                bottomNaviLayout.findViewById(R.id.btn_bottom_navi_exhibition_tab) as RelativeLayout
        tl_bottom_navi_act_bottom_menu.getTabAt(3)!!.customView =
                bottomNaviLayout.findViewById(R.id.btn_bottom_navi_mypage_tab) as RelativeLayout
        tl_bottom_navi_act_bottom_menu.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                selectedMainTab(position = tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                selectedMainTab(position = tab!!.position)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                selectedMainTab(position = tab!!.position)
            }
        })

    }
    private fun selectedMainTab(position: Int) {
        if (position == 0) {
            tv_bottom_navi_home_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_bottom_navi_product_tab.setTextColor(resources.getColor(R.color.colorMainNoSelect))
            tv_bottom_navi_exhibition_tab.setTextColor(resources.getColor(R.color.colorMainNoSelect))
            tv_bottom_navi_my_tab.setTextColor(resources.getColor(R.color.colorMainNoSelect))
            tv_bottom_navi_home_tab.setTypeface(Typeface.createFromAsset(assets, "font/notosanscjkkr_medium.otf"))
            tv_bottom_navi_product_tab.setTypeface(Typeface.createFromAsset(assets, "font/notosanscjkkr_regular.otf"))
            tv_bottom_navi_exhibition_tab.setTypeface(Typeface.createFromAsset(assets, "font/notosanscjkkr_regular.otf"))
            tv_bottom_navi_my_tab.setTypeface(Typeface.createFromAsset(assets, "font/notosanscjkkr_regular.otf"))
        }
        else if (position == 1) {
            tv_bottom_navi_home_tab.setTextColor(resources.getColor(R.color.colorMainNoSelect))
            tv_bottom_navi_product_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_bottom_navi_exhibition_tab.setTextColor(resources.getColor(R.color.colorMainNoSelect))
            tv_bottom_navi_my_tab.setTextColor(resources.getColor(R.color.colorMainNoSelect))
            tv_bottom_navi_home_tab.setTypeface(Typeface.createFromAsset(assets, "font/notosanscjkkr_regular.otf"))
            tv_bottom_navi_product_tab.setTypeface(Typeface.createFromAsset(assets, "font/notosanscjkkr_medium.otf"))
            tv_bottom_navi_exhibition_tab.setTypeface(Typeface.createFromAsset(assets, "font/notosanscjkkr_regular.otf"))
            tv_bottom_navi_my_tab.setTypeface(Typeface.createFromAsset(assets, "font/notosanscjkkr_regular.otf"))
        }
        else if (position == 2) {
            tv_bottom_navi_home_tab.setTextColor(resources.getColor(R.color.colorMainNoSelect))
            tv_bottom_navi_product_tab.setTextColor(resources.getColor(R.color.colorMainNoSelect))
            tv_bottom_navi_exhibition_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_bottom_navi_my_tab.setTextColor(resources.getColor(R.color.colorMainNoSelect))
            tv_bottom_navi_home_tab.setTypeface(Typeface.createFromAsset(assets, "font/notosanscjkkr_regular.otf"))
            tv_bottom_navi_product_tab.setTypeface(Typeface.createFromAsset(assets, "font/notosanscjkkr_regular.otf"))
            tv_bottom_navi_exhibition_tab.setTypeface(Typeface.createFromAsset(assets, "font/notosanscjkkr_medium.otf"))
            tv_bottom_navi_my_tab.setTypeface(Typeface.createFromAsset(assets, "font/notosanscjkkr_regular.otf"))
//            tv_bottom_navi_home_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            tv_bottom_navi_product_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            tv_bottom_navi_exhibition_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_medium))
//            tv_bottom_navi_my_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
        }
        else if (position == 3) {
            tv_bottom_navi_home_tab.setTextColor(resources.getColor(R.color.colorMainNoSelect))
            tv_bottom_navi_product_tab.setTextColor(resources.getColor(R.color.colorMainNoSelect))
            tv_bottom_navi_exhibition_tab.setTextColor(resources.getColor(R.color.colorMainNoSelect))
            tv_bottom_navi_my_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_bottom_navi_home_tab.setTypeface(Typeface.createFromAsset(assets, "font/notosanscjkkr_regular.otf"))
            tv_bottom_navi_product_tab.setTypeface(Typeface.createFromAsset(assets, "font/notosanscjkkr_regular.otf"))
            tv_bottom_navi_exhibition_tab.setTypeface(Typeface.createFromAsset(assets, "font/notosanscjkkr_regular.otf"))
            tv_bottom_navi_my_tab.setTypeface(Typeface.createFromAsset(assets, "font/notosanscjkkr_medium.otf"))
//            tv_bottom_navi_home_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            tv_bottom_navi_product_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            tv_bottom_navi_exhibition_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            tv_bottom_navi_my_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_medium))
        }
    }

}
