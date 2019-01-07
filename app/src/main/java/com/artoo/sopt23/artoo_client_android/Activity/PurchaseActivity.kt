package com.artoo.sopt23.artoo_client_android.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetPurchaseResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Post.PostPurchaseResponse
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_purchase.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat

class PurchaseActivity : AppCompatActivity() {

    val jsonObject = JSONObject()
    var checkPost : Boolean = true
    var checkPayment : Int = 1
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)
        getArtworkDetail() // <--- 사실 여기에서 받는 정보는 intent로 다 넘겨주면 됨! 앞에 intent로 다 보내주시고 이건 지워주세요.

        img_purchase_transfer_express.setOnClickListener {
            img_purchase_transfer_express.setImageResource(R.drawable.purchase_delivery)
            img_purchase_transfer_onface.setImageResource(R.drawable.purchase_no_direct)
            checkPost = true
        }
        img_purchase_transfer_onface.setOnClickListener {
            img_purchase_transfer_express.setImageResource(R.drawable.purchase_no_delivery)
            img_purchase_transfer_onface.setImageResource(R.drawable.purchase_direct)
            checkPost = false
        }

        img_purchase_method_card.setOnClickListener {
            img_purchase_method_card.setImageResource(R.drawable.purchase_card)
            img_purchase_method_transfer.setImageResource(R.drawable.purchase_no_bank)
            checkPayment = 1
        }

        img_purchase_method_transfer.setOnClickListener {
            img_purchase_method_card.setImageResource(R.drawable.purchase_no_card)
            img_purchase_method_transfer.setImageResource(R.drawable.purchase_bank)
            checkPayment = 0
        }

        purchase_btn.setOnClickListener {
            postPurchaseInfo()
        }
    }

    private fun postPurchaseInfo() {
        //jsonObject.put("u_email", input_email)
        if(checkPost == true){
            jsonObject.put("p_isPost", true)
            jsonObject.put("p_payment", checkPayment)
            jsonObject.put("p_recipient", edt_purchase_buyer_name.text.toString())
            jsonObject.put("p_address", edt_purchase_buyer_address.text.toString())
            jsonObject.put("p_phone", edt_purchase_buyer_contact.text.toString())
        }else{
            jsonObject.put("p_isPost", false)
            jsonObject.put("p_payment", checkPayment)
        }
        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
        val a_id = intent.getIntExtra("a_idx", -1)
        val u_id = intent.getIntExtra("u_idx", -1)
        val postPurchaseResponse: Call<PostPurchaseResponse> = networkService.postPurchaseResponse(SharedPreferenceController.getAuthorization(this@PurchaseActivity)
                ,a_id,u_id, gsonObject)
        postPurchaseResponse.enqueue(object : Callback<PostPurchaseResponse>{
            override fun onFailure(call: Call<PostPurchaseResponse>, t: Throwable) {
                Log.e("post_purchase failed", t.toString())
            }

            override fun onResponse(call: Call<PostPurchaseResponse>, response: Response<PostPurchaseResponse>) {
                if(response.isSuccessful){
                    finish()
                }
            }
        })
    }

    private fun getArtworkDetail() {
        val a_id = intent.getIntExtra("a_idx", -1)
        val u_id = intent.getIntExtra("u_idx", -1)
        if (a_id != -1 && u_id != -1) {
            val getArtworkResponse: Call<GetPurchaseResponse> = networkService.getPurchaseResponse(SharedPreferenceController.getAuthorization(this@PurchaseActivity)
                    , a_id, u_id)
            getArtworkResponse.enqueue(object : Callback<GetPurchaseResponse> {
                override fun onFailure(call: Call<GetPurchaseResponse>, t: Throwable) {
                    Log.e("failed getting artwork info", t.toString())
                }

                override fun onResponse(call: Call<GetPurchaseResponse>, response: Response<GetPurchaseResponse>) {
                    if (response.isSuccessful) {
                        val bodyData = response.body()!!.data
                        val url = intent.getStringExtra("pic_url")
                        Glide.with(this@PurchaseActivity).load(url).into(purchase_art_img)// url 안 보냈을때 이거도 붙이면 좋을듯.onLoadFailed()
                        val df = DecimalFormat("#,###")
                        val price = df.format(bodyData.artworkPrice * 0.9) + "원"
                        txt_purchase_price_product.text = price
                        val susuprice = df.format(bodyData.artworkPrice * 0.1) + "원"
                        txt_purchase_price_vat.text = susuprice
                        txt_post_price.text = "3,000원"
                        txt_purchase_price_total.text = df.format(bodyData.artworkPrice + 3000)
                    }
                }
            })
        } else {
            finish()
        }
    }
}
