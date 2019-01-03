package com.artoo.sopt23.artoo_client_android.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val PRODUCT_FRAGMENT: Int = 1

    public var todayArtistProductData: ArrayList<TodayArtistProductData> = arrayListOf(
        TodayArtistProductData(8, "그리움", 2018, "img1.jpg"),
        TodayArtistProductData(8, "그리움", 2018, "img1.jpg")
    )

    public var todayArtist: ArrayList<TodayArtistData> = arrayListOf(
        TodayArtistData(1, "김다영", "2019 최고의 작가", "동덕여자대학교", todayArtistProductData),
        TodayArtistData(1, "김다영", "2019 최고의 작가", "동덕여자대학교", todayArtistProductData),
        TodayArtistData(1, "김다영", "2019 최고의 작가", "동덕여자대학교", todayArtistProductData),
        TodayArtistData(1, "김다영", "2019 최고의 작가", "동덕여자대학교", todayArtistProductData),
        TodayArtistData(1, "김다영", "2019 최고의 작가", "동덕여자대학교", todayArtistProductData)
    )

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    public var filter_size: String? = null
    public var filter_type: String? = null
    public var filter_category: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getTodayArtist()
    }

    fun getTodayArtist() {
        Log.v("*****MainActivity::getTodayArtist()::", todayArtist.toString())
        val getTodayArtistResponse = networkService.getTodayArtistResponse()
        getTodayArtistResponse.enqueue(object : Callback<GetTodayArtistResponse> {
            override fun onFailure(call: Call<GetTodayArtistResponse>, t: Throwable) {
                Log.e("board list fail", t.toString())
            }

            override fun onResponse(call: Call<GetTodayArtistResponse>, response: Response<GetTodayArtistResponse>) {
                if(response.isSuccessful){
                    //todayArtist = response.body()!!.data
                    configureBottomNavigation()
                }
            }

        })
    }

    private fun configureBottomNavigation() {
        Log.d("*****MainActivity::configureBottomNavi()::", todayArtist.toString())

        vp_bottom_navi_act_frag_pager.adapter = MainFragmentStatePagerAdapter(
            supportFragmentManager,
            4,
            todayArtist
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
    }
}
