package com.artoo.sopt23.artoo_client_android.Data

data class AlarmBuyData(
        var p_idx: Int,
        var p_isPay: Int,
        var p_isDelivery: Int,
        var p_date: String,
        var a_idx: Int,
        var a_name: String,
        var a_u_name: String,
        var a_price: Int,
        var a_pic_url: String,
        var u_idx: Int,
        var u_name: String,
        var u_phone: String,
        var u_address: String,
        var u_bank: String,
        var u_account: String,
        var c_isComment: Boolean
)