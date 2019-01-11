package com.artoo.sopt23.artoo_client_android.Data.Response.Get

import com.artoo.sopt23.artoo_client_android.Data.AlarmBuyData

data class GetAlarmBuyResponse (
    var status: Int,
    var message: String,
    var data : ArrayList<AlarmBuyData>
)