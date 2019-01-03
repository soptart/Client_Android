package com.artoo.sopt23.artoo_client_android.Adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.artoo.sopt23.artoo_client_android.Data.TodayArtistData
import com.artoo.sopt23.artoo_client_android.Fragment.*


//Home Tab Adapter
class HomeArtistFragmentStatePagerAdapter(fm : FragmentManager, val fragmentCount : Int, val todayArtistList: ArrayList<TodayArtistData>): FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return HomeArtist1Fragment().apply{
                todayArtistData = todayArtistList.get(0)
            }
            1 -> return HomeArtist2Fragment().apply{
                todayArtistData = todayArtistList.get(1)
            }
            2 -> return HomeArtist3Fragment().apply{
                todayArtistData = todayArtistList.get(2)
            }
            3 -> return HomeArtist4Fragment().apply{
                todayArtistData = todayArtistList.get(3)
            }
            4 -> return HomeArtist5Fragment().apply{
                todayArtistData = todayArtistList.get(4)
            }
            else -> return null
        }
    }
    override fun getCount(): Int = fragmentCount
}