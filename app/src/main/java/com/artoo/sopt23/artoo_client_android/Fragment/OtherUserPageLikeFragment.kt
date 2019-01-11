package com.artoo.sopt23.artoo_client_android.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artoo.sopt23.artoo_client_android.Adapter.OtherpageLikeRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Data.OtherpageLikeData
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_other_user_page_like.*

class OtherUserPageLikeFragment : Fragment() {

    var likeData = ArrayList<OtherpageLikeData>()
    lateinit var mypageLikeRecyclerViewAdapter: OtherpageLikeRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_other_user_page_like, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        mypageLikeRecyclerViewAdapter = OtherpageLikeRecyclerViewAdapter(likeData)
        val staggeredGridLayoutManager: StaggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        staggeredGridLayoutManager.gapStrategy= StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS

        rv_fragment_other_user_page_like_list.adapter = mypageLikeRecyclerViewAdapter
        rv_fragment_other_user_page_like_list.layoutManager = staggeredGridLayoutManager
        rv_fragment_other_user_page_like_list.itemAnimator = null
    }
}