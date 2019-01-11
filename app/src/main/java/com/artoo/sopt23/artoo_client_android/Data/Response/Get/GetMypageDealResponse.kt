package com.artoo.sopt23.artoo_client_android.Data.Response.Get

import com.artoo.sopt23.artoo_client_android.Data.MypageDealData

data class GetMypageDealResponse (
    var status: Int,
    var message: String,
    var u_name: String,
    var u_description: String,
    var data: ArrayList<MypageDealData>,
    var dataNum: Int
)