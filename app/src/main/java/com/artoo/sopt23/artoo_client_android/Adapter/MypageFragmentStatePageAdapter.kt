package com.artoo.sopt23.artoo_client_android.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.artoo.sopt23.artoo_client_android.Fragment.MypageDealFragment
import com.artoo.sopt23.artoo_client_android.Fragment.MypageLikeFragment
import com.artoo.sopt23.artoo_client_android.Fragment.MypageProductFragment
import com.artoo.sopt23.artoo_client_android.Fragment.MypageReviewFragment

class MypageFragmentStatePageAdapter(fm : FragmentManager, val fragmentCount : Int): FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return MypageProductFragment()
            1 -> return MypageLikeFragment()
            2 -> return MypageDealFragment()
            3 -> return MypageReviewFragment()
            else -> return null
        }
    }
    override fun getCount(): Int = fragmentCount
}