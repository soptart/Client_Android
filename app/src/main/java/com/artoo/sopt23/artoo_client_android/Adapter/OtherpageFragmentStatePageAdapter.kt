package com.artoo.sopt23.artoo_client_android.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.artoo.sopt23.artoo_client_android.Data.OtherpageLikeData
import com.artoo.sopt23.artoo_client_android.Data.OtherpageProductData
import com.artoo.sopt23.artoo_client_android.Data.OtherpageReviewData
import com.artoo.sopt23.artoo_client_android.Fragment.OtherUserPageLikeFragment
import com.artoo.sopt23.artoo_client_android.Fragment.OtherUserPageProductFragment
import com.artoo.sopt23.artoo_client_android.Fragment.OtherUserPageReviewFragment

class OtherpageFragmentStatePageAdapter(fm : FragmentManager, val fragmentCount : Int,
                                        val productDataList: ArrayList<OtherpageProductData>,
                                        val likeDataList: ArrayList<OtherpageLikeData>,
                                        val reviewDataList: ArrayList<OtherpageReviewData>): FragmentStatePagerAdapter(fm){

    var otherpageProductFragment: OtherUserPageProductFragment = OtherUserPageProductFragment().apply{
        productData = productDataList
    }
    var otherpageLikeFragment: OtherUserPageLikeFragment = OtherUserPageLikeFragment().apply{
        likeData = likeDataList
    }
    var otherpageReviewFragment: OtherUserPageReviewFragment = OtherUserPageReviewFragment().apply{
        reviewData = reviewDataList
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return otherpageProductFragment
            1 -> return otherpageLikeFragment
            2 -> return otherpageReviewFragment
            else -> return null
        }
    }
    override fun getCount(): Int = fragmentCount
}