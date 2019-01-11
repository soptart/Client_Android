package com.artoo.sopt23.artoo_client_android.Network

import com.artoo.sopt23.artoo_client_android.Data.Response.Get.*
import com.artoo.sopt23.artoo_client_android.Data.ApplyExhibitionData
import com.artoo.sopt23.artoo_client_android.Data.Response.Delete.*
import com.artoo.sopt23.artoo_client_android.Data.Response.Post.*
import com.artoo.sopt23.artoo_client_android.Data.Response.Put.*
import com.artoo.sopt23.artoo_client_android.Data.Response.Put.PutMypageMyInfoResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Put.PutMypageMyInfoPWResponse
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

    // 유저 소개 조회
    @GET("/users/{u_idx}/description")
    fun getUserDescResponse(
        @Header("Content-Type") content_type: String,
        @Path("u_idx") u_idx: Int
    ): Call<GetUserDescResponse>

    @POST("/users/u_email/{u_email}")
    fun getDuplicateEmailResponse(
        @Path("u_email") u_email: String
    ): Call<GetDuplicateEmailResponse>

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

    // 필터&키워드로 작품 조회
    @GET("/artworks/filter/{a_idx}")
    fun getProductListLimitedResponse(
            @Path("a_idx") a_idx: Int,
            @Query("a_size") a_size: String?,
            @Query("a_form") a_form: String?,
            @Query("a_category") a_category: String?,
            @Query("a_keyword") a_keyword: String
    ): Call<GetProductListResponse>

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
            @Part("a_purchaseState") a_purchaseState: Int,
            @Part("u_idx") u_idx: Int,
            @Part("a_detail") a_detail: RequestBody,
            @Part("a_year") a_year: RequestBody,
            @Part("a_tags") a_tags: RequestBody,
            @Part("a_license") a_license: RequestBody,
            @Part("a_material") a_material: RequestBody,
            @Part("a_expression") a_expressions: RequestBody,
            @Part pic_url: MultipartBody.Part
    ): Call<PostProductUploadResponse>

    @Multipart
    @PUT("/artworks/{a_idx}")
    fun putProductUpdateResponse(
            @Header("Authorization") token: String,
            @Path("a_idx") a_idx: Int,
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
            @Part("a_material") a_material: RequestBody,
            @Part("a_expression") a_expressions: RequestBody,
            @Part pic_url: MultipartBody.Part
    ): Call<PutProductUpdateResponse>

    @DELETE("/artworks/delete/{a_idx}")
    fun deleteProductResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token: String,
        @Path("a_idx") a_idx: Int
    ): Call<DeleteProductResponse>

    @GET("/artworks/{a_idx}")
    fun getProductDetailResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token: String,
        @Path("a_idx") a_idx: Int
    ): Call<GetProductDetailResponse>

    @POST("/artworks/{a_idx}/likes/{u_idx}")
    fun postProductLikeResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token: String,
        @Path("a_idx") a_idx: Int,
        @Path("u_idx") u_idx: Int
    ): Call<PostProductLikeResponse>

    @GET("/artworks/{a_idx}/purchase/{u_idx}")
    fun getPurchaseResponse(
        @Header("Authorization") token: String,
        @Path("a_idx") a_idx: Int,
        @Path("u_idx") u_idx: Int
    ):Call<GetPurchaseResponse>

    @POST("/artworks/{a_idx}/purchases/{u_idx}")
    fun postPurchaseResponse(
            @Header("Authorization") token: String,
            @Path("a_idx") a_idx: Int,
            @Path("u_idx") u_idx: Int,
            @Body() body: JsonObject
    ):Call<PostPurchaseResponse>

    @GET("/comments/{a_idx}")
    fun getProductCommentResponse(
        @Header("Authorization") token: String,
        @Path("a_idx") a_idx: Int
    ):Call<GetProductCommentResponse>

    @POST("/comments")
    fun postProductCommentResponse(
        @Header("Authorization") token: String,
        @Body() body: JsonObject
    ):Call<PostProductCommentResponse>

    @DELETE("/comments/{c_idx}")
    fun deleteProductCommentResponse(
        @Header("Authorization") token: String,
        @Header("u_idx") u_idx: Int,
        @Path("c_idx") c_idx: Int
    ):Call<DeleteProductCommentResponse>

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

    //OtherpageProduct::list
    @GET("/users/{u_idx}")
    fun getOtherpageProductResponse(
        @Header("Content-Type") content_type: String,
        @Path("u_idx") u_idx: Int
    ): Call<GetOtherpageProductResponse>

    //OtherpageLike::list
    @GET("/users/{u_idx}/likes")
    fun getOtherpageLikeResponse(
        @Header("Content-Type") content_type: String,
        @Path("u_idx") u_idx: Int
    ): Call<GetOtherpageLikeResponse>

    //OtherpagerReview::list
    @GET("/users/{u_idx}/reviews")
    fun getOtherpageReviewResponse(
        @Header("Content-Type") content_type: String,
        @Path("u_idx") u_idx: Int
    ): Call<GetOtherpageReviewResponse>

    //MypageUserDescription::update
    @PUT("/users/{u_idx}/myInfo")
    fun putMypagePrefInfoResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token: String,
        @Path("u_idx") u_idx: Int,
        @Body() body: JsonObject
     ): Call<PutMypagePrefInfoResponse>

    //MypagePrefer::list
    @GET("/users/{u_idx}/myInfo")
    fun getMypagePrefInfoResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token: String,
        @Path("u_idx") u_idx: Int
    ): Call<GetMypagePrefInfoResponse>

    //OtherpagePrefer::list
    @GET("/users/{u_idx}/myInfo")
    fun getOtherpagePrefInfoResponse(
            @Header("Content-Type") content_type: String,
            @Path("u_idx") u_idx: Int
    ): Call<GetOtherpagePrefInfoResponse>

    @GET("/users/{u_idx}/myInfo")
    fun getMypageMyInfoResponse(
        @Header("Authorization") token: String,
        @Path("u_idx") u_idx: Int
    ): Call<GetMypageMyInfoResponse>

    @PUT("/users/{u_idx}/myInfo")
    fun putMypageMyInfoResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token: String,
        @Path("u_idx") u_idx: Int,
        @Body() body: JsonObject
    ): Call<PutMypageMyInfoResponse>

    @PUT("/users/{u_idx}/myInfo/pw")
    fun putMypageMyInfoPWResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token: String,
        @Path("u_idx") u_idx: Int,
        @Body() body: JsonObject
    ): Call<PutMypageMyInfoPWResponse>

    // 전시 신청
    @Headers("Content-Type: application/json")
    @POST("/discontents/{user_idx}")
    fun postApplyExhibitionResponse(
            //@Header("Content-Type") content_type: String,
            @Header("Authorization") token: String,
            @Path("user_idx") user_idx: Int,
            @Body applyExhibitionData: ApplyExhibitionData
    ): Call<PostApplyExhibitionResponse>

    // 전시 신청서 띄우기
    @GET("/discontents/application/{user_idx}")
    fun getApplyExhibitionResponse(
            @Header("Authorization") token: String,
            @Path("user_idx") user_idx: Int
    ): Call<GetApplyExhibitionResponse>

    // 전시 관람
    @GET("/discontents/displays/{display_idx}")
    fun getDetailExhibitionResponse(
            @Header("Authorization") token: String,
            @Path("display_idx") display_idx: Int
    ) : Call<GetDetailExhibitionResponse>


    // 구매 내역 조회 : 알림구매내역페이지
    @GET("/notices/buys/{user_idx}")
    fun getAlarmBuyResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token:String,
        @Path("user_idx") user_idx: Int
    ) : Call<GetAlarmBuyResponse>

    // 판매 내역 조회 : 알림판매내역페이지
    @GET("/notices/sells/{user_idx}")
    fun getAlarmSellResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token:String,
        @Path("user_idx") user_idx: Int
    ) : Call<GetAlarmSellResponse>

    // 전시 내역 조회 : 알림전시내역페이지
    @GET("/notices/displays/users/{user_idx}")
    fun getAlarmExhibitionResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token:String,
        @Path("user_idx") user_idx: Int
    ) : Call<GetAlarmExhibitionResponse>

    // 후기 작성 : 알림구매내역 결제완료 후기작성버튼
    @POST("/notices/buys/{p_idx}")
    fun postCommentResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token:String,
        @Body() body: JsonObject,
        @Path("p_idx") p_idx: Int
    ) : Call<PostCommentResponse>

    // 전시 취소 : 알림전시내역 전시취소버튼
    @DELETE("/discontents/{displaycontent_idx}/users/{user_idx}")
    fun deleteExhibitionResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token:String,
        @Path("displaycontent_idx") displaycontent_idx : Int,
        @Path("user_idx") u_idx: Int
    ) : Call<DeleteExhibitionResponse>
}