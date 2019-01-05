package com.artoo.sopt23.artoo_client_android.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.artoo.sopt23.artoo_client_android.Activity.AlarmActivity
import com.artoo.sopt23.artoo_client_android.Adapter.MypageFragmentStatePageAdapter
import com.artoo.sopt23.artoo_client_android.Activity.MypagePreferencesActivity
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.Data.MypageDealData
import com.artoo.sopt23.artoo_client_android.Data.MypageProductData
import com.artoo.sopt23.artoo_client_android.Data.MypageReviewData
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetMypageDealResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetMypageProductResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetMypageReviewResponse
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService

import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_mypage.*
import kotlinx.android.synthetic.main.fragment_mypage.view.*
import kotlinx.android.synthetic.main.top_navigation_tab_mypage.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageFragment : Fragment() {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }
    //val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJEb0lUU09QVCIsInVzZXJfaWR4IjoxMiwiZXhwIjoxNTQ5MzAyOTQ0fQ.zDjqGFdhkf0qL12UxCPaFFfjg6SLSnOp52hTmT5WwMA"

    var productDataList = ArrayList<MypageProductData>()
    var dealDataList = ArrayList<MypageDealData>()
    var reviewDataList = ArrayList<MypageReviewData>()

    lateinit var inflater: LayoutInflater
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.inflater = inflater

        var view: View = inflater.inflate(R.layout.fragment_mypage, container, false)

        getMypageDealResponse(view)
        getMypageReviewResponse(view)

        return view
    }

    private fun configureTopNavigation(view: View) {
        view.vp_top_navi_act_frag_pager_mypage.adapter = MypageFragmentStatePageAdapter(childFragmentManager, 4, dealDataList, reviewDataList)
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

    private fun setOnClickListener(view: View) {
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
        view.btn_mypage_alert.setOnClickListener {
            val intent = Intent(context, AlarmActivity::class.java)
            startActivity(intent)
        }
        view.btn_mypage_setting.setOnClickListener {
            val intent = Intent(activity?.applicationContext, MypagePreferencesActivity::class.java)
            startActivity(intent)
        }
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

    private fun getMypageProductResponse(view: View) {
        val token = SharedPreferenceController.getAuthorization(context!!)
        val u_idx = SharedPreferenceController.getUserID(context!!)
        val getMypageProductResponse = networkService.getMypageProductResponse("application/json", token, u_idx)
        getMypageProductResponse.enqueue(object: Callback<GetMypageProductResponse> {
            override fun onFailure(call: Call<GetMypageProductResponse>, t: Throwable) {
                Log.d("*****MypageFragment::getMypageProductResponse::", "List_ProductData_Failed")
            }
            override fun onResponse(call: Call<GetMypageProductResponse>, response: Response<GetMypageProductResponse>) {
                if (response.isSuccessful) {
                    productDataList = response.body()!!.data
                    Log.d("*****MypageFragment::getMypageProductResponse::Success::", productDataList.toString())
                    configureTopNavigation(view)
                    setOnClickListener(view)
                } else {
                    Log.d("*****MypageFragment::getMypageProductResponse::Failed::", response.toString())
                }
            }
        })
    }

    private fun getMypageDealResponse(view: View) {
        val token = SharedPreferenceController.getAuthorization(context!!)
        val u_idx = SharedPreferenceController.getUserID(context!!)
        val getMypageDealResponse = networkService.getMypageDealResponse("application/json", token, u_idx)
        getMypageDealResponse.enqueue(object: Callback<GetMypageDealResponse> {
            override fun onFailure(call: Call<GetMypageDealResponse>, t: Throwable) {
                Log.d("*****MypageFragment::getMypageDealResponse::", "List_DealData_Failed")
            }

            override fun onResponse(call: Call<GetMypageDealResponse>, response: Response<GetMypageDealResponse>) {
                if (response.isSuccessful) {
                    dealDataList = response.body()!!.data
                    Log.d("*****MypageFragment::getMypageDealResponse::Success::", dealDataList.toString())
                    configureTopNavigation(view)
                    setOnClickListener(view)
                } else {
                    Log.d("*****MypageFragment::getMypageDealResponse::Failure::", response.toString())
                }
            }
        })
    }

    private fun getMypageReviewResponse(view: View) {
        val token = SharedPreferenceController.getAuthorization(context!!)
        val u_idx = SharedPreferenceController.getUserID(context!!)
        val getMypageReviewResponse = networkService.getMypageReviewResponse("application/json", token, u_idx)
        getMypageReviewResponse.enqueue(object: Callback<GetMypageReviewResponse> {
            override fun onFailure(call: Call<GetMypageReviewResponse>, t: Throwable) {
                Log.d("*****MypageFragment::getMypageReviewResponse::", "List_ReviewData_Failed")
            }
            override fun onResponse(call: Call<GetMypageReviewResponse>, response: Response<GetMypageReviewResponse>) {
                if (response.isSuccessful) {
                    reviewDataList = response.body()!!.data
                    Log.d("*****MypageFragment::getMypageDealResponse::Success::", reviewDataList.toString())
                    configureTopNavigation(view)
                    setOnClickListener(view)
                } else {
                    Log.d("*****MypageFragment::getMypageDealResponse::Failure::", response.toString())
                }
            }
        })
    }
}
