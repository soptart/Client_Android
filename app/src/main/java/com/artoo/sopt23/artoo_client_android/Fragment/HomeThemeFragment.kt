package com.artoo.sopt23.artoo_client_android.Fragment

import android.content.Context
import android.graphics.BlurMaskFilter
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artoo.sopt23.artoo_client_android.Adapter.HomeThemeRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Data.ProductOverviewData
import com.artoo.sopt23.artoo_client_android.Data.ThemeData

import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import kotlinx.android.synthetic.main.fragment_home_theme.*
import kotlinx.android.synthetic.main.rv_item_home_theme.*
import org.jetbrains.anko.coroutines.experimental.asReference
import kotlin.collections.ArrayList
import android.os.Build
import com.bumptech.glide.request.transition.Transition
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.view.animation.Transformation
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.request.Request
import jp.wasabeef.glide.transformations.BlurTransformation


class HomeThemeFragment : Fragment() {
    lateinit var homeThemeRecyclerViewAdapter: HomeThemeRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_theme, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
        var options: RequestOptions = RequestOptions().placeholder(android.R.drawable.checkbox_on_background).centerCrop()

        Glide.with(this)
            .load("http://img.newspim.com/news/2018/03/31/1803311718414850.jpg")
            .apply(options)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
            .into(object : SimpleTarget<Drawable>() {
                override fun onResourceReady(
                    drawable: Drawable,
                    @Nullable transition: Transition<in Drawable>?
                ) {
                    ll_home_theme_background.background = drawable
                }
            })
    }


    private fun setRecyclerView() {
        var dataList: ArrayList<ProductOverviewData> = ArrayList()

        dataList.add(ProductOverviewData(1, "http://img.newspim.com/news/2018/03/31/1803311718414850.jpg"))
        dataList.add(ProductOverviewData(2, "http://www.kalimanrawlins.com/images/s-media-cache-ak0.pinimg.com/736x/3b/a0/df/3ba0df2a39421e263a53b7ec74ee4de5--new-york-city-buildings-building-architecture.jpg"))
        dataList.add(ProductOverviewData(3, "http://www.newsa.co.kr/news/photo/201809/187929_146645_3554.jpg"))
        dataList.add(ProductOverviewData(4, "https://www.nemopan.com/files/attach/images/2582/269/848/493724be0c020.jpg"))
        dataList.add(ProductOverviewData(5, "http://webresizer.com/images2/bird1_after.jpg"))

        homeThemeRecyclerViewAdapter = HomeThemeRecyclerViewAdapter(dataList)
        rv_home_theme_recommend_product.adapter = homeThemeRecyclerViewAdapter
        rv_home_theme_recommend_product.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }
}
