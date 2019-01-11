package com.artoo.sopt23.artoo_client_android.Data.Response.Get

data class GetPurchaseResponse(
    val status: String,
    val message: String,
    val data: ArtworkData
)

data class ArtworkData(
    val artworkName: String,
    val artistSchool: String,
    val artistName: String,
    val artworkPrice: Int,
    val deliveryCharge: Int
)