package com.artoo.sopt23.artoo_client_android.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import com.artoo.sopt23.artoo_client_android.Adapter.ApplyExMyProductAdapter
import com.artoo.sopt23.artoo_client_android.Adapter.ApplyExhibitionAdapter
import com.artoo.sopt23.artoo_client_android.Data.ApplyExMyProductData
import com.artoo.sopt23.artoo_client_android.Data.ApplyExhibitionData
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.activity_apply_exhibition.*

class ApplyExhibitionActivity : AppCompatActivity() {

    var applyExMyProductAdapter : ApplyExMyProductAdapter?=null
    var applyExhibitionAdapter : ApplyExhibitionAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_exhibition)

        var snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rv_apply_ex_my_product)

        var myDataList: ArrayList<ApplyExMyProductData> = ArrayList()
        var exhibitionDataList : ArrayList <ApplyExhibitionData> = ArrayList()

        var img_url: ArrayList<String> = ArrayList()
        img_url.add("http://webresizer.com/images2/bird1_after.jpg")
        img_url.add("http://img.newspim.com/news/2018/03/31/1803311718414850.jpg")
        img_url.add("http://www.newsa.co.kr/news/photo/201809/187929_146645_3554.jpg")
        img_url.add("https://www.nemopan.com/files/attach/images/2582/269/848/493724be0c020.jpg")

        myDataList.add(ApplyExMyProductData(img_url))
        myDataList.add(ApplyExMyProductData(img_url))
        myDataList.add(ApplyExMyProductData(img_url))
        myDataList.add(ApplyExMyProductData(img_url))
        myDataList.add(ApplyExMyProductData(img_url))
        myDataList.add(ApplyExMyProductData(img_url))

        exhibitionDataList.add(ApplyExhibitionData("[네가 그리는 자유展]", "자유편", "이제 모든걸 벗어 번지고 자유를 표현하고 싶다"))
        exhibitionDataList.add(ApplyExhibitionData("[맨발로 기억을 거닐다展]", "시간과 기억편", "시간은 흐르고, 추억을 돌이키며.."))
        exhibitionDataList.add(ApplyExhibitionData("[익숙함이 새로웠던展]", "일상편", "익숙함 속에 새로움을 찾아 두려움을 넘는다"))


        applyExMyProductAdapter = ApplyExMyProductAdapter(myDataList)
        rv_apply_ex_my_product.adapter = applyExMyProductAdapter
        rv_apply_ex_my_product.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        applyExhibitionAdapter = ApplyExhibitionAdapter(exhibitionDataList)
        rv_apply_ex_radio.adapter = applyExhibitionAdapter
        rv_apply_ex_radio.layoutManager = LinearLayoutManager(this)


    }
}
