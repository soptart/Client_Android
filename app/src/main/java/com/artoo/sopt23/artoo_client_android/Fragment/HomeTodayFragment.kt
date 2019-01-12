package com.artoo.sopt23.artoo_client_android.Fragment

import android.content.res.AssetManager
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Activity.TodayArtooActivity
import com.artoo.sopt23.artoo_client_android.Adapter.HomeArtistFragmentStatePagerAdapter
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetTodayArtistResponse
import com.artoo.sopt23.artoo_client_android.Data.TodayArtistData
import com.artoo.sopt23.artoo_client_android.Data.TodayArtistProductData
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService

import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_home_today.*
import kotlinx.android.synthetic.main.fragment_home_today.view.*
import kotlinx.android.synthetic.main.rv_item_home_today_artist.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx

class HomeTodayFragment : Fragment() {

    var todayArtistProductData: ArrayList<TodayArtistProductData> = arrayListOf(
        TodayArtistProductData(8, "그리움", "2018", "img1.jpg"),
        TodayArtistProductData(8, "그리움", "2018", "img1.jpg")
    )
    var todayArtist: ArrayList<TodayArtistData> = arrayListOf(
        TodayArtistData(1, "김다영", "2019 최고의 작가", "동덕여자대학교", todayArtistProductData),
        TodayArtistData(1, "김다영", "2019 최고의 작가", "동덕여자대학교", todayArtistProductData),
        TodayArtistData(1, "김다영", "2019 최고의 작가", "동덕여자대학교", todayArtistProductData),
        TodayArtistData(1, "김다영", "v2019 최고의 작가", "동덕여자대학교", todayArtistProductData),
        TodayArtistData(1, "김다영", "2019 최고의 작가", "동덕여자대학교", todayArtistProductData)
    )

    lateinit var txt_top_navi_first_arti_tab: TextView
    lateinit var txt_top_navi_second_arti_tab: TextView
    lateinit var txt_top_navi_third_arti_tab: TextView
    lateinit var txt_top_navi_fourth_arti_tab: TextView
    lateinit var txt_top_navi_fifth_arti_tab: TextView

    lateinit var inflater: LayoutInflater
    lateinit var homeArtistFragmentStatePagerAdapter: HomeArtistFragmentStatePagerAdapter


    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureTopNavigation()
        setOnClickListener()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.inflater = inflater

        return inflater!!.inflate(R.layout.fragment_home_today, container, false)
    }

    override fun onResume() {
        super.onResume()
        getTodayArtist()
    }

    fun getTodayArtist() {
        val getTodayArtistResponse = networkService.getTodayArtistResponse()
        getTodayArtistResponse.enqueue(object : Callback<GetTodayArtistResponse> {
            override fun onFailure(call: Call<GetTodayArtistResponse>, t: Throwable) {
                Log.e("board list fail", t.toString())
            }

            override fun onResponse(call: Call<GetTodayArtistResponse>, response: Response<GetTodayArtistResponse>) {
                if(response.isSuccessful){
                    // todayArtist = response.body()!!.data
                    for (i in 0..response.body()!!.data.size - 1){
                        todayArtist[i] = response.body()!!.data[i]
                    }
                    updateTopNavigation()
                }
            }
        })
    }

    fun updateTopNavigation(){
        txt_top_navi_first_arti_tab.text = todayArtist[0].u_name
        txt_top_navi_second_arti_tab.text = todayArtist[1].u_name
        txt_top_navi_third_arti_tab.text = todayArtist[2].u_name
        txt_top_navi_fourth_arti_tab.text = todayArtist[3].u_name
        txt_top_navi_fifth_arti_tab.text = todayArtist[4].u_name
        homeArtistFragmentStatePagerAdapter.todayArtistList = todayArtist
        homeArtistFragmentStatePagerAdapter.updateData()
    }

    private fun configureTopNavigation() {
        //Artist Tab

        val topNaviLayout = inflater.inflate(R.layout.top_navigation_tab_home_artist, null, false)
        homeArtistFragmentStatePagerAdapter = HomeArtistFragmentStatePagerAdapter(childFragmentManager, 5, todayArtist)
        vp_top_navi_act_frag_pager_home_artist.adapter = homeArtistFragmentStatePagerAdapter
        vp_top_navi_act_frag_pager_home_artist.offscreenPageLimit = 5
        tl_top_navi_act_top_menu_home_artist.setupWithViewPager(view!!.vp_top_navi_act_frag_pager_home_artist)

        txt_top_navi_first_arti_tab = topNaviLayout.findViewById(R.id.tv_top_navi_first_arti_tab)
        txt_top_navi_second_arti_tab = topNaviLayout.findViewById(R.id.tv_top_navi_second_arti_tab)
        txt_top_navi_third_arti_tab = topNaviLayout.findViewById(R.id.tv_top_navi_third_arti_tab)
        txt_top_navi_fourth_arti_tab = topNaviLayout.findViewById(R.id.tv_top_navi_fourth_arti_tab)
        txt_top_navi_fifth_arti_tab = topNaviLayout.findViewById(R.id.tv_top_navi_fifth_arti_tab)
        txt_top_navi_first_arti_tab.text = todayArtist[0].u_name
        txt_top_navi_second_arti_tab.text = todayArtist[1].u_name
        txt_top_navi_third_arti_tab.text = todayArtist[2].u_name
        txt_top_navi_fourth_arti_tab.text = todayArtist[3].u_name
        txt_top_navi_fifth_arti_tab.text = todayArtist[4].u_name
        tl_top_navi_act_top_menu_home_artist.getTabAt(4)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_fifth_arti_tab) as RelativeLayout
        tl_top_navi_act_top_menu_home_artist.getTabAt(3)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_fourth_arti_tab) as RelativeLayout
        tl_top_navi_act_top_menu_home_artist.getTabAt(2)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_third_arti_tab) as RelativeLayout
        tl_top_navi_act_top_menu_home_artist.getTabAt(1)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_second_arti_tab) as RelativeLayout
        tl_top_navi_act_top_menu_home_artist.getTabAt(0)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_first_arti_tab) as RelativeLayout
        tl_top_navi_act_top_menu_home_artist.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
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
            txt_top_navi_first_arti_tab.setTextColor(resources.getColor(R.color.colorEssential))
            txt_top_navi_second_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            txt_top_navi_third_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            txt_top_navi_fourth_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            txt_top_navi_fifth_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            txt_top_navi_first_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_bold.otf"))
            txt_top_navi_second_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_regular.otf"))
            txt_top_navi_third_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_regular.otf"))
            txt_top_navi_fourth_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_regular.otf"))
            txt_top_navi_fifth_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_regular.otf"))
//            txt_top_navi_first_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_bold))
//            txt_top_navi_second_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            txt_top_navi_third_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            txt_top_navi_fourth_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            txt_top_navi_fifth_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
        } else if (position == 1) {
            txt_top_navi_first_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            txt_top_navi_second_arti_tab.setTextColor(resources.getColor(R.color.colorEssential))
            txt_top_navi_third_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            txt_top_navi_fourth_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            txt_top_navi_fifth_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            txt_top_navi_first_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_regular.otf"))
            txt_top_navi_second_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_bold.otf"))
            txt_top_navi_third_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_regular.otf"))
            txt_top_navi_fourth_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_regular.otf"))
            txt_top_navi_fifth_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_regular.otf"))
//            txt_top_navi_first_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            txt_top_navi_second_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_bold))
//            txt_top_navi_third_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            txt_top_navi_fourth_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            txt_top_navi_fifth_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
        } else if (position == 2) {
            txt_top_navi_first_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            txt_top_navi_second_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            txt_top_navi_third_arti_tab.setTextColor(resources.getColor(R.color.colorEssential))
            txt_top_navi_fourth_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            txt_top_navi_fifth_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            txt_top_navi_first_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_regular.otf"))
            txt_top_navi_second_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_regular.otf"))
            txt_top_navi_third_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_bold.otf"))
            txt_top_navi_fourth_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_regular.otf"))
            txt_top_navi_fifth_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_regular.otf"))
//            txt_top_navi_first_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            txt_top_navi_second_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            txt_top_navi_third_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_bold))
//            txt_top_navi_fourth_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            txt_top_navi_fifth_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
        } else if (position == 3) {
            txt_top_navi_first_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            txt_top_navi_second_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            txt_top_navi_third_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            txt_top_navi_fourth_arti_tab.setTextColor(resources.getColor(R.color.colorEssential))
            txt_top_navi_fifth_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            txt_top_navi_first_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_regular.otf"))
            txt_top_navi_second_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_regular.otf"))
            txt_top_navi_third_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_regular.otf"))
            txt_top_navi_fourth_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_bold.otf"))
            txt_top_navi_fifth_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_regular.otf"))
//            txt_top_navi_first_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            txt_top_navi_second_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            txt_top_navi_third_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            txt_top_navi_fourth_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            txt_top_navi_fifth_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
        } else {
            txt_top_navi_first_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            txt_top_navi_second_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            txt_top_navi_third_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            txt_top_navi_fourth_arti_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            txt_top_navi_fifth_arti_tab.setTextColor(resources.getColor(R.color.colorEssential))
            txt_top_navi_first_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_regular.otf"))
            txt_top_navi_second_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_regular.otf"))
            txt_top_navi_third_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_regular.otf"))
            txt_top_navi_fourth_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_regular.otf"))
            txt_top_navi_fifth_arti_tab.setTypeface(Typeface.createFromAsset(activity!!.assets, "font/notosanscjkkr_bold.otf"))
//            txt_top_navi_first_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            txt_top_navi_second_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            txt_top_navi_third_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            txt_top_navi_fourth_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_regular))
//            txt_top_navi_fifth_arti_tab.setTypeface(resources.getFont(R.font.notosanscjkkr_bold))
        }
    }

    private fun setOnClickListener() {
        btn_home_first_ticket.setOnClickListener {
            try {
                ctx.startActivity<TodayArtooActivity>("c_id" to 1)
            } catch (e: Exception) {
            }
        }
        btn_home_second_ticket.setOnClickListener {
            try {
                ctx.startActivity<TodayArtooActivity>("c_id" to 2)
            } catch (e: Exception) {
            }
        }
    }
}