package com.artoo.sopt23.artoo_client_android.Data.Response.Get

import com.artoo.sopt23.artoo_client_android.Data.TodayArtistData

data class GetTodayArtistResponse(
    var status: Int,
    var message: String,
    var data: ArrayList<TodayArtistData>
)