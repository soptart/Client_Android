package com.artoo.sopt23.artoo_client_android.Data.Response.Post

data class PostPurchaseResponse(
        val status: Int,
        val message: String,
        val data: PurchaseData
)
data class PurchaseData(
        /*val artworkName: String,
        val artistSchool: String,
        val artistName: String,
        val artworkPrice: Int,
        val deliveryCharge: Int*/
        val p_recipient: String
)