package com.artoo.sopt23.artoo_client_android.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.artoo.sopt23.artoo_client_android.Data.AlarmBuyData
import com.artoo.sopt23.artoo_client_android.Data.AlarmExhibitionData
import com.artoo.sopt23.artoo_client_android.Data.AlarmSellData
import com.artoo.sopt23.artoo_client_android.Fragment.AlarmBuyFragment
import com.artoo.sopt23.artoo_client_android.Fragment.AlarmExhibitionFragment
import com.artoo.sopt23.artoo_client_android.Fragment.AlarmSellFragment

//Alarm Tab Adpater
class AlarmFragmentStatePagerAdapter(fm : FragmentManager, val fragmentCount : Int): FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return AlarmBuyFragment()
            1 -> return AlarmSellFragment()
            2 -> return AlarmExhibitionFragment()
            else -> return null
        }
    }
    override fun getCount(): Int = fragmentCount
}