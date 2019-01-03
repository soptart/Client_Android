package com.artoo.sopt23.artoo_client_android.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.artoo.sopt23.artoo_client_android.Adapter.MypageFragmentStatePageAdapter

import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_mypage.*
import kotlinx.android.synthetic.main.fragment_mypage.view.*
import kotlinx.android.synthetic.main.top_navigation_tab_mypage.*

class MypageFragment : Fragment() {

    lateinit var inflater: LayoutInflater

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.inflater = inflater

        var view: View = inflater.inflate(R.layout.fragment_mypage, container, false)
        configureTopNavigation(view)

        view.btn_mypage_update_intro.setOnClickListener{
            tv_mypage_user_intro.visibility = View.GONE
            et_mypage_user_intro.visibility = View.VISIBLE
            btn_mypage_update_intro_finish.visibility = View.VISIBLE
        }
        view.btn_mypage_update_intro_finish.setOnClickListener {
            tv_mypage_user_intro.setText(et_mypage_user_intro.text.toString())
            tv_mypage_user_intro.visibility = View.VISIBLE
            et_mypage_user_intro.visibility = View.GONE
            btn_mypage_update_intro_finish.visibility = View.GONE
        }

        return view
    }

    private fun configureTopNavigation(view: View) {
        view.vp_top_navi_act_frag_pager_mypage.adapter = MypageFragmentStatePageAdapter(childFragmentManager, 4)
        view.tl_top_navi_act_top_menu_mypage.setupWithViewPager(view.vp_top_navi_act_frag_pager_mypage)

        val topNaviLayout : View = inflater.inflate(R.layout.top_navigation_tab_mypage, null, false)
        view.tl_top_navi_act_top_menu_mypage.getTabAt(3)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_mypage_review_tab) as RelativeLayout
        view.tl_top_navi_act_top_menu_mypage.getTabAt(2)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_mypage_deal_tab) as RelativeLayout
        view.tl_top_navi_act_top_menu_mypage.getTabAt(1)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_mypage_like_tab) as RelativeLayout
        view.tl_top_navi_act_top_menu_mypage.getTabAt(0)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_mypage_product_tab) as RelativeLayout
        view.tl_top_navi_act_top_menu_mypage.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                selectedTab(position = tab!!.position)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                selectedTab(position = tab!!.position)
            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                selectedTab(position = tab!!.position)
            }
        })

    }

    private fun selectedTab(position: Int) {
        if (position == 0) {
            tv_top_navi_mypage_product_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_top_navi_mypage_like_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_mypage_deal_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_mypage_review_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
        } else if (position == 1){
            tv_top_navi_mypage_product_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_mypage_like_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_top_navi_mypage_deal_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_mypage_review_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
        } else if (position == 2) {
            tv_top_navi_mypage_product_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_mypage_like_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_mypage_deal_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_top_navi_mypage_review_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
        } else {
            tv_top_navi_mypage_product_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_mypage_like_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_mypage_deal_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_mypage_review_tab.setTextColor(resources.getColor(R.color.colorEssential))
        }

    }

}
