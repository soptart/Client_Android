package com.artoo.sopt23.artoo_client_android.Data

data class TodayArtistData(
    var u_idx: Int, // 회원 번호
    var u_name: String, // 회원 이름
    var u_description: String, // 회원 자기소개
    var u_school: String, // 회원 학교
    var list: ArrayList<TodayArtistProductData> // 회원 작품들
    /*var title:String,
    var a_idx: Int,
    var img_url:String*/
)