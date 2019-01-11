package com.artoo.sopt23.artoo_client_android.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artoo.sopt23.artoo_client_android.Adapter.OtherpageProductRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Data.OtherpageProductData
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_other_user_page_product.*

class OtherUserPageProductFragment : Fragment() {

    var productData = ArrayList<OtherpageProductData>()
    lateinit var otherpageProductRecyclerViewAdapter: OtherpageProductRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_other_user_page_product, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //작품개수 0개 이상일 때,
        if (productData.size > 0) {
            rl_otherpage_product_count_zero.visibility = View.GONE
            ll_otherpage_product_count_many.visibility = View.VISIBLE
        }
        setRecyclerView()
        setOnClickListener()
    }

    private fun setRecyclerView() {
        otherpageProductRecyclerViewAdapter = OtherpageProductRecyclerViewAdapter(productData)
        val staggeredGridLayoutManager: StaggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        staggeredGridLayoutManager.gapStrategy= StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS

        rv_fragment_otherpage_product_list.adapter = otherpageProductRecyclerViewAdapter
        rv_fragment_otherpage_product_list.layoutManager = staggeredGridLayoutManager
        staggeredGridLayoutManager.invalidateSpanAssignments()

        rv_fragment_otherpage_product_list.invalidate()
    }

    private fun setOnClickListener() {

    }
}