package com.artoo.sopt23.artoo_client_android.Fragment
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import com.artoo.sopt23.artoo_client_android.Activity.FilterActivity
import com.artoo.sopt23.artoo_client_android.Adapter.ProductRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Data.ProductOverviewData
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetProductListResponse
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService

import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_product.*
import org.jetbrains.anko.support.v4.startActivityForResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductFragment : Fragment() {

    val PRODUCT_FRAGMENT: Int = 1

    lateinit var productRecyclerViewAdapter: ProductRecyclerViewAdapter
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    var filter_size: String? = null
    var filter_type: String? = null
    var filter_category: String? = null
    var keyword: String = ""

    var dataList: ArrayList<ProductOverviewData> = arrayListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ll_product_filter.setOnClickListener {
            startActivityForResult<FilterActivity>(PRODUCT_FRAGMENT,
                "filter_size" to filter_size,
                "filter_type" to filter_type,
                "filter_category" to filter_category)
        }

        sv_product_search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query == null) keyword = ""
                else keyword = query
                setFilter()
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return true
            }
        })

        sw_product_list.setOnRefreshListener(object: SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {
                if(txt_fragment_filter.text!="필터를 선택해주세요") updateDataList()
            }
        })

        setRecyclerView()
        txt_fragment_filter.text = "필터를 선택해주세요"
    }

    override fun onResume() {
        super.onResume()
        sv_product_search.isFocusable = false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PRODUCT_FRAGMENT){
            filter_size = null
            filter_type = null
            filter_category = null
            if(resultCode == Activity.RESULT_OK){
                filter_size = data!!.getStringExtra("filter_size")
                filter_type = data!!.getStringExtra("filter_type")
                filter_category = data!!.getStringExtra("filter_category")
                setFilter()
            }
        }
    }

    fun setFilter(){
        txt_fragment_filter.text = "전체"
        if(filter_size != null) txt_fragment_filter.text = filter_size
        if(filter_type!= null) txt_fragment_filter.text = txt_fragment_filter.text.toString() + " | " + filter_type
        if(filter_category!= null) txt_fragment_filter.text = txt_fragment_filter.text.toString() + " | " + filter_category

        updateDataList()
    }

    fun updateDataList(){
        val getProductListResponse = networkService.getProductListResponse(filter_size, filter_type, filter_category, keyword!!)
        getProductListResponse.enqueue(object: Callback<GetProductListResponse> {
            override fun onFailure(call: Call<GetProductListResponse>, t: Throwable) {
                Log.e("board list fail", t.toString())
            }

            override fun onResponse(call: Call<GetProductListResponse>, response: Response<GetProductListResponse>) {
                if(response.isSuccessful){
                    dataList = response.body()!!.data
                    txt_product_num.text = dataList.size.toString() + "개의 작품들을 찾았어요!"

                    productRecyclerViewAdapter.dataList = dataList
                    productRecyclerViewAdapter.notifyDataSetChanged()
                }
            }
        })
    }

    fun setRecyclerView(){
        productRecyclerViewAdapter = ProductRecyclerViewAdapter(dataList)
        txt_product_num.text = dataList.size.toString() + "개의 작품들을 찾았어요!"
        val staggeredGridLayoutManager:StaggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        staggeredGridLayoutManager.gapStrategy=StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS

        rv_fragment_product_list.adapter = productRecyclerViewAdapter
        rv_fragment_product_list.layoutManager = staggeredGridLayoutManager
        staggeredGridLayoutManager.invalidateSpanAssignments()

        rv_fragment_product_list.invalidate()
        ll_product_list.invalidate()
    }
}