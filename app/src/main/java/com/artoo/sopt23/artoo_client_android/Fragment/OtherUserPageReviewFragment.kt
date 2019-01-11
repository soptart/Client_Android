package com.artoo.sopt23.artoo_client_android.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artoo.sopt23.artoo_client_android.Adapter.OtherpageReviewRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Data.OtherpageReviewData
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_other_user_page_review.*

class OtherUserPageReviewFragment : Fragment() {

    var reviewData = ArrayList<OtherpageReviewData>()
    lateinit var otherpageReviewRecyclerViewAdapter: OtherpageReviewRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_other_user_page_review, container, false) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        /*var dataList: ArrayList<MypageReviewData> = ArrayList()

        dataList.add(MypageReviewData("dkdkdk", "dldldlld", "", "dkdkdkdkkdkd", "2019.11.11"))
        dataList.add(MypageReviewData("dkdkdk", "dldldlld", "", "dkdkdkdkkdkd", "2019.11.11"))
        dataList.add(MypageReviewData("dkdkdk", "dldldlld", "", "dkdkdkdkkdkd", "2019.11.11"))*/

        otherpageReviewRecyclerViewAdapter = OtherpageReviewRecyclerViewAdapter(reviewData)
        rv_fragment_other_user_page_review_list.adapter = otherpageReviewRecyclerViewAdapter
        rv_fragment_other_user_page_review_list.layoutManager = LinearLayoutManager(activity)

    }
}