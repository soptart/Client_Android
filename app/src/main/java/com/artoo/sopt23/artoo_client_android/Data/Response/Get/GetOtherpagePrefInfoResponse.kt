package com.artoo.sopt23.artoo_client_android.Data.Response.Get

import com.artoo.sopt23.artoo_client_android.Data.OtherpagePrefInfoData

data class GetOtherpagePrefInfoResponse (
        val status: Int,
        val message: String,
        val data: OtherpagePrefInfoData
)