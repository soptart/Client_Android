package com.artoo.sopt23.artoo_client_android.Data.Response.Get

import com.artoo.sopt23.artoo_client_android.Data.MypageLikeData

data class GetMypageLikeResponse(
    val status: String,
    val message: String,
    val u_name: String,
    val u_description: String,
    val data: ArrayList<MypageLikeData>,
    val dataNum: Int
)