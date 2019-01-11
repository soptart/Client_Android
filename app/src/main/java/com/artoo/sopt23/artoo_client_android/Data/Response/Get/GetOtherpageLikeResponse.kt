package com.artoo.sopt23.artoo_client_android.Data.Response.Get

import com.artoo.sopt23.artoo_client_android.Data.OtherpageLikeData

data class GetOtherpageLikeResponse(
        var status: Int,
        var message: String,
        var u_name: String,
        var u_description: String,
        var data: ArrayList<OtherpageLikeData>,
        var dataNum: Int
)