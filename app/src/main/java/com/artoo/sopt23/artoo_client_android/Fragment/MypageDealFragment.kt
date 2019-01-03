package com.artoo.sopt23.artoo_client_android.Fragment
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artoo.sopt23.artoo_client_android.Adapter.MypageDealRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Data.MypageDealData

import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_mypage_deal.*
import kotlin.collections.ArrayList

class MypageDealFragment : Fragment() {
    lateinit var mypageDealRecyclerViewAdapter: MypageDealRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mypage_deal, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
    }


    private fun setRecyclerView() {
        var dataList: ArrayList<MypageDealData> = ArrayList()

        dataList.add(MypageDealData("http://img.newspim.com/news/2018/03/31/1803311718414850.jpg", "7살과 그녀", "","", "",  "http://www.newsa.co.kr/news/photo/201809/187929_146645_3554.jpg"))
        dataList.add(MypageDealData("http://img.newspim.com/news/2018/03/31/1803311718414850.jpg", "7살과 그녀", "","", "",  "http://www.newsa.co.kr/news/photo/201809/187929_146645_3554.jpg"))
        dataList.add(MypageDealData("http://img.newspim.com/news/2018/03/31/1803311718414850.jpg", "7살과 그녀", "","", "",  "http://www.newsa.co.kr/news/photo/201809/187929_146645_3554.jpg"))

        mypageDealRecyclerViewAdapter = MypageDealRecyclerViewAdapter(dataList)
        rv_fragment_mypage_deal_list.adapter = mypageDealRecyclerViewAdapter
        rv_fragment_mypage_deal_list.layoutManager = LinearLayoutManager(activity)
    }
}
