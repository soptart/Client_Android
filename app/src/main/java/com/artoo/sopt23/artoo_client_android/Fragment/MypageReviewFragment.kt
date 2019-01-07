package com.artoo.sopt23.artoo_client_android.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artoo.sopt23.artoo_client_android.Adapter.MypageReviewRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Data.MypageReviewData
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_mypage_review.*

class MypageReviewFragment : Fragment() {

    var reviewData = ArrayList<MypageReviewData>()
    lateinit var mypageReviewRecyclerViewAdapter: MypageReviewRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mypage_review, container, false) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        /*var dataList: ArrayList<MypageReviewData> = ArrayList()

        dataList.add(MypageReviewData("dkdkdk", "dldldlld", "", "dkdkdkdkkdkd", "2019.11.11"))
        dataList.add(MypageReviewData("dkdkdk", "dldldlld", "", "dkdkdkdkkdkd", "2019.11.11"))
        dataList.add(MypageReviewData("dkdkdk", "dldldlld", "", "dkdkdkdkkdkd", "2019.11.11"))*/

        mypageReviewRecyclerViewAdapter = MypageReviewRecyclerViewAdapter(reviewData)
        rv_fragment_mypage_review_list.adapter = mypageReviewRecyclerViewAdapter
        rv_fragment_mypage_review_list.layoutManager = LinearLayoutManager(activity)

    }
}
