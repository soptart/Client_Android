package com.artoo.sopt23.artoo_client_android.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.artoo.sopt23.artoo_client_android.Data.MypageDealData
import com.artoo.sopt23.artoo_client_android.Data.MypageReviewData
import com.artoo.sopt23.artoo_client_android.Fragment.*

class MypageFragmentStatePageAdapter(fm : FragmentManager, val fragmentCount : Int,
                                     val dealDataList: ArrayList<MypageDealData>,
                                     val reviewDataList: ArrayList<MypageReviewData>): FragmentStatePagerAdapter(fm){

    var mypageProductFragment: MypageProductFragment = MypageProductFragment().apply{
        //productData = productDataList
    }
    var mypageLikeFragment: MypageLikeFragment = MypageLikeFragment().apply{
        //likeDataList = tlikeDataList
    }
    var mypageDealFragment: MypageDealFragment = MypageDealFragment().apply{
        dealData = dealDataList
    }
    var mypageReviewFragment: MypageReviewFragment = MypageReviewFragment().apply{
        reviewData = reviewDataList
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {

            0 -> return MypageProductFragment()
            1 -> return MypageLikeFragment()
            2 -> return mypageDealFragment
            3 -> return mypageReviewFragment
            else -> return null
        }
    }
    override fun getCount(): Int = fragmentCount
}