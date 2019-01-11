package com.artoo.sopt23.artoo_client_android.Data.Response.Get

import com.artoo.sopt23.artoo_client_android.Data.MypagePrefInfoData

data class GetMypagePrefInfoResponse (
    val status: Int,
    val message: String,
    val data: MypagePrefInfoData
)