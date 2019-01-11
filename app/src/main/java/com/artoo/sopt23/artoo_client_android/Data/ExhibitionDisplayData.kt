package com.artoo.sopt23.artoo_client_android.Data

data class ExhibitionDisplayData (
        val d_idx : Int,
        val d_sDateNow : String,
        val d_eDateNow : String,
        val d_sDateApply : String,
        val d_eDateApply : String,
        val d_title : String,
        val d_subTitle : String,
        val d_shortDetail : String,
        val d_artworkUser : ArrayList<String>
)
