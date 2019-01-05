package com.artoo.sopt23.artoo_client_android.Data

import java.io.Serializable

data class MainExhibitionData(
        var d_idx : Int,
        var d_sDateNow : String,
        var d_eDateNow : String,
        var d_sDateApply : String,
        var d_eDateApply : String,
        var d_repImg_url : String,
        var d_titleImg_url : String,
        var d_mainImg_url : String,
        var d_title : String,
        var d_subTitle : String,
        var d_longDetail : String,
        var d_shortDetail : String,
        var d_artworkUser : ArrayList<String>
)

// : Serializable 다 넘겨줄때 유용