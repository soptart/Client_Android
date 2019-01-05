package com.artoo.sopt23.artoo_client_android.Data.Response.Get

data class GetThemeProductResponse (
        val status: Int,
        val message: String,
        val data: ArrayList<ProductData>
)

data class ProductData(
        val a_idx: Int,
        val pic_url: String
)