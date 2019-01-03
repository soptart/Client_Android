package com.artoo.sopt23.artoo_client_android.Data.Response.Get

data class GetUserDescResponse(
    var status: Int,
    var message: String,
    val data: String
)