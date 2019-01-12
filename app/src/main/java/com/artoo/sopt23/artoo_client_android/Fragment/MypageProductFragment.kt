package com.artoo.sopt23.artoo_client_android.Fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artoo.sopt23.artoo_client_android.Activity.ProductUploadActivity
import com.artoo.sopt23.artoo_client_android.Adapter.MypageProductRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Data.MypageProductData
import com.artoo.sopt23.artoo_client_android.R
import com.artoo.sopt23.artoo_client_android.R.id.ll_mypage_product_count_many
import com.artoo.sopt23.artoo_client_android.R.id.rl_mypage_product_count_zero
import kotlinx.android.synthetic.main.fragment_mypage_product.*
import kotlinx.android.synthetic.main.fragment_mypage_product.view.*

class MypageProductFragment : Fragment() {

    var productData = ArrayList<MypageProductData>()
    lateinit var mypageProductRecyclerViewAdapter: MypageProductRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_mypage_product, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //작품개수 0개 이상일 때,
        if (productData.size > 0) {
            rl_mypage_product_count_zero.visibility = View.GONE
            ll_mypage_product_count_many.visibility = View.VISIBLE
        }
        setRecyclerView()
        setOnClickListener()
    }

    private fun setRecyclerView() {
        mypageProductRecyclerViewAdapter = MypageProductRecyclerViewAdapter(productData)
        val staggeredGridLayoutManager: StaggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        staggeredGridLayoutManager.gapStrategy= StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS

        rv_fragment_mypage_product_list.adapter = mypageProductRecyclerViewAdapter
        rv_fragment_mypage_product_list.layoutManager = staggeredGridLayoutManager
        staggeredGridLayoutManager.invalidateSpanAssignments()

        rv_fragment_mypage_product_list.invalidate()
    }

    private fun setOnClickListener() {

        btn_mypage_prodcut_nothing_add.setOnClickListener {
            try {
                val intent = Intent(context, ProductUploadActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
        btn_mypage_product_add.setOnClickListener {
            try {
                val intent = Intent(context, ProductUploadActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
    }
}