package com.artoo.sopt23.artoo_client_android.Data.Response.Get

import com.artoo.sopt23.artoo_client_android.Data.MypageMyInfoData

data class GetMypageMyInfoResponse(
    var status: Int,
    var message: String,
    var data: MypageMyInfoData
)