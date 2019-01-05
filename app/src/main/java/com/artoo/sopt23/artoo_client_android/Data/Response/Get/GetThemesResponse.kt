package com.artoo.sopt23.artoo_client_android.Data.Response.Get

data class GetThemesResponse (
        val status: Int,
        val message: String,
        val data: ArrayList<ThemeTagData>
)

data class ThemeTagData(
        val t_idx: Int,
        val t_shortTag: String,
        val t_mainTag: String,
        val t_subTag: String,
        val t_imgUrl: String,
        val list: ArrayList<ThemeListData>
)

data class ThemeListData(
        val a_idx: Int,
        val pic_url: String
)