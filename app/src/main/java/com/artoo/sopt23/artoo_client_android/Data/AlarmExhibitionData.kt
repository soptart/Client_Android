package com.artoo.sopt23.artoo_client_android.Data

data class AlarmExhibitionData(
        var display: ExhibitionData,
        var a_idx: Int,
        var a_name: String,
        var u_idx: Int,
        var u_name: String,
        var state: Int,
        var dc_idx: Int,
        var dc_date: String
)

data class ExhibitionData(
        var d_idx: Int,
        var d_sDateNow: String,
        var d_eDateNow: String,
        var d_title: String
)