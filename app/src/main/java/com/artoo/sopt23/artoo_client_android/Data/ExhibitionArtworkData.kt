package com.artoo.sopt23.artoo_client_android.Data

import java.util.*

data class ExhibitionArtworkData (
        val a_idx : Int,
        val a_name : String,
        val a_width : Int,
        val a_height : Int,
        val a_depth : Int,
        val a_category : String,
        val a_form : String,
        val a_price : Int,
        val a_like_count : Int,
        val u_idx : Int,
        val a_detail : String,
        val a_date : Date,
        val a_year : String,
        val pic_url : String,
        val auth : Boolean,
        val islike : Boolean,
        val a_tags : String,
        val a_license : String,
        val a_size : Int,
        val a_isDisplay : Boolean,
        val a_active : Boolean

)