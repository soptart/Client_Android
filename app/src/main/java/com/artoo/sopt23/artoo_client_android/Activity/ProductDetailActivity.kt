package com.artoo.sopt23.artoo_client_android.Activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.artoo.sopt23.artoo_client_android.Adapter.ProductDetailCommentRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.Data.CommentData
import com.artoo.sopt23.artoo_client_android.Data.ProductDetailData
import com.artoo.sopt23.artoo_client_android.Data.Response.Delete.DeleteProductResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetProductCommentResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetProductDetailResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Post.PostProductCommentResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Post.PostProductLikeResponse
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_product_detail.*
import org.jetbrains.anko.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailActivity : AppCompatActivity() {

    var a_idx:Int = -1

    var productCommentList: ArrayList<CommentData> = arrayListOf()
    lateinit var productDetailData: ProductDetailData
    lateinit var productCommentRecyclerViewAdapter: ProductDetailCommentRecyclerViewAdapter

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        a_idx = intent.getIntExtra("a_idx", -1)

        setRecyclerView()
        getProductDetailData()
        getProductCommentData()

        img_product_detail_product.setOnClickListener {
            startActivity<ProductZoomActivity>("pic_url" to productDetailData.pic_url, "a_name" to productDetailData.a_name)
        }

        ll_product_detail_bottomnav.setOnClickListener {
            startActivity<ProductPurchaseActivity>("pic_url" to productDetailData.pic_url, "a_idx" to productDetailData.a_idx, "u_idx" to SharedPreferenceController.getUserID(this))
        }

        img_product_detail_purchase.setOnClickListener {
            var a = 10
        }

        ll_product_detail_like.setOnClickListener({
            postProductLikeData()
        })

        tv_product_detail_add_comment.setOnClickListener {
            val token = SharedPreferenceController.getAuthorization(this)

            var jsonObject: JSONObject = JSONObject()
            jsonObject.put("c_content", edt_product_detail_comment.text.toString())
            jsonObject.put("u_idx", SharedPreferenceController.getUserID(this))
            jsonObject.put("a_idx", productDetailData.a_idx)

            edt_product_detail_comment.text.clear()
            edt_product_detail_comment.clearFocus()

            val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
            val postProductCommentResponse:Call<PostProductCommentResponse> = networkService.postProductCommentResponse(token, gsonObject)

            postProductCommentResponse.enqueue(object: Callback<PostProductCommentResponse>{
                override fun onFailure(call: Call<PostProductCommentResponse>, t: Throwable) {
                    Log.i("ProdutDetailActivity", "Comment")
                }

                override fun onResponse(
                    call: Call<PostProductCommentResponse>,
                    response: Response<PostProductCommentResponse>
                ) {
                    Toast.makeText(this@ProductDetailActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    if(response.isSuccessful) getProductCommentData()
                }
            })
        }

        img_product_detail_expand.setOnClickListener {
            if(it.isSelected == false){
                txt_product_detail_desc.maxLines = Int.MAX_VALUE
                it.isSelected = true
            }
            else{
                txt_product_detail_desc.maxLines = 3
                it.isSelected = false
            }
        }

        txt_product_detail_delete.setOnClickListener {
            val token = SharedPreferenceController.getAuthorization(this)
            val deleteProductResponse = networkService.deleteProductResponse("appliaction/json", token, a_idx)
            deleteProductResponse.enqueue(object: Callback<DeleteProductResponse>{
                override fun onFailure(call: Call<DeleteProductResponse>, t: Throwable) {
                    Log.i("ProductDetailActivity", "Connection Failure" + t.toString())
                }
                override fun onResponse(call: Call<DeleteProductResponse>, response: Response<DeleteProductResponse>) {
                    if(response.isSuccessful){
                        finish()
                    }
                }
            })
        }

        txt_product_detail_modify.setOnClickListener {
            startActivity<ProductUploadActivity>("a_idx" to a_idx)
        }
    }

    fun postProductLikeData(){
        val token = SharedPreferenceController.getAuthorization(this)
        val postProductLikeResponse = networkService.postProductLikeResponse("application/json", token, a_idx)
        postProductLikeResponse.enqueue(object: Callback<PostProductLikeResponse>{
            override fun onFailure(call: Call<PostProductLikeResponse>, t: Throwable) {
                Log.i("ProductDetailActivity", "Connection Failure" + t.toString())
            }
            override fun onResponse(call: Call<PostProductLikeResponse>, response: Response<PostProductLikeResponse>) {
                if(response.isSuccessful){
                    if(!img_product_detail_like.isSelected){
                        img_product_detail_like.isSelected = true
                        txt_product_detail_like.text = (txt_product_detail_like.text.toString().toInt()+1).toString()
                        txt_product_detail_like.setTextColor(Color.parseColor("#ff6f61"))
                    }
                    else{
                        img_product_detail_like.isSelected = false
                        txt_product_detail_like.text = (txt_product_detail_like.text.toString().toInt()-1).toString()
                        txt_product_detail_like.setTextColor(Color.parseColor("#9c9c9c"))
                    }
                }
            }
        })
    }

    fun getProductCommentData(){
        val token = SharedPreferenceController.getAuthorization(this)
        val getProductCommentResponse = networkService.getProductCommentResponse(token, a_idx)
        getProductCommentResponse.enqueue(object: Callback<GetProductCommentResponse>{
            override fun onFailure(call: Call<GetProductCommentResponse>, t: Throwable) {
                Log.i("ProductDetailActivity", "Connection Failure" + t.toString())
            }

            override fun onResponse(
                call: Call<GetProductCommentResponse>,
                response: Response<GetProductCommentResponse>
            ) {
                if(response.isSuccessful){
                    productCommentList = response.body()!!.data
                    for(i in 0..productCommentList.size-1){
                        if(productCommentList[i].u_idx == SharedPreferenceController.getUserID(this@ProductDetailActivity)){
                            ll_product_detail_skill.visibility = View.VISIBLE
                            ll_product_detail_info.visibility = View.GONE
                        }
                    }
                    productCommentRecyclerViewAdapter.dataList = productCommentList
                    productCommentRecyclerViewAdapter.notifyDataSetChanged()
                }
                else{
                    Log.i("ProductDetailActivity", "Get Comment Data Failure")
                }
            }
        })
    }

    fun getProductDetailData(){
        val token = SharedPreferenceController.getAuthorization(this)
        val getProductDetailResponse = networkService.getProductDetailResponse("application/json", token, a_idx)
        getProductDetailResponse.enqueue(object: Callback<GetProductDetailResponse> {
            override fun onFailure(call: Call<GetProductDetailResponse>, t: Throwable) {
                Log.i("ProductDetailActivity", "Connection Failure")
            }
            override fun onResponse(
                call: Call<GetProductDetailResponse>,
                response: Response<GetProductDetailResponse>
            ) {
                if(response.isSuccessful){
                    var options: RequestOptions = RequestOptions().centerInside()
                    productDetailData = response.body()!!.data
                    Glide.with(this@ProductDetailActivity)
                        .load(productDetailData.pic_url).apply(options).into(img_product_detail_product)
                    txt_product_detail_title.text = productDetailData.a_name
                    txt_product_detail_artist.text = productDetailData.u_school + " " + productDetailData.u_name
                    txt_product_detail_size.text = productDetailData.a_width.toString() + " x " +
                            productDetailData.a_height.toString() + " x " +
                            productDetailData.a_depth.toString()

                    if(productDetailData.a_size < 2412) img_product_detail_size.setImageResource(R.drawable.size_s)
                    else if(productDetailData.a_size < 6609) img_product_detail_size.setImageResource(R.drawable.size_m)
                    else if(productDetailData.a_size < 10629) img_product_detail_size.setImageResource(R.drawable.size_l)
                    else img_product_detail_size.setImageResource(R.drawable.size_xl)

                    if(productDetailData.a_license == "CCL표시안함")
                        img_product_detail_license.setImageResource(R.drawable.invalid_name)
                    else if(productDetailData.a_license == "저작자표시")
                        img_product_detail_license.setImageResource(R.drawable.cc_by)
                    else if(productDetailData.a_license == "저작자표시-비영리")
                        img_product_detail_license.setImageResource(R.drawable.cc_by_nc)
                    else if(productDetailData.a_license == "저작자표시-동일조건변경허락")
                        img_product_detail_license.setImageResource(R.drawable.cc_by_sa_copy)
                    else if(productDetailData.a_license == "저작자표시-변경금지")
                        img_product_detail_license.setImageResource(R.drawable.cc_by_nc_nd)
                    else if(productDetailData.a_license == "저작자표시-비영리-동일조건변경허락")
                        img_product_detail_license.setImageResource(R.drawable.cc_by_nc_sa)
                    else if(productDetailData.a_license == "저작자표시-비영리-변경금지")
                        img_product_detail_license.setImageResource(R.drawable.cc_by_nc_nd)
                    else{
                        Log.i("ProductDetailActivity", "Unknown License")
                    }

                    txt_product_detail_desc.text = productDetailData.a_detail
                    txt_product_detail_skill.text = productDetailData.a_expression
                    txt_product_detail_manufacturer.text = productDetailData.a_material
                    txt_product_detail_like.text = productDetailData.a_like_count.toString()
                    img_product_detail_like.isSelected = productDetailData.islike
                    txt_product_detail_price.text = productDetailData.a_price.toString()
                    txt_product_detail_year.text = productDetailData.a_year.toString()
                    if(productDetailData.auth){
                        txt_product_detail_modify.visibility = View.VISIBLE
                        txt_product_detail_delete.visibility = View.VISIBLE
                    }
                    else {
                        txt_product_detail_modify.visibility = View.GONE
                        txt_product_detail_delete.visibility = View.GONE
                    }

                    if(productDetailData.a_purchaseState == 0){
                        img_product_detail_purchase_ic.setImageResource(R.drawable.artwork_no_price)
                        txt_product_detail_price.text = "판매 안 함"
                        txt_product_detail_price.setTextColor(Color.parseColor("#434343"))
                        img_product_detail_purchase.setImageResource(R.drawable.artwork_sale_no_purchase)
                        ll_product_detail_bottomnav.isClickable = false
                    }
                    else if(productDetailData.a_purchaseState == 1){
                        img_product_detail_purchase_ic.setImageResource(R.drawable.artwork_price)
                        txt_product_detail_price.text = productDetailData.a_price.toString() +"원"
                        txt_product_detail_price.setTextColor(Color.parseColor("#ff6f61"))
                        img_product_detail_purchase.setImageResource(R.drawable.artwork_sale_purchase)
                        ll_product_detail_bottomnav.isClickable = true
                    }
                    else if(productDetailData.a_purchaseState == 2){
                        img_product_detail_purchase_ic.setImageResource(R.drawable.artwork_no_price)
                        txt_product_detail_price.text = "판매 안 함"
                        txt_product_detail_price.setTextColor(Color.parseColor("#434343"))
                        img_product_detail_purchase.setImageResource(R.drawable.artwork_sale_complete)
                        ll_product_detail_bottomnav.isClickable = false
                    }
                    else{
                        Log.i("ProductDetailActivity", "Unknown PurchaseState")
                    }
                }
                else{
                    Log.i("ProductDetailActivity", "Get Data Failure")
                }
            }
        })
    }

    private fun setRecyclerView() {
        productCommentRecyclerViewAdapter = ProductDetailCommentRecyclerViewAdapter(this, productCommentList)
        rv_product_detail_comment_list.adapter = productCommentRecyclerViewAdapter
        rv_product_detail_comment_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}