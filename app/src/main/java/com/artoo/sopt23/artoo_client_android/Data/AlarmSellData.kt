package com.artoo.sopt23.artoo_client_android.Data

data class AlarmSellData(
        var type: Boolean,
        var date: String,
        var img_url:String,
        var title: String,
        var artist: String,
        var buyer_name: String,
        var buyer_number: String,
        var buyer_address: String
)