package com.artoo.sopt23.artoo_client_android.Data.Response.Get

import com.artoo.sopt23.artoo_client_android.Data.MypageDealData

data class GetMypageDealResponse (
    val status: Int,
    val message: String,
    val u_name: String,
    val u_description: String,
    val data: ArrayList<MypageDealData>,
    val dataNum: Int
)