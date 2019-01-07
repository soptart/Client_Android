package com.artoo.sopt23.artoo_client_android.Data.Response.Get

import com.artoo.sopt23.artoo_client_android.Data.MypageLikeData

data class GetMypageLikeResponse(
    val status: String,
    val message: String,
    val data: ArrayList<MypageLikeData>,
    val dataNum: Int
)