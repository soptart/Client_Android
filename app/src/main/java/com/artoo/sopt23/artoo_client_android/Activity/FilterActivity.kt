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

        // init type
        img_filter_type_painting.isSelected = false
        img_filter_type_oriental.isSelected = false
        img_filter_type_drawing.isSelected = false
        img_filter_type_crafting.isSelected = false
        img_filter_type_mixing.isSelected = false
        img_filter_type_digital.isSelected = false

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
        else if(filter_type == "디지털") img_filter_type_digital.isSelected = true
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
            filter_size = null
            updateFilterView()
        }
        img_filter_size_s.setOnClickListener {
            filter_size = "S"
            updateFilterView()
        }
        img_filter_size_m.setOnClickListener {
            filter_size = "M"
            updateFilterView()
        }
        img_filter_size_l.setOnClickListener {
            filter_size = "L"
            updateFilterView()
        }
        img_filter_size_xl.setOnClickListener {
            filter_size = "XL"
            updateFilterView()
        }

        // TYPE
        img_filter_type_all.setOnClickListener {
            filter_type = null
            updateFilterView()
        }
        img_filter_type_crafting.setOnClickListener {
            filter_type = "조형/공예"
            updateFilterView()
        }
        img_filter_type_drawing.setOnClickListener {
            filter_type = "드로잉"
            updateFilterView()
        }
        img_filter_type_mixing.setOnClickListener {
            filter_type = "혼합 매체"
            updateFilterView()
        }
        img_filter_type_digital.setOnClickListener {
            filter_type = "디지털"
            updateFilterView()
        }
        img_filter_type_oriental.setOnClickListener {
            filter_type = "동양화"
            updateFilterView()
        }
        img_filter_type_painting.setOnClickListener {
            filter_type = "페인팅"
            updateFilterView()
        }

        // CATEGORY
        img_filter_category_all.setOnClickListener {
            filter_category = null
            updateFilterView()
        }
        img_filter_category_abstract.setOnClickListener {
            filter_category = "추상"
            updateFilterView()
        }
        img_filter_category_plant.setOnClickListener {
            filter_category = "식물"
            updateFilterView()
        }
        img_filter_category_object.setOnClickListener {
            filter_category = "사물"
            updateFilterView()
        }
        img_filter_category_human.setOnClickListener {
            filter_category = "인물"
            updateFilterView()
        }
        img_filter_category_animal.setOnClickListener {
            filter_category = "동물"
            updateFilterView()
        }
        img_filter_category_scenery.setOnClickListener {
            filter_category = "풍경"
            updateFilterView()
        }

        txt_filter_apply.setOnClickListener {
            val intent: Intent = Intent()
            intent.putExtra("filter_size", filter_size)
            intent.putExtra("filter_type", filter_type)
            intent.putExtra("filter_category", filter_category)
            setResult(Activity.RESULT_OK, intent)

            finish()
        }
    }
}
