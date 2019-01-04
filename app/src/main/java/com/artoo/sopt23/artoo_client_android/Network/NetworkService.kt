package com.artoo.sopt23.artoo_client_android.Network

import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetThemeProductResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetExhibitionDisplayResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetTodayArtistResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetUserDescResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Post.PostJoinResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Post.PostLoginResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Post.PostProductUploadResponse
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {
    // 오늘의 작품 조회
    @GET("/today")
    fun getTodayArtistResponse(
    ): Call<GetTodayArtistResponse>

    // 유저 소개 조회
    @GET("/users/{u_idx}/description")
    fun getUserDescResponse(
        @Header("Content-Type") content_type: String,
        @Path("u_idx") u_idx: Int
    ): Call<GetUserDescResponse>

    //Join
    @POST("/users")
    fun postJoinResponse(
        @Header("Content-Type") content_type: String,
        @Body() body: JsonObject
    ): Call<PostJoinResponse>

    //Login
    @POST("/login")
    fun postLoginResponse(
        @Header("Content-Type") content_type: String,
        @Body() body: JsonObject
    ): Call<PostLoginResponse>

    //ProductUpload::create
    @Multipart
    @POST("/artworks")
    fun postProductUploadResponse(
        @Header("Authorization") token: String,
        @Part("a_name") a_name: RequestBody,
        @Part("a_width") a_width: Int,
        @Part("a_height") a_height: Int,
        @Part("a_depth") a_depth: Int,
        @Part("a_category") a_category: RequestBody,
        @Part("a_form") a_form: RequestBody,
        @Part("a_price") a_price: Int,
        @Part("u_idx") u_idx: Int,
        @Part("a_detail") a_detail: RequestBody,
        @Part("a_year") a_year: RequestBody,
        @Part("a_tags") a_tags: RequestBody,
        @Part("a_license") a_license: RequestBody,
        @Part pic_url: MultipartBody.Part///,
        //@Part("a_material") a_material: RequestBody,
        //@Part("a_expression") a_expressions: RequestBody
    ): Call<PostProductUploadResponse>

    //테마 상세페이지
    @GET("/themes/details/{t_idx}")
    fun getThemeProductResponse(
    ):Call<GetThemeProductResponse>

    // 모든 전시 조회
    @GET("/displays")
    fun getExhibitionDisplayResponse(
    ) : Call<GetExhibitionDisplayResponse>

}