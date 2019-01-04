package com.artoo.sopt23.artoo_client_android.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log
import com.artoo.sopt23.artoo_client_android.Data.TodayArtistData
import com.artoo.sopt23.artoo_client_android.Fragment.ExhibitionFragment
import com.artoo.sopt23.artoo_client_android.Fragment.HomeFragment
import com.artoo.sopt23.artoo_client_android.Fragment.MypageFragment
import com.artoo.sopt23.artoo_client_android.Fragment.ProductFragment

//Main Tab Adpater
class MainFragmentStatePagerAdapter(fm : FragmentManager, val fragmentCount : Int): FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return HomeFragment()
            1 -> return ProductFragment()
            2 -> return ExhibitionFragment()
            3 -> return MypageFragment()
            else -> return null
        }
    }
    override fun getCount(): Int = fragmentCount
}