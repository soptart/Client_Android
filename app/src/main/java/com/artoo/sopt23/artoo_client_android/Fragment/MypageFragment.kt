package com.artoo.sopt23.artoo_client_android.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Activity.AlarmActivity
import com.artoo.sopt23.artoo_client_android.Adapter.MypageFragmentStatePageAdapter
import com.artoo.sopt23.artoo_client_android.Activity.MypagePreferencesActivity
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.Data.MypageDealData
import com.artoo.sopt23.artoo_client_android.Data.MypageLikeData
import com.artoo.sopt23.artoo_client_android.Data.MypageProductData
import com.artoo.sopt23.artoo_client_android.Data.MypageReviewData
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.*
import com.artoo.sopt23.artoo_client_android.Data.Response.Put.PutMypagePrefInfoResponse
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService

import com.artoo.sopt23.artoo_client_android.R
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.fragment_mypage.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageFragment : Fragment() {

    var mypageIntro: String = ""
    val jsonObject = JSONObject()

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    var productDataList = ArrayList<MypageProductData>()
    var likeDataList = ArrayList<MypageLikeData>()
    var dealDataList = ArrayList<MypageDealData>()
    var reviewDataList = ArrayList<MypageReviewData>()

    lateinit var tv_top_navi_mypage_product_tab: TextView
    lateinit var tv_top_navi_mypage_like_tab: TextView
    lateinit var tv_top_navi_mypage_deal_tab: TextView
    lateinit var tv_top_navi_mypage_review_tab: TextView

    lateinit var txt_top_navi_mypage_product_cnt: TextView
    lateinit var txt_top_navi_mypage_like_cnt: TextView
    lateinit var txt_top_navi_mypage_deal_cnt: TextView
    lateinit var txt_top_navi_mypage_review_cnt: TextView

    var productDataCount: Int = 0
    var likeDataCount: Int = 0
    var dealDataCount: Int = 0
    var reviewDataCount: Int = 0

    lateinit var inflater: LayoutInflater
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.inflater = inflater

        var view: View = inflater.inflate(R.layout.fragment_mypage, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureTopNavigation()
        setOnClickListener()
    }

    override fun onResume() {
        super.onResume()
        getMypageProductResponse()
        getMypageLikeResponse()
        getMypageDealResponse()
        getMypageReviewResponse()
        getMypagePrefInfoResponse()
    }

    private fun configureTopNavigation() {
        vp_top_navi_act_frag_pager_mypage.adapter = MypageFragmentStatePageAdapter(childFragmentManager, 4,
            productDataList, likeDataList, dealDataList, reviewDataList)
        tl_top_navi_act_top_menu_mypage.setupWithViewPager(vp_top_navi_act_frag_pager_mypage)

        val topNaviLayout : View = inflater.inflate(R.layout.top_navigation_tab_mypage, null, false)

        tv_top_navi_mypage_product_tab = topNaviLayout.findViewById(R.id.tv_top_navi_mypage_product_tab)
        tv_top_navi_mypage_like_tab = topNaviLayout.findViewById(R.id.tv_top_navi_mypage_like_tab)
        tv_top_navi_mypage_deal_tab = topNaviLayout.findViewById(R.id.tv_top_navi_mypage_deal_tab)
        tv_top_navi_mypage_review_tab = topNaviLayout.findViewById(R.id.tv_top_navi_mypage_review_tab)

        txt_top_navi_mypage_product_cnt = topNaviLayout.findViewById(R.id.tv_mypage_user_product_cnt) as TextView
        txt_top_navi_mypage_like_cnt = topNaviLayout.findViewById(R.id.tv_mypage_user_like_cnt) as TextView
        txt_top_navi_mypage_deal_cnt = topNaviLayout.findViewById(R.id.tv_mypage_user_deal_cnt) as TextView
        txt_top_navi_mypage_review_cnt = topNaviLayout.findViewById(R.id.tv_mypage_user_review_cnt) as TextView

        txt_top_navi_mypage_product_cnt.text = productDataCount.toString()
        txt_top_navi_mypage_like_cnt.text = likeDataCount.toString()
        txt_top_navi_mypage_deal_cnt.text = dealDataCount.toString()
        txt_top_navi_mypage_review_cnt.text = reviewDataCount.toString()

        tl_top_navi_act_top_menu_mypage.getTabAt(3)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_mypage_review_tab) as RelativeLayout
        tl_top_navi_act_top_menu_mypage.getTabAt(2)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_mypage_deal_tab) as RelativeLayout
        tl_top_navi_act_top_menu_mypage.getTabAt(1)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_mypage_like_tab) as RelativeLayout
        tl_top_navi_act_top_menu_mypage.getTabAt(0)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_mypage_product_tab) as RelativeLayout
        tl_top_navi_act_top_menu_mypage.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
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
        btn_mypage_update_intro.setOnClickListener{
            try {
                tv_mypage_user_intro.visibility = View.GONE
                et_mypage_user_intro.visibility = View.VISIBLE
                et_mypage_user_intro.hint = mypageIntro
                btn_mypage_update_intro_finish.visibility = View.VISIBLE
                btn_mypage_update_intro.isSelected = true
            } catch (e: Exception) {
            }
        }
        btn_mypage_update_intro_finish.setOnClickListener {
            try {
                mypageIntro = et_mypage_user_intro.text.toString()
                btn_mypage_update_intro.isSelected = false
                tv_mypage_user_intro.setText(mypageIntro)
                tv_mypage_user_intro.visibility = View.VISIBLE
                et_mypage_user_intro.visibility = View.GONE
                btn_mypage_update_intro_finish.visibility = View.GONE

                jsonObject.put("u_description", mypageIntro)
                putMypagePrefInfoResponse()
            } catch (e: Exception) {
            }
        }
        btn_mypage_alert.setOnClickListener {
            try {
                val intent = Intent(context, AlarmActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
        btn_mypage_setting.setOnClickListener {
            try {
                val intent = Intent(activity?.applicationContext, MypagePreferencesActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
            }
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

    private fun getMypageProductResponse() {
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
                    productDataCount = response.body()!!.dataNum
                    Log.d("*****MypageFragment::getMypageProductResponse::Success::", productDataList.toString())
                    configureTopNavigation()
                } else {
                    Log.d("*****MypageFragment::getMypageProductResponse::Failed::", response.toString())
                }
            }
        })
    }

    private fun getMypageLikeResponse() {
        val token = SharedPreferenceController.getAuthorization(context!!)
        val u_idx = SharedPreferenceController.getUserID(context!!)
        val getMypageLikeResponse = networkService.getMypageLikeResponse(u_idx)
        getMypageLikeResponse.enqueue(object: Callback<GetMypageLikeResponse>{
            override fun onFailure(call: Call<GetMypageLikeResponse>, t: Throwable) {
                Log.d("*****MypageFragment::getMypageLikeResponse::", "List_LikeData_Failed")
            }

            override fun onResponse(call: Call<GetMypageLikeResponse>, response: Response<GetMypageLikeResponse>) {
                if (response.isSuccessful) {
                    likeDataCount = response.body()!!.dataNum
                    likeDataList = response.body()!!.data
                    Log.d("*****MypageFragment::getMypageLikeResponse::Success::", likeDataList.toString())
                    configureTopNavigation()
                } else {
                    Log.d("*****MypageFragment::getMypageLikeResponse::Failed::", response.toString())
                }
            }
        })
    }

    private fun getMypageDealResponse() {
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
                    dealDataCount = response.body()!!.dataNum
                    Log.d("*****MypageFragment::getMypageDealResponse::Success::", dealDataList.toString())
                    configureTopNavigation()
                } else {
                    Log.d("*****MypageFragment::getMypageDealResponse::Failure::", response.toString())
                }
            }
        })
    }

    private fun getMypageReviewResponse() {
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
                    reviewDataCount = response.body()!!.dataNum

                    Log.d("*****MypageFragment::getMypageReviewResponse::Success::", reviewDataList.toString())
                    configureTopNavigation()
                } else {
                    Log.d("*****MypageFragment::getMypageReviewResponse::Failed::", response.toString())
                }
            }
        })
    }

    private fun getMypagePrefInfoResponse() {
        val token = SharedPreferenceController.getAuthorization(context!!)
        val u_idx = SharedPreferenceController.getUserID(context!!)
        val getMypagePrefInfoResponse = networkService.getMypagePrefInfoResponse("application/json", token, u_idx)
        getMypagePrefInfoResponse.enqueue(object: Callback<GetMypagePrefInfoResponse> {
            override fun onFailure(call: Call<GetMypagePrefInfoResponse>, t: Throwable) {
                Log.d("*****MypageFragment::getMypagePrefInfoResponse::Failed::", "Get_UserInfo_Failed")
            }
            override fun onResponse(call: Call<GetMypagePrefInfoResponse>, response: Response<GetMypagePrefInfoResponse>) {
                if (response.isSuccessful) {
                    Log.d("*****MypageFragment::getMypagePrefInfoResponse::Success::", response.body().toString())
                    var intro : String = response.body()!!.data.u_description
                    if(intro != null) {
                        mypageIntro = response.body()!!.data.u_description
                    }
                    tv_mypage_user_name.text = response.body()!!.data.u_name
                    tv_mypage_user_intro.text = mypageIntro
                } else {
                    Log.d("*****MypageFragment::getMypagePrefInfoResponse::Failed::", response.body().toString())
                }
            }
        })
    }

    private fun putMypagePrefInfoResponse() {
        val token = SharedPreferenceController.getAuthorization(context!!)
        val u_idx = SharedPreferenceController.getUserID(context!!)

        Log.d("*****MypageFragment::putMypagePrefInfoResponse::json::", jsonObject.toString())

        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
        val putMypagePrefInfoResponse = networkService.putMypagePrefInfoResponse("application/json", token, u_idx, gsonObject)
        putMypagePrefInfoResponse.enqueue(object: Callback<PutMypagePrefInfoResponse> {
            override fun onFailure(call: Call<PutMypagePrefInfoResponse>, t: Throwable) {
                Log.d("*****MypageFragment::putMypagePrefInfoResponse::Failed::", "Update_UserInfo_Failed")
            }
            override fun onResponse(call: Call<PutMypagePrefInfoResponse>, response: Response<PutMypagePrefInfoResponse>) {
                if (response.isSuccessful) {
                    Log.d("*****MypageFragment::putMypagePrefInfoResponse::Success::", response.body().toString())
                    tv_mypage_user_intro.text = mypageIntro
                } else {
                    Log.d("*****MypageFragment::putMypagePrefInfoResponse::Failed::", response.body().toString())
                }
            }
        })
    }
}