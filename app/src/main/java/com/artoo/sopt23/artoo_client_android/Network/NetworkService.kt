package com.artoo.sopt23.artoo_client_android.Network

import com.artoo.sopt23.artoo_client_android.Data.Response.Get.*
import com.artoo.sopt23.artoo_client_android.Data.ApplyExhibitionData
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetThemeProductResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetExhibitionDisplayResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetTodayArtistResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Post.PostApplyExhibitionResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Post.PostJoinResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Post.PostLoginResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Post.PostProductUploadResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Post.PostPurchaseResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Put.PutMypagePrefInfoResponse
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

    // 필터&키워드로 작품 조회
    @GET("/artworks/filter")
    fun getProductListResponse(
        @Query("a_size") a_size: String?,
        @Query("a_form") a_form: String?,
        @Query("a_category") a_category: String?,
        @Query("a_keyword") a_keyword: String
    ): Call<GetProductListResponse>

    //ProductUpload::create
    @Multipart
    @POST("/artworks")
    fun postProductUploadResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token: String,
        @Part("a_name") a_name: RequestBody,
        @Part("a_width") a_width: Int,
        @Part("a_height") a_height: Int,
        @Part("a_depth") a_depth: Int,
        @Part("a_category") a_category: RequestBody,
        @Part("a_form") a_form: RequestBody,
        @Part("a_price") a_price: Int,
        //@Part("u_idx") u_idx: Int,
        @Part("a_detail") a_detail: RequestBody,
        @Part("a_year") a_year: RequestBody,
        @Part("a_tags") a_tags: RequestBody,
        @Part("a_license") a_license: RequestBody,
        @Part pic_url: MultipartBody.Part?,
        @Part("a_material") a_material: RequestBody,
        @Part("a_expression") a_expressions: RequestBody
    ): Call<PostProductUploadResponse>

    @GET("/artworks/{a_idx}/purchase/{u_idx}")
    fun getPurchaseResponse(
        @Header("Authorization") token: String,
        @Path("a_idx") a_idx: Int,
        @Path("u_idx") u_idx: Int
    ):Call<GetPurchaseResponse>

    @POST("/artworks/{a_idx}/purchase/{u_idx}")
    fun postPurchaseResponse(
        @Header("Authorization") token: String,
        @Path("a_idx") a_idx: Int,
        @Path("u_idx") u_idx: Int,
        @Body() body: JsonObject
    ):Call<PostPurchaseResponse>

    //테마 상세페이지
    // 테마의 따른 작품 조회 : 홈테마 페이지
    @GET("/themes")
    fun getThemesResponse(
    ): Call<GetThemesResponse>

    // 특정 테마 상세 조회: 테마 상세페이지
    @GET("/themes/details/{t_idx}")
    fun getThemeProductResponse(
            @Path("t_idx") t_idx : Int
    ):Call<GetThemeProductResponse>

    // 모든 전시 조회
    @GET("/displays")
    fun getExhibitionDisplayResponse(
    ): Call<GetExhibitionDisplayResponse>

    //MypageProduct::list
    @GET("/users/{u_idx}")
    fun getMypageProductResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token: String,
        @Path("u_idx") u_idx: Int
    ): Call<GetMypageProductResponse>

    //MypageLike::list
    @GET("/users/{u_idx}/likes")
    fun getMypageLikeResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token: String,
        @Path("u_idx") u_idx: Int
    ): Call<GetMypageLikeResponse>

    //MypageDeal::list
    @GET("/users/{u_idx}/purchases")
    fun getMypageDealResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token: String,
        @Path("u_idx") u_idx: Int
    ): Call<GetMypageDealResponse>

    //MypageReview::list
    @GET("/users/{u_idx}/reviews")
    fun getMypageReviewResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token: String,
        @Path("u_idx") u_idx: Int
    ): Call<GetMypageReviewResponse>

    //MypageUserDescription::update
    @PUT("/users/{u_idx}/myInfo")
    fun putMypagePrefIntroResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token: String,
        @Path("u_idx") u_idx: Int
     ): Call<PutMypagePrefInfoResponse>

    //MypagePrefer::list
    @GET("/users/{u_idx}/myInfo")
    fun getMypagePrefInfoResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token: String,
        @Path("u_idx") u_idx: Int
    ): Call<GetMypagePrefInfoResponse>

    // 전시 신청
    @Headers("Content-Type: application/json")
    @POST("/discontents/{user_idx}")
    fun postApplyExhibitionResponse(
            //@Header("Content-Type") content_type: String,
            @Header("Authorization") token: String,
            @Path("user_idx") user_idx: Int,
            @Body applyExhibitionData: ApplyExhibitionData
    ): Call<PostApplyExhibitionResponse>
}