package com.artoo.sopt23.artoo_client_android.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.artoo.sopt23.artoo_client_android.Adapter.ThemeRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Data.ProductOverviewData
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.activity_theme.*

class ThemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme)
        setRecyclerView()

    }

    lateinit var themeRecyclerViewAdapter: ThemeRecyclerViewAdapter

    fun setRecyclerView(){
        var dataList: ArrayList<ProductOverviewData> = ArrayList()
        dataList.add(ProductOverviewData(1, "http://img.newspim.com/news/2018/03/31/1803311718414850.jpg"))
        dataList.add(ProductOverviewData(2, "http://www.kalimanrawlins.com/images/s-media-cache-ak0.pinimg.com/736x/3b/a0/df/3ba0df2a39421e263a53b7ec74ee4de5--new-york-city-buildings-building-architecture.jpg"))
        dataList.add(ProductOverviewData(3, "http://www.newsa.co.kr/news/photo/201809/187929_146645_3554.jpg"))
        dataList.add(ProductOverviewData(4, "https://www.nemopan.com/files/attach/images/2582/269/848/493724be0c020.jpg"))
        dataList.add(ProductOverviewData(5, "http://webresizer.com/images2/bird1_after.jpg"))


        dataList.add(ProductOverviewData(1, "https://i.imgur.com/nqTGipe.jpg"))
        dataList.add(ProductOverviewData(2, "https://i.pinimg.com/236x/f6/16/d0/f616d044c7937dde2e8ec2b4f3e6f79c--france-travel-winding-road.jpg"))
        dataList.add(ProductOverviewData(3, "https://render.fineartamerica.com/images/rendered/default/framed-print/images-medium-5/the-distance-abstract-art-by-laura-gomez-horizontal-long-strip-format-laura-and-karina-gomez.jpg?imgWI=20.000&imgHI=6.875&sku=CRQ13&mat1=PM918&mat2=&t=2&b=2&l=2&r=2&off=0.5&frameW=0.875"))
        dataList.add(ProductOverviewData(4, "https://cdn.shopify.com/s/files/1/1202/6102/products/carhartt-horizontal-long-sleeve-t-shirt-dark-grey-heather-2.jpg?v=1537893366"))
        dataList.add(ProductOverviewData(5, "https://images.yaoota.com/Aho8kBbyPDfSY9vBM5q3-Z1352A=/trim/yaootaweb-production-ke/media/crawledproductimages/4b8e2ffd30a069e023b833644e4f8442d773e1de.jpg"))

        themeRecyclerViewAdapter = ThemeRecyclerViewAdapter(dataList)
        rv_theme_list.adapter = themeRecyclerViewAdapter
        rv_theme_list.layoutManager = GridLayoutManager(this, 3)
    }
}
