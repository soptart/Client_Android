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
import org.jetbrains.anko.bundleOf
import java.util.ResourceBundle.getBundle
import kotlin.collections.ArrayList

class MypageDealFragment : Fragment() {

    var dealData = ArrayList<MypageDealData>()
    lateinit var mypageDealRecyclerViewAdapter: MypageDealRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mypage_deal, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {

        mypageDealRecyclerViewAdapter = MypageDealRecyclerViewAdapter(dealData)
        rv_fragment_mypage_deal_list.adapter = mypageDealRecyclerViewAdapter
        rv_fragment_mypage_deal_list.layoutManager = LinearLayoutManager(activity)
    }
}
