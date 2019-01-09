package com.artoo.sopt23.artoo_client_android.Data.Response.Get

import com.artoo.sopt23.artoo_client_android.Data.MypageLikeData

data class GetMypageLikeResponse(
    var status: Int,
    var message: String,
    var u_name: String,
    var u_description: String,
    var data: ArrayList<MypageLikeData>,
    var dataNum: Int
)