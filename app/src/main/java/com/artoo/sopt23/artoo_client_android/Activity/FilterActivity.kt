package com.artoo.sopt23.artoo_client_android.Activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.activity_filter.*

class FilterActivity : AppCompatActivity() {

    var filter_size: String? = null
    var filter_type: String? = null
    var filter_category: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        filter_size = intent.getStringExtra("filter_size")
        filter_type = intent.getStringExtra("filter_type")
        filter_category = intent.getStringExtra("filter_category")

        updateFilterView()
        setOnclickListener()
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    fun updateFilterView(){
        // init size
        img_filter_size_l.isSelected = false
        img_filter_size_m.isSelected = false
        img_filter_size_s.isSelected = false
        img_filter_size_xl.isSelected = false
        img_filter_size_all.isSelected = false

        // init category
        img_filter_category_scenery.isSelected = false
        img_filter_category_animal.isSelected = false
        img_filter_category_human.isSelected = false
        img_filter_category_object.isSelected = false
        img_filter_category_plant.isSelected = false
        img_filter_category_abstract.isSelected = false
        img_filter_category_all.isSelected = false

        // init type
        img_filter_type_painting.isSelected = false
        img_filter_type_oriental.isSelected = false
        img_filter_type_drawing.isSelected = false
        img_filter_type_crafting.isSelected = false
        img_filter_type_mixing.isSelected = false
        img_filter_type_digital.isSelected = false
        img_filter_type_all.isSelected = false

        if(filter_size == "S"){
            img_filter_size_s.isSelected = true
            img_filter_size_box.setImageResource(R.drawable.filter_size_s_select)
        }
        else if(filter_size == "M"){
            img_filter_size_m.isSelected = true
            img_filter_size_box.setImageResource(R.drawable.filter_size_m_select)
        }
        else if(filter_size == "L"){
            img_filter_size_l.isSelected = true
            img_filter_size_box.setImageResource(R.drawable.filter_size_l_select)
        }
        else if(filter_size == "XL"){
            img_filter_size_xl.isSelected = true
            img_filter_size_box.setImageResource(R.drawable.filter_size_xl_select)
        }
        else{
            img_filter_size_all.isSelected = true
            img_filter_size_box.setImageResource(R.drawable.filter_size_all_select)
        }

        if(filter_type == "조형/공예") img_filter_type_crafting.isSelected = true
        else if(filter_type == "드로잉") img_filter_type_drawing.isSelected = true
        else if(filter_type == "혼합 매체") img_filter_type_mixing.isSelected = true
        else if(filter_type == "사진") img_filter_type_digital.isSelected = true
        else if(filter_type == "동양화") img_filter_type_oriental.isSelected = true
        else if(filter_type == "페인팅") img_filter_type_painting.isSelected = true
        else img_filter_type_all.isSelected = true

        if(filter_category == "추상") img_filter_category_abstract.isSelected = true
        else if(filter_category == "식물") img_filter_category_plant.isSelected = true
        else if(filter_category == "사물") img_filter_category_object.isSelected = true
        else if(filter_category == "인물") img_filter_category_human.isSelected = true
        else if(filter_category == "동물") img_filter_category_animal.isSelected = true
        else if(filter_category == "풍경") img_filter_category_scenery.isSelected = true
        else img_filter_category_all.isSelected = true
    }

    fun setOnclickListener(){
        // SIZE
        img_filter_size_all.setOnClickListener {
            try {
                filter_size = null
                updateFilterView()
            } catch (e: Exception) {
            }
        }
        img_filter_size_s.setOnClickListener {
            try {
                filter_size = "S"
                updateFilterView()
            } catch (e: Exception) {
            }
        }
        img_filter_size_m.setOnClickListener {
            try {
                filter_size = "M"
                updateFilterView()
            } catch (e: Exception) {
            }
        }
        img_filter_size_l.setOnClickListener {
            try {
                filter_size = "L"
                updateFilterView()
            } catch (e: Exception) {
            }
        }
        img_filter_size_xl.setOnClickListener {
            try {
                filter_size = "XL"
                updateFilterView()
            } catch (e: Exception) {
            }
        }

        // TYPE
        img_filter_type_all.setOnClickListener {
            try {
                filter_type = null
                updateFilterView()
            } catch (e: Exception) {
            }
        }
        img_filter_type_crafting.setOnClickListener {
            try {
                filter_type = "조형/공예"
                updateFilterView()
            } catch (e: Exception) {
            }
        }
        img_filter_type_drawing.setOnClickListener {
            try {
                filter_type = "드로잉"
                updateFilterView()
            } catch (e: Exception) {
            }
        }
        img_filter_type_mixing.setOnClickListener {
            try {
                filter_type = "혼합 매체"
                updateFilterView()
            } catch (e: Exception) {
            }
        }
        img_filter_type_digital.setOnClickListener {
            try {
                filter_type = "사진"
                updateFilterView()
            } catch (e: Exception) {
            }
        }
        img_filter_type_oriental.setOnClickListener {
            try {
                filter_type = "동양화"
                updateFilterView()
            } catch (e: Exception) {
            }
        }
        img_filter_type_painting.setOnClickListener {
            try {
                filter_type = "페인팅"
                updateFilterView()
            } catch (e: Exception) {
            }
        }

        // CATEGORY
        img_filter_category_all.setOnClickListener {
            try {
                filter_category = null
                updateFilterView()
            } catch (e: Exception) {
            }
        }
        img_filter_category_abstract.setOnClickListener {
            try {
                filter_category = "추상"
                updateFilterView()
            } catch (e: Exception) {
            }
        }
        img_filter_category_plant.setOnClickListener {
            try {
                filter_category = "식물"
                updateFilterView()
            } catch (e: Exception) {
            }
        }
        img_filter_category_object.setOnClickListener {
            try {
                filter_category = "사물"
                updateFilterView()
            } catch (e: Exception) {
            }
        }
        img_filter_category_human.setOnClickListener {
            try {
                filter_category = "인물"
                updateFilterView()
            } catch (e: Exception) {
            }
        }
        img_filter_category_animal.setOnClickListener {
            try {
                filter_category = "동물"
                updateFilterView()
            } catch (e: Exception) {
            }
        }
        img_filter_category_scenery.setOnClickListener {
            try {
                filter_category = "풍경"
                updateFilterView()
            } catch (e: Exception) {
            }
        }

        txt_filter_apply.setOnClickListener {
            try {
                val intent: Intent = Intent()
                intent.putExtra("filter_size", filter_size)
                intent.putExtra("filter_type", filter_type)
                intent.putExtra("filter_category", filter_category)
                setResult(Activity.RESULT_OK, intent)

                finish()
            } catch (e: Exception) {
            }
        }
    }
}
