package com.artoo.sopt23.artoo_client_android.Activity

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.artoo.sopt23.artoo_client_android.DB.SharedPreferenceController
import com.artoo.sopt23.artoo_client_android.Data.ProductDetailData
import com.artoo.sopt23.artoo_client_android.Data.ProductUploadTagLayoutData
import com.artoo.sopt23.artoo_client_android.Data.Response.Post.PostProductUploadResponse
import com.artoo.sopt23.artoo_client_android.Data.Response.Put.PutProductUpdateResponse
import com.artoo.sopt23.artoo_client_android.Fragment.ProductUploadAlertFragment
import com.artoo.sopt23.artoo_client_android.Fragment.ProductUploadThemeFragment
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import com.artoo.sopt23.artoo_client_android.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_product_upload.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.net.URL
import java.util.ArrayList

class ProductUploadActivity : AppCompatActivity() {

    val REQUEST_CODE_SELECT_IMAGE: Int = 1000
    var input_product_img: MultipartBody.Part? = null
    var a_idx = -1
    lateinit var input_product_purchase_state: String

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    val themes = ArrayList<String>()
    val layouts = ArrayList<ProductUploadTagLayoutData>()

    var categories = arrayOf("선택[카테고리]", "인물", "동물", "식물", "사물", "추상", "풍경")
    var formats = arrayOf("선택[형태]", "드로잉", "페인팅", "동양화", "혼합매체", "조형/공예", "사진")
    var licenses = arrayOf(
            "선택[저작권]",
            "CCL표시안함",
            "저작권표시",
            "저작권표시-비영리",
            "저작자표시-동일조건변경허락",
            "저작자표시-변경금지",
            "저작자표시-비영리-동일조건변경허락",
            "저작자표시-비영리-변경금지"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_upload)

        setAlertDialog()
        setViewClickListener()

        spn_product_upload_category.adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)

        spn_product_upload_format.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, formats)

        spn_product_upload_license.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, licenses)
        setSpinnerItemSelectedListener()

        if(intent.hasExtra("productDetailData")){ // Modify
            mockupProductDetailData()
        }
    }

    private fun mockupProductDetailData(){
        var productDetailData: ProductDetailData = intent.getSerializableExtra("productDetailData") as ProductDetailData
        a_idx = productDetailData.a_idx
//        txt_product_upload_info.visibility = View.GONE
//        //Glide을 사진 URI를 ImageView에 넣은 방식. 외부 URI가 아니라 굳이 Glide을 안써도 되지만 ᄒᄒ!\
//        Glide.with(this@ProductUploadActivity).load(productDetailData.pic_url).thumbnail(0.1f)
//                .into(iv_product_upload_product_img)
        et_product_upload_product_year.setText(productDetailData.a_year)
        et_product_upload_product_title.setText(productDetailData.a_name)
        et_product_upload_product_detail.setText(productDetailData.a_detail)
        et_product_upload_size_width.setText(productDetailData.a_width.toString())
        et_product_upload_size_height.setText(productDetailData.a_height.toString())
        et_product_upload_size_depth.setText(productDetailData.a_depth.toString())
        et_product_upload_price.setText(productDetailData.a_price.toString())
        et_product_upload_material.setText(productDetailData.a_material)
        et_product_upload_tip.setText(productDetailData.a_expression)

//        val file:File = File(URL(productDetailData.pic_url).toURI())
//        val photoBody =
//                RequestBody.create(MediaType.parse("image/jpg"), file)
//        //첫번째 매개변수 String을 꼭! 꼭! 서버 API에 명시된 이름으로 넣어주세요!!!
//        input_product_img = MultipartBody.Part.createFormData(
//                "pic_url",
//                productDetailData.pic_url,
//                photoBody
//        )
    }

    private fun setAlertDialog() {
        val alert_dialog = ProductUploadAlertFragment()
        alert_dialog.show(supportFragmentManager, alert_dialog.tag)
    }

    private fun setViewClickListener() {
        btn_product_upload_close.setOnClickListener {
            try {
                finish()
            } catch (e: Exception) {
            }
        }
        iv_product_upload_product_img.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
                intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE)
            } catch (e: Exception) {
            }
        }
        btn_product_upload_add_theme.setOnClickListener {
            try {
                val bottom_sheet_dialog = ProductUploadThemeFragment()
                bottom_sheet_dialog.show(supportFragmentManager, bottom_sheet_dialog.tag)
            } catch (e: Exception) {
            }
        }
        /*btn_product_upload_delivery_parcel.setOnClickListener {
            input_product_purchase_state = btn_product_upload_delivery_parcel.text.toString()
        }
        btn_product_upload_delivery_direct.setOnClickListener {
            input_product_purchase_state = btn_product_upload_delivery_direct.text.toString()
        }*/
        btn_product_upload_finish.setOnClickListener {
            try {
                if (iv_product_upload_product_img.resources == null) {
                    toast("작품 사진을 첨부해주세요.")
                } else if (et_product_upload_product_title.text.toString().trim().length == 0) {
                    toast("작품명을 작성해주세요.")
                } else if(et_product_upload_product_detail.text.toString().trim().length == 0) {
                    toast("작품 설명을 작성해주세요.")
                } else if (et_product_upload_size_width.text.toString().trim().length == 0 || et_product_upload_size_depth.text.toString().trim().length == 0) {
                    toast("작품 크기의 가로, 세로는 필수값입니다.")
                } else if (et_product_upload_size_width.text.toString().toInt() < 0 || et_product_upload_size_width.text.toString().toInt() >=163
                        || et_product_upload_size_depth.text.toString().toInt() < 0 || et_product_upload_size_depth.text.toString().toInt() >= 163) {
                    toast("작품의 최대 가로, 세로 길이는 163cm입니다.\n다시 입력해주세요.")
                } else if (et_product_upload_price.text.toString().trim().length == 0) {
                    toast("작품의 가격을 입력해주세요.")
                } else if (et_product_upload_material.text.toString().trim().length == 0) {
                    toast("작품에 사용한 재료를 기입해주세요.")
                } else {

                    getProductUploadResponse()
                }
            } catch (e: Exception) {
            }

        }
    }

    private fun setSpinnerItemSelectedListener() {

        spn_product_upload_category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }
        }
        spn_product_upload_format.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }
        }
        spn_product_upload_license.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }
        }
    }

    fun setTag() {

        for (i in 0 until themes.size)
            Log.d(":::::product_upload_theme_activity:::::themes:::::setTag::", i.toString() + themes[i])
        Log.d(":::::product_upload_theme_activity:::::themes:::::setTag::layouts.size::", layouts.size.toString())

        layouts.clear()

        if (themes.size == 0) {
            /*해시태그 선택 안했을 때*/
            btn_product_upload_add_theme.visibility = View.VISIBLE
            rl_product_upload_tag1.visibility = View.GONE
            rl_product_upload_tag2.visibility = View.GONE
            rl_product_upload_tag3.visibility = View.GONE
        } else if (themes.size == 1) {
            /*해시태그 1개 선택 했을 때*/

            layouts.add(ProductUploadTagLayoutData(rl_product_upload_tag1, tv_product_upload_tag1))

            setRemoveTagButtonClickListener()

            tv_product_upload_tag1.setText(themes[0])
            btn_product_upload_add_theme.visibility = View.VISIBLE
            rl_product_upload_tag1.visibility = View.VISIBLE
            rl_product_upload_tag2.visibility = View.GONE
            rl_product_upload_tag3.visibility = View.GONE

        } else if (themes.size == 2) {
            /*해시태그 2개 선택 했을 때*/
            layouts.add(ProductUploadTagLayoutData(rl_product_upload_tag1, tv_product_upload_tag1))
            layouts.add(ProductUploadTagLayoutData(rl_product_upload_tag2, tv_product_upload_tag2))

            setRemoveTagButtonClickListener()

            tv_product_upload_tag1.setText(themes[0])
            tv_product_upload_tag2.setText(themes[1])
            btn_product_upload_add_theme.visibility = View.VISIBLE
            rl_product_upload_tag1.visibility = View.VISIBLE
            rl_product_upload_tag2.visibility = View.VISIBLE
            rl_product_upload_tag3.visibility = View.GONE
        } else if (themes.size == 3) {
            /*해시태그 3개 선택 했을 때*/
            layouts.add(ProductUploadTagLayoutData(rl_product_upload_tag1, tv_product_upload_tag1))
            layouts.add(ProductUploadTagLayoutData(rl_product_upload_tag2, tv_product_upload_tag2))
            layouts.add(ProductUploadTagLayoutData(rl_product_upload_tag3, tv_product_upload_tag3))

            setRemoveTagButtonClickListener()

            tv_product_upload_tag1.setText(themes[0])
            tv_product_upload_tag2.setText(themes[1])
            tv_product_upload_tag3.setText(themes[2])
            btn_product_upload_add_theme.visibility = View.GONE
            rl_product_upload_tag1.visibility = View.VISIBLE
            rl_product_upload_tag2.visibility = View.VISIBLE
            rl_product_upload_tag3.visibility = View.VISIBLE

        }
    }

    private fun setRemoveTagButtonClickListener() {

        btn_product_upload_delete_tag1.setOnClickListener {
            try {
                removeTag(tv_product_upload_tag1.text.toString())
            } catch (e: Exception) {
            }
        }
        btn_product_upload_delete_tag2.setOnClickListener {
            try {
                removeTag(tv_product_upload_tag2.text.toString())
            } catch (e: Exception) {
            }
        }
        btn_product_upload_delete_tag3.setOnClickListener {
            try {
                removeTag(tv_product_upload_tag3.text.toString())
            } catch (e: Exception) {
            }
        }

    }

    fun removeTag(rTag: String) {

        var rIdx: Int = -1

        Log.e("ABAB:rTag", rTag)
        for (i in 0 until themes.size) {
            Log.e("ABAB:themes", themes.get(i))
            if (themes.get(i).equals(rTag)) {
                rIdx = i
                Log.d(":::::product_upload_theme_activity:::::themes:::::removeTag::rTag::", themes.get(rIdx))
                break
            }
        }

        layouts.removeAt(rIdx)
        themes.removeAt(rIdx)
        setTag()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                data?.let {
                    var seletedPictureUri = it.data
                    val options = BitmapFactory.Options()
                    val inputStream: InputStream = contentResolver.openInputStream(seletedPictureUri)
                    val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
                    val photoBody =
                            RequestBody.create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray())
                    //첫번째 매개변수 String을 꼭! 꼭! 서버 API에 명시된 이름으로 넣어주세요!!!
                    input_product_img = MultipartBody.Part.createFormData(
                            "pic_url",
                            File(seletedPictureUri.toString()).name + ".jpg",
                            photoBody
                    )
                    txt_product_upload_info.visibility = View.GONE
                    //Glide을 사진 URI를 ImageView에 넣은 방식. 외부 URI가 아니라 굳이 Glide을 안써도 되지만 ᄒᄒ!\
                    Glide.with(this@ProductUploadActivity).load(seletedPictureUri).thumbnail(0.1f)
                            .into(iv_product_upload_product_img)
                }
            }
        }

    }

    private fun getProductUploadResponse() {

        Log.d("*****ProductUploadActivity::getProductUploadResponse::",  spn_product_upload_category.selectedItem.toString() + "::" + et_product_upload_product_year.text.toString() + "::"
                + iv_product_upload_product_img.toString() + "::" + et_product_upload_size_depth.text.toString())
        //input_product_img
        val input_product_category = RequestBody.create(MediaType.parse("text/plain"), spn_product_upload_category.selectedItem.toString())
        val input_product_format = RequestBody.create(MediaType.parse("text/plain"), spn_product_upload_format.selectedItem.toString())
        val input_product_license = RequestBody.create(MediaType.parse("text/plain"), spn_product_upload_license.selectedItem.toString())
        val input_product_year = RequestBody.create(MediaType.parse("text/plain"), et_product_upload_product_year.text.toString())

        val input_product_title = RequestBody.create(MediaType.parse("text/plain"), et_product_upload_product_title.text.toString())
        val input_product_detail = RequestBody.create(MediaType.parse("text/plain"), et_product_upload_product_detail.text.toString())
        val input_tags = RequestBody.create(MediaType.parse("text/plain"), "1,7")


        var input_product_width = -1
        if (et_product_upload_size_width.text.toString().isNotEmpty()) {
            input_product_width = et_product_upload_size_width.text.toString().toInt()
        }

        var input_product_depth = -1
        if (et_product_upload_size_depth.text.toString().isNotEmpty()) {
            input_product_depth = et_product_upload_size_depth.text.toString().toInt()
        }

        var input_product_height = 1
        if (et_product_upload_size_height.text.toString().isNotEmpty()) {
            input_product_height = et_product_upload_size_height.text.toString().toInt()
        }

        var input_product_price = -1
        if(et_product_upload_price.text.toString().isNotEmpty()) {
            input_product_price = et_product_upload_price.text.toString().toInt()
        }
        var input_purchase_state = 1
        if (cb_purchase_state.isChecked) {
            input_purchase_state = 0
        }

        val input_product_material = RequestBody.create(MediaType.parse("text/plain"), et_product_upload_material.text.toString())
        val input_product_tip = RequestBody.create(MediaType.parse("text/plain"), et_product_upload_tip.text.toString())

        if (input_product_img!=null && et_product_upload_price.text.toString().isNotEmpty() && et_product_upload_product_title.text.toString().isNotEmpty() && et_product_upload_product_year.text.toString().isNotEmpty()
                && et_product_upload_size_width.text.toString().isNotEmpty() && et_product_upload_size_depth.text.toString().isNotEmpty() && spn_product_upload_category.selectedItemPosition>0 &&
                spn_product_upload_format.selectedItemPosition>0 && spn_product_upload_license.selectedItemPosition>0) {
            val token = SharedPreferenceController.getAuthorization(this)
            val u_idx = SharedPreferenceController.getUserID(this)
            if(!intent.hasExtra("productDetailData")) {

                val postProductUploadResponse = networkService.postProductUploadResponse(token,
                        input_product_title,
                        input_product_width, input_product_height, input_product_depth,
                        input_product_category, input_product_format, input_product_price, input_purchase_state,
                        u_idx, input_product_detail, input_product_year,
                        input_tags, input_product_license, input_product_material, input_product_tip, input_product_img!!/*, input_product_material, input_product_tip*/)

                Log.d("*****ProductUploadActivity::", postProductUploadResponse.toString())

                postProductUploadResponse.enqueue(object : Callback<PostProductUploadResponse> {
                    override fun onFailure(call: Call<PostProductUploadResponse>, t: Throwable) {
                        Log.d("*****ProductUploadActivity::", t.toString())
                    }

                    override fun onResponse(call: Call<PostProductUploadResponse>, response: Response<PostProductUploadResponse>) {
                        if (response.isSuccessful) {
                            toast(response.body()!!.message).show()
                            finish()
                        } else {
                            toast(response.body()!!.message).show()
                        }
                    }
                })
            }
            else{
                val putPostProductUpdateResponse = networkService.putProductUpdateResponse(token, a_idx,
                        input_product_title,
                        input_product_width, input_product_height, input_product_depth,
                        input_product_category, input_product_format, input_product_price,
                        u_idx, input_product_detail, input_product_year,
                        input_tags, input_product_license, input_product_material, input_product_tip, input_product_img!!)
                putPostProductUpdateResponse.enqueue(object: Callback<PutProductUpdateResponse>{
                    override fun onFailure(call: Call<PutProductUpdateResponse>, t: Throwable) {
                        Log.d("*****ProductUpdateActivity::", t.toString())
                    }

                    override fun onResponse(call: Call<PutProductUpdateResponse>, response: Response<PutProductUpdateResponse>) {
                        if (response.isSuccessful) {
                            toast(response.body()!!.message)
                            finish()
                        } else {
                            toast(response.body()!!.message)
                        }
                    }
                })
            }
        }
    }
}