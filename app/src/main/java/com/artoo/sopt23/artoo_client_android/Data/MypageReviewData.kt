package com.artoo.sopt23.artoo_client_android.Data

data class MypageReviewData(
    var product_title: String,
    //buyer가 넘어오면 '판거래", 그렇지 않으면 '산거래'
    var product_buyer: String,
    var product_img: String,
    var content: String,
    var writetime: String
)