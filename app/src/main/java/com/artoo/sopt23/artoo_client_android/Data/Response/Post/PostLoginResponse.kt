package com.artoo.sopt23.artoo_client_android.Data.Response.Post

data class PostLoginResponse(
    val status: Int,
    val message: String,
    val data: LoginData
)

data class LoginData(
    val token: String,
    val userIdx: Int
)