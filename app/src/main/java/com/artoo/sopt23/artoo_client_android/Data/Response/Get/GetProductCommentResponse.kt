package com.artoo.sopt23.artoo_client_android.Data.Response.Get

import com.artoo.sopt23.artoo_client_android.Data.CommentData

data class GetProductCommentResponse(
    val status: Int,
    val message: String,
    val data: ArrayList<CommentData>
)