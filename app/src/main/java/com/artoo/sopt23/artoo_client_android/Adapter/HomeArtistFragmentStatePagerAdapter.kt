package com.artoo.sopt23.artoo_client_android.Adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.artoo.sopt23.artoo_client_android.Data.TodayArtistData
import com.artoo.sopt23.artoo_client_android.Fragment.*


//Home Tab Adapter
class HomeArtistFragmentStatePagerAdapter(fm : FragmentManager, val fragmentCount : Int, var todayArtistList: ArrayList<TodayArtistData>): FragmentStatePagerAdapter(fm){

    var homeArtist1Fragment: HomeArtist1Fragment = HomeArtist1Fragment().apply{
        todayArtistData = todayArtistList.get(0)
    }
    var homeArtist2Fragment: HomeArtist2Fragment = HomeArtist2Fragment().apply{
        todayArtistData = todayArtistList.get(1)
    }
    var homeArtist3Fragment: HomeArtist3Fragment = HomeArtist3Fragment().apply{
        todayArtistData = todayArtistList.get(2)
    }
    var homeArtist4Fragment: HomeArtist4Fragment = HomeArtist4Fragment().apply{
        todayArtistData = todayArtistList.get(3)
    }
    var homeArtist5Fragment: HomeArtist5Fragment = HomeArtist5Fragment().apply{
        todayArtistData = todayArtistList.get(4)
    }

    fun updateData(){
        homeArtist1Fragment.todayArtistData = todayArtistList.get(0)
        homeArtist2Fragment.todayArtistData = todayArtistList.get(1)
        homeArtist3Fragment.todayArtistData = todayArtistList.get(2)
        homeArtist4Fragment.todayArtistData = todayArtistList.get(3)
        homeArtist5Fragment.todayArtistData = todayArtistList.get(4)
        homeArtist1Fragment.updateData()
        homeArtist2Fragment.updateData()
        homeArtist3Fragment.updateData()
        homeArtist4Fragment.updateData()
        homeArtist5Fragment.updateData()
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return homeArtist1Fragment
            1 -> return homeArtist2Fragment
            2 -> return homeArtist3Fragment
            3 -> return homeArtist4Fragment
            4 -> return homeArtist5Fragment
            else -> return null
        }
    }

    override fun getCount(): Int = fragmentCount
}