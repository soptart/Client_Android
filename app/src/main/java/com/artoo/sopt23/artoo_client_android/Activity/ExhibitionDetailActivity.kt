package com.artoo.sopt23.artoo_client_android.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import com.artoo.sopt23.artoo_client_android.Adapter.ExhibitionDetailAdapter
import com.artoo.sopt23.artoo_client_android.Data.ExhibitionDetailData
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.activity_exhibition_detail.*

class ExhibitionDetailActivity : AppCompatActivity() {
    lateinit var exhibitionDetailAdapter: ExhibitionDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exhibition_detail)


        var snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rv_ex_detail)

        var dataList: ArrayList<ExhibitionDetailData> = ArrayList()

        var img_url: ArrayList<String> = ArrayList()
        img_url.add("http://webresizer.com/images2/bird1_after.jpg")
        img_url.add("http://img.newspim.com/news/2018/03/31/1803311718414850.jpg")
        img_url.add("http://www.newsa.co.kr/news/photo/201809/187929_146645_3554.jpg")
        img_url.add("https://www.nemopan.com/files/attach/images/2582/269/848/493724be0c020.jpg")

        dataList.add(ExhibitionDetailData(img_url,"무제다","강세린","페인팅",34,12,"2018"))
        dataList.add(ExhibitionDetailData(img_url,"제목이다","한선민","초상화",224,123,"2018"))

        exhibitionDetailAdapter = ExhibitionDetailAdapter(dataList)
        rv_ex_detail.adapter = exhibitionDetailAdapter
        rv_ex_detail.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)


    }


}
