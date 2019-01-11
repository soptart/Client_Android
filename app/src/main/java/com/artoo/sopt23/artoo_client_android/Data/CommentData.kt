package com.artoo.sopt23.artoo_client_android.Data

data class CommentData(
    var u_idx: Int,
    var u_name: String,
    var c_idx: Int,
    var c_content: String,
    var c_date: String,
    var auth: Boolean
)