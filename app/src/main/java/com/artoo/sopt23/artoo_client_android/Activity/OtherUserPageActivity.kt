package com.artoo.sopt23.artoo_client_android.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Adapter.OtherpageFragmentStatePageAdapter
import com.artoo.sopt23.artoo_client_android.Data.OtherpageLikeData
import com.artoo.sopt23.artoo_client_android.Data.OtherpageProductData
import com.artoo.sopt23.artoo_client_android.Data.OtherpageReviewData
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetOtherpageLikeResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetOtherpagePrefInfoResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetOtherpageProductResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetOtherpageReviewResponse
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.activity_other_user_page.*
import org.jetbrains.anko.support.v4.viewPager
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtherUserPageActivity : AppCompatActivity() {

    var otherpageIntro = ""
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    var productDataList = ArrayList<OtherpageProductData>()
    var likeDataList = ArrayList<OtherpageLikeData>()
    var reviewDataList = ArrayList<OtherpageReviewData>()

    lateinit var tv_top_navi_otherpage_product_tab: TextView
    lateinit var tv_top_navi_otherpage_like_tab: TextView
    lateinit var tv_top_navi_otherpage_review_tab: TextView

    lateinit var txt_top_navi_mypage_product_cnt: TextView
    lateinit var txt_top_navi_mypage_like_cnt: TextView
    lateinit var txt_top_navi_mypage_review_cnt: TextView

    var productDataCount: Int = 0
    var likeDataCount: Int = 0
    var reviewDataCount: Int = 0

    var other_idx: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_user_page)

        other_idx = intent.getIntExtra("other_idx", -1)

        configureTopNavigation()
        setOnClickListener()
    }

    override fun onResume() {
        super.onResume()

        getOtherpageProductResponse()
        getOtherpageLikeResponse()
        getOtherpageReviewResponse()
        getOtherpagePrefInfoResponse()
    }

    private fun configureTopNavigation() {
        vp_top_navi_act_frag_pager_other_user_page.adapter = OtherpageFragmentStatePageAdapter(supportFragmentManager, 3,
                productDataList, likeDataList, reviewDataList)
        tl_top_navi_act_top_menu_other_user_page.setupWithViewPager(vp_top_navi_act_frag_pager_other_user_page)

        val topNaviLayout: View = this.layoutInflater.inflate(R.layout.top_navigation_tab_otherpage, null, false)

        tv_top_navi_otherpage_product_tab = topNaviLayout.findViewById(R.id.tv_top_navi_otherpage_product_tab)
        tv_top_navi_otherpage_like_tab = topNaviLayout.findViewById(R.id.tv_top_navi_otherpage_like_tab)
        tv_top_navi_otherpage_review_tab = topNaviLayout.findViewById(R.id.tv_top_navi_otherpage_review_tab)

        txt_top_navi_mypage_product_cnt = topNaviLayout.findViewById(R.id.tv_otherpage_user_product_cnt) as TextView
        txt_top_navi_mypage_like_cnt = topNaviLayout.findViewById(R.id.tv_otherpage_user_like_cnt) as TextView
        txt_top_navi_mypage_review_cnt = topNaviLayout.findViewById(R.id.tv_otherpage_user_review_cnt) as TextView

        txt_top_navi_mypage_product_cnt.text = productDataCount.toString()
        txt_top_navi_mypage_like_cnt.text = likeDataCount.toString()
        txt_top_navi_mypage_review_cnt.text = reviewDataCount.toString()

        tl_top_navi_act_top_menu_other_user_page.getTabAt(2)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_otherpage_review_tab) as RelativeLayout
        tl_top_navi_act_top_menu_other_user_page.getTabAt(1)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_otherpage_like_tab) as RelativeLayout
        tl_top_navi_act_top_menu_other_user_page.getTabAt(0)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_otherpage_product_tab) as RelativeLayout
        tl_top_navi_act_top_menu_other_user_page.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
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

    private fun setOnClickListener() {

    }

    private fun selectedTab(position: Int) {
        if (position == 0) {
            tv_top_navi_otherpage_product_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_top_navi_otherpage_like_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_otherpage_review_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
        } else if (position == 1){
            tv_top_navi_otherpage_product_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_otherpage_like_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_top_navi_otherpage_review_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
        } else {
            tv_top_navi_otherpage_product_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_otherpage_like_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_otherpage_review_tab.setTextColor(resources.getColor(R.color.colorEssential))
        }
    }

    private fun getOtherpageProductResponse() {
        val getOtherpageProductResponse = networkService.getOtherpageProductResponse("application/json", other_idx)
        getOtherpageProductResponse.enqueue(object: Callback<GetOtherpageProductResponse> {
            override fun onFailure(call: Call<GetOtherpageProductResponse>, t: Throwable) {
                Log.d("*****OtherpageFragment::getOtherpageProductResponse::", "List_ProductData_Failed")
            }
            override fun onResponse(call: Call<GetOtherpageProductResponse>, response: Response<GetOtherpageProductResponse>) {
                if (response.isSuccessful) {
                    productDataList = response.body()!!.data
                    productDataCount = response.body()!!.dataNum
                    Log.d("*****OtherpageFragment::getOtherpageProductResponse::Success::", productDataList.toString())
                    configureTopNavigation()
                } else {
                    Log.d("*****OtherpageFragment::getOtherpageProductResponse::Failed::", response.toString())
                }
            }
        })
    }

    private fun getOtherpageLikeResponse() {
        val getOtherpageLikeResponse = networkService.getOtherpageLikeResponse("application/json", other_idx)
        getOtherpageLikeResponse.enqueue(object: Callback<GetOtherpageLikeResponse>{
            override fun onFailure(call: Call<GetOtherpageLikeResponse>, t: Throwable) {
                Log.d("*****OtherpageFragment::getOtherpageLikeResponse::", "List_LikeData_Failed")
            }

            override fun onResponse(call: Call<GetOtherpageLikeResponse>, response: Response<GetOtherpageLikeResponse>) {
                if (response.isSuccessful) {
                    likeDataCount = response.body()!!.dataNum
                    likeDataList = response.body()!!.data
                    Log.d("*****OtherpageFragment::getOtherpageLikeResponse::Success::", likeDataList.toString())
                    configureTopNavigation()
                } else {
                    Log.d("*****OtherpageFragment::getOtherpageLikeResponse::Failed::", response.toString())
                }
            }
        })
    }

    private fun getOtherpageReviewResponse() {
        val getOtherpageReviewResponse = networkService.getOtherpageReviewResponse("application/json", other_idx)
        getOtherpageReviewResponse.enqueue(object: Callback<GetOtherpageReviewResponse> {
            override fun onFailure(call: Call<GetOtherpageReviewResponse>, t: Throwable) {
                Log.d("*****OtherpageFragment::getOtherpageReviewResponse::", "List_ReviewData_Failed")
            }
            override fun onResponse(call: Call<GetOtherpageReviewResponse>, response: Response<GetOtherpageReviewResponse>) {
                if (response.isSuccessful) {
                    reviewDataList = response.body()!!.data
                    reviewDataCount = response.body()!!.dataNum

                    Log.d("*****OtherpageFragment::getOtherpageReviewResponse::Success::", reviewDataList.toString())
                    configureTopNavigation()
                } else {
                    Log.d("*****OtherpageFragment::getOtherpageReviewResponse::Failed::", response.toString())
                }
            }
        })
    }

    private fun getOtherpagePrefInfoResponse() {
        val getOtherpagePrefInfoResponse = networkService.getOtherpagePrefInfoResponse("application/json", other_idx)
        getOtherpagePrefInfoResponse.enqueue(object: Callback<GetOtherpagePrefInfoResponse> {
            override fun onFailure(call: Call<GetOtherpagePrefInfoResponse>, t: Throwable) {
                Log.d("*****OtherpageFragment::getOtherpagePrefInfoResponse::Failed::", "Get_UserInfo_Failed")
            }
            override fun onResponse(call: Call<GetOtherpagePrefInfoResponse>, response: Response<GetOtherpagePrefInfoResponse>) {
                if (response.isSuccessful) {
                    Log.d("*****OtherpageFragment::getOtherpagePrefInfoResponse::Success::", response.body().toString())
                    if (response.body()!!.data.u_description != null)
                        otherpageIntro = response.body()!!.data.u_description
                    tv_other_user_page_user_name.text = response.body()!!.data.u_name
                    tv_otherpage_user_intro.text = otherpageIntro
                } else {
                    Log.d("*****OtherpageFragment::getOtherpagePrefInfoResponse::Failed::", response.body().toString())
                }
            }
        })
    }
}
