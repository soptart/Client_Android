package com.artoo.sopt23.artoo_client_android.Data.Response.Get

import com.artoo.sopt23.artoo_client_android.Data.ProductOverviewData

data class GetProductListResponse (
        var status: Int,
        var message: String,
        var len: Int,
        var data : ArrayList<ProductOverviewData>
)