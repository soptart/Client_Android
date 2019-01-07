package com.artoo.sopt23.artoo_client_android.Data

import java.io.Serializable

data class MypageDealData (
    /*
    var product_img: String,
    var product_title: String,
    var product_buyer: String,
    var product_price: String,
    var writetime: String,
    var status_img: String
    */
    var p_idx: Int,
    var a_idx: Int,
    var a_name: String,
    var u_name: String,
    var a_price: Int,
    var p_state: Int,
    var a_pic_url: String,
    var p_date: String,
    var buyer: Boolean
)