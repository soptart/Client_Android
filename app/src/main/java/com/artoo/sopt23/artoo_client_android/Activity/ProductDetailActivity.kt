package com.artoo.sopt23.artoo_client_android.Activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.artoo.sopt23.artoo_client_android.Adapter.ProductDetailCommentRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Data.CommentData
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.activity_product_detail.*
import org.jetbrains.anko.startActivity

class ProductDetailActivity : AppCompatActivity() {

    var img_product_detail_expand_pos = 0
    var is_like = 0

    lateinit var productDetailCommentRecyclerViewAdapter: ProductDetailCommentRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        setRecyclerView()

        ll_product_detail_info.visibility = View.GONE

        img_product_detail_product.setOnClickListener {
            startActivity<ProductZoomActivity>("pic_url" to "http://expert.eduhong.com/mallimg/2017/03/14/1489457076-4567.jpg")
        }

        ll_product_detail_like.setOnClickListener({
            if(is_like == 0){
                is_like = 1;
                txt_product_detail_like.text = (txt_product_detail_like.text.toString().toInt() + 1).toString()
                txt_product_detail_like.setTextColor(Color.parseColor("#ff6f61"))
            }
            else{
                is_like = 1;
                txt_product_detail_like.text = (txt_product_detail_like.text.toString().toInt() - 1).toString()
                txt_product_detail_like.setTextColor(Color.parseColor("#9c9c9c"))
            }

        })

        img_product_detail_expand.setOnClickListener {
            if(img_product_detail_expand_pos == 0){
                img_product_detail_expand_pos = 1;
                txt_product_detail_desc.maxLines = Int.MAX_VALUE
                img_product_detail_expand.setImageResource(android.R.drawable.arrow_up_float)
            }
            else{
                img_product_detail_expand_pos = 0;
                txt_product_detail_desc.maxLines = 3
                img_product_detail_expand.setImageResource(android.R.drawable.arrow_down_float)
            }
        }

        ll_product_detail_bottomnav.setOnClickListener {
            startActivity<PurchaseActivity>("pid" to 1, "uid" to 1)
        }
    }

    private fun setRecyclerView() {
        var dataList: ArrayList<CommentData> = ArrayList()
        dataList.add(CommentData(1, "최윤정", "작품 좋아요", "2018.11.24"))
        dataList.add(CommentData(1, "최윤정", "작품 좋아요", "2018.11.25"))
        dataList.add(CommentData(2, "배선영", "내가 세상에서 제일좋아하는빵또아는 노란색 포장지에 쌓여있고 하얀색 속살을 가지고 있는 맛있는 아이스크립입니다.", "2018.11.24"))
        dataList.add(CommentData(1, "최윤정", "작품 좋아요", "2018.11.26"))

        productDetailCommentRecyclerViewAdapter = ProductDetailCommentRecyclerViewAdapter(this, dataList)
        rv_product_detail_comment_list.adapter = productDetailCommentRecyclerViewAdapter
        rv_product_detail_comment_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}
