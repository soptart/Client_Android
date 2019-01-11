package com.artoo.sopt23.artoo_client_android.Data

import java.io.Serializable
import java.util.*

data class ProductDetailData(
    var a_idx: Int,
    var a_name: String,
    var a_width: Int,
    var a_height: Int,
    var a_depth: Int,
    var a_price: Int,
    var a_like_count: Int,
    var u_idx: Int,
    var u_name: String,
    var u_school: String,
    var a_detail: String,
    var a_material: String,
    var a_expression: String,
    var a_year: String,
    var pic_url: String,
    var auth: Boolean,
    var islike: Boolean,
    var a_license: String,
    var a_size: Int,
    var a_purchaseState:Int
):Serializable