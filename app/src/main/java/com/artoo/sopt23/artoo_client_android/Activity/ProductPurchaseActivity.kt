package com.artoo.sopt23.artoo_client_android.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.artoo.sopt23.artoo_client_android.CustomView.PreparingDialog
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
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

class ProductPurchaseActivity : AppCompatActivity() {

    val jsonObject = JSONObject()
    var checkPost : Boolean = true
    var checkPayment : Int = 0
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)
        getArtworkDetail() // <--- 사실 여기에서 받는 정보는 intent로 다 넘겨주면 됨! 앞에 intent로 다 보내주시고 이건 지워주세요.

        img_purchase_transfer_express.setOnClickListener {
            try {
                img_purchase_transfer_express.setImageResource(R.drawable.purchase_delivery)
                img_purchase_transfer_onface.setImageResource(R.drawable.purchase_no_direct)
                checkPost = true
                post_buy_container.visibility = View.VISIBLE
                post_description.visibility = View.VISIBLE
                direct_buy_container.visibility = View.GONE
            } catch (e: Exception) {
            }
        }
        img_purchase_transfer_onface.setOnClickListener {
            try {
                img_purchase_transfer_express.setImageResource(R.drawable.purchase_no_delivery)
                img_purchase_transfer_onface.setImageResource(R.drawable.purchase_direct)
                checkPost = false
                post_buy_container.visibility = View.GONE
                post_description.visibility = View.GONE
                direct_buy_container.visibility = View.VISIBLE
            } catch (e: Exception) {
            }
        }

        img_purchase_method_card.setOnClickListener {
            try {/*img_purchase_method_card.setImageResource(R.drawable.purchase_card)
            img_purchase_method_transfer.setImageResource(R.drawable.purchase_no_bank)
            checkPayment = 1*/
                val dialog = PreparingDialog(this@ProductPurchaseActivity)
                dialog.show()
            } catch (e: Exception) {
            }
        }

        img_purchase_method_transfer.setOnClickListener {
            try {
                img_purchase_method_card.setImageResource(R.drawable.purchase_no_card)
                img_purchase_method_transfer.setImageResource(R.drawable.purchase_bank)
                checkPayment = 0
            } catch (e: Exception) {
            }
        }

        purchase_btn.setOnClickListener {
            try {
                if(rbtn_purchase_agree_warning.isChecked && rbtn_purchase_agree_refund.isChecked){
                    postPurchaseInfo()
                }else{
                    Toast.makeText(this@ProductPurchaseActivity, "약관을 모두 동의해주세요.", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
            }
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
        val postPurchaseResponse: Call<PostPurchaseResponse> = networkService.postPurchaseResponse(SharedPreferenceController.getAuthorization(this@ProductPurchaseActivity)
                ,a_id,SharedPreferenceController.getUserID(this@ProductPurchaseActivity), gsonObject)
        postPurchaseResponse.enqueue(object : Callback<PostPurchaseResponse>{
            override fun onFailure(call: Call<PostPurchaseResponse>, t: Throwable) {
                Log.e("post_purchase failed", t.toString())
            }

            override fun onResponse(call: Call<PostPurchaseResponse>, response: Response<PostPurchaseResponse>) {
                Log.i("ldfadf", response.body().toString())
                Log.i("ldfadfd", response.body()?.status.toString())
                if(response.isSuccessful){
                    Log.i("asfwrs", response.toString())
                    if(response.body()?.message.equals("구매 정보 저장 성공")){
                        Toast.makeText(this@ProductPurchaseActivity, "구매 신청이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@ProductPurchaseActivity, ProductPurchaseResultActivity::class.java)
                        intent.putExtra("isPost", checkPost)
                        intent.putExtra("artname", intent.getStringExtra("a_name"))
                        intent.putExtra("price", total_price_txt_bottom.text.toString())
                        intent.putExtra("value1", "Artoo(윤여진)")
                        intent.putExtra("value2","010-3395-9980")
                        intent.putExtra("value3","농협 301-0243-2595-01")
                        startActivity(intent)
                        finish()
                    }else if(response.body()?.status ==204){
                        Toast.makeText(this@ProductPurchaseActivity, "이미 구매중인 상품이 있습니다.", Toast.LENGTH_SHORT).show()
                    }
                }/*else if(response.body()!!.status==400){
                    Toast.makeText(this@ProductPurchaseActivity, "미기입 사항을 입력해주세요", Toast.LENGTH_SHORT).show()
                }*/
                else{
                    Log.i("asfwr", response.toString())
                    Toast.makeText(this@ProductPurchaseActivity, "미기입 사항을 입력해주세요", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun getArtworkDetail() {
        val a_id = intent.getIntExtra("a_idx", -1)
        val u_id = intent.getIntExtra("u_idx", -1)
        if (a_id != -1) {
            val url = intent.extras.getString("pic_url")
            val artworkPrice = intent.getIntExtra("price", 0)
            Log.i("purchase_art_img", url)
            Glide.with(this@ProductPurchaseActivity).load(url).into(purchase_art_img)
            val df = DecimalFormat("#,###")
            val price = df.format(artworkPrice * 1.0) + "원"
            txt_purchase_price_product.text = price
            val susuprice = df.format(artworkPrice * 0.1) + "원"
            val artworkPrice11 = df.format(artworkPrice * 1.1) + "원"
            txt_purchase_price.text = artworkPrice11
            txt_purchase_price_vat.text = susuprice
            val size = intent.getIntExtra("size", 0)
            var postPrice : Int
            if(artworkPrice > 150000) postPrice = 0
            else if(size < 2412) postPrice = 3000
            else if(size < 6609) postPrice =4000
            else postPrice = 5000
            if(artworkPrice>150000){
                postPrice = 0
            }
            var besongprice = postPrice.toString() +"원"
            txt_post_price.text = besongprice
            txt_purchase_price_total.text = df.format(artworkPrice * 1.1 + postPrice)+"원"
            total_price_txt_bottom.text = df.format(artworkPrice * 1.1 + postPrice)+"원"
            txt_purchase_title.text = intent.getStringExtra("a_name")
            val artistName = intent.getStringExtra("u_school")+ " "+intent.getStringExtra("u_name")
            txt_purchase_artist.text = artistName
            /*val getArtworkResponse: Call<GetPurchaseResponse> = networkService.getPurchaseResponse(SharedPreferenceController.getAuthorization(this@ProductPurchaseActivity)
                    , a_id, u_id)
            getArtworkResponse.enqueue(object : Callback<GetPurchaseResponse> {
                override fun onFailure(call: Call<GetPurchaseResponse>, t: Throwable) {
                    Log.e("failed getting artwork info", t.toString())
                }

                override fun onResponse(call: Call<GetPurchaseResponse>, response: Response<GetPurchaseResponse>) {
                    if (response.isSuccessful) {

                    }
                }
            })*/
        } else {
            Log.i("purchase finished", "finished")
            finish()
        }
    }
}

















