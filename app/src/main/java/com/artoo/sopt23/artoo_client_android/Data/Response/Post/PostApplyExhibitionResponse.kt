package com.artoo.sopt23.artoo_client_android.Data.Response.Post
import com.google.gson.annotations.SerializedName


data class PostApplyExhibitionResponse (
        @SerializedName("status") val status: Int, // 201
        @SerializedName("message") val message: String, // 전시 등록 성공
        @SerializedName("data") val data: Data
)

data class Data(
        @SerializedName("d_idx") val dIdx: Int, // 1
        @SerializedName("d_title") val dTitle: String, // 네가 그리는 자유展
        @SerializedName("d_subTitle") val dSubTitle: String, // 자유 편
        @SerializedName("u_idx") val uIdx: Int, // 9
        @SerializedName("u_name") val uName: String, // ssss
        @SerializedName("a_idx") val aIdx: Int, // 9
        @SerializedName("a_name") val aName: String, // 내안의 무엇
        @SerializedName("dc_date") val dcDate: String // 2019-01-04
)
