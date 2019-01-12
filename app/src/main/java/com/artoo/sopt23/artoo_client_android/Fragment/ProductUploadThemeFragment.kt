package com.artoo.sopt23.artoo_client_android.Fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artoo.sopt23.artoo_client_android.Activity.ProductUploadActivity

import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_product_upload_theme.*
import kotlinx.android.synthetic.main.fragment_product_upload_theme.view.*
import org.jetbrains.anko.support.v4.toast

class ProductUploadThemeFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater!!.inflate(R.layout.fragment_product_upload_theme, container, false)
        val activity: ProductUploadActivity = activity as ProductUploadActivity

        for (i in 0 until activity.themes.size)
            Log.d(":::::product_upload_theme_frag:::::themes", activity.themes[i])

        //선택한 해시태그 색깔 바꿔서 띄우기 코드
        for (i in 0 until activity.themes.size){
            when(activity.themes.get(i)){
                "행복한"->{
                    view.btn_product_upload_theme_happy.setImageResource(R.drawable.upload_theme_coral_happy)
                }
                "심플한"->{
                    view.btn_product_upload_theme_simple.setImageResource(R.drawable.upload_theme_coral_simple)
                }
                "감성적인"->{
                    btn_product_upload_theme_sensitive.setImageResource(R.drawable.upload_theme_coral_sensitive)
                }
                "화려한"->{
                    view.btn_product_upload_theme_fancy.setImageResource(R.drawable.upload_theme_coral_fancy)
                }
                "심오한"->{
                    view.btn_product_upload_theme_profound.setImageResource(R.drawable.upload_theme_coral_unfathomable)
                }
                "아기자기한"->{
                    view.btn_product_upload_theme_cute.setImageResource(R.drawable.upload_theme_coral_cute)
                }
                "봄"->{
                    view.btn_product_upload_theme_spring.setImageResource(R.drawable.upload_theme_coral_spring)
                }
                "여름"->{
                    view.btn_product_upload_theme_summer.setImageResource(R.drawable.upload_theme_coral_summer)
                }
                "가을"->{
                    view.btn_product_upload_theme_fall.setImageResource(R.drawable.upload_theme_coral_fall)
                }
                "겨울"->{
                    view.btn_product_upload_theme_winter.setImageResource(R.drawable.upload_theme_coral_winter)
                }
            }
        }

        //만약 이미 선택한 태그에 해당하는 버튼은 다른색으로 background, 누를 수 없게 처리
        //이미 선택한 해시태그와 선택중인 해시태그를 구분할 필요가 있음
        //toast 넘 구린데

        view.btn_product_upload_theme_happy.setOnClickListener {

            try {
                if (checkOverSize(activity.themes) || checkSameValue("행복한", activity.themes)) {
                    //do nothing
                } else {
                    activity.themes.add("행복한")
                    view.btn_product_upload_theme_happy.setImageResource(R.drawable.upload_theme_coral_happy)
                }
            } catch (e: Exception) {
            }
        }
        view.btn_product_upload_theme_simple.setOnClickListener {

            try {
                if (checkOverSize(activity.themes) || checkSameValue("심플한", activity.themes)) {
                    //do nothing
                } else {
                    activity.themes.add("심플한")
                    view.btn_product_upload_theme_simple.setImageResource(R.drawable.upload_theme_coral_simple)
                }
            } catch (e: Exception) {
            }
        }
        view.btn_product_upload_theme_sensitive.setOnClickListener {

            try {
                if (checkOverSize(activity.themes) || checkSameValue("감성적인", activity.themes)) {
                    //do nothing
                } else {
                    activity.themes.add("감성적인")
                    btn_product_upload_theme_sensitive.setImageResource(R.drawable.upload_theme_coral_sensitive)
                }
            } catch (e: Exception) {
            }
        }
        view.btn_product_upload_theme_fancy.setOnClickListener {

            try {
                if (checkOverSize(activity.themes) || checkSameValue("화려한", activity.themes)) {
                    //do nothing
                } else {
                    activity.themes.add("화려한")
                    view.btn_product_upload_theme_fancy.setImageResource(R.drawable.upload_theme_coral_fancy)
                }
            } catch (e: Exception) {
            }
        }
        view.btn_product_upload_theme_profound.setOnClickListener {

            try {
                if (checkOverSize(activity.themes) || checkSameValue("심오한", activity.themes)) {
                    //do nothing
                } else {
                    activity.themes.add("심오한")
                    view.btn_product_upload_theme_profound.setImageResource(R.drawable.upload_theme_coral_unfathomable)
                }
            } catch (e: Exception) {
            }
        }
        view.btn_product_upload_theme_cute.setOnClickListener {

            try {
                if (checkOverSize(activity.themes) || checkSameValue("아기자기한", activity.themes)) {
                    //do nothing
                } else {
                    activity.themes.add("아기자기한")
                    view.btn_product_upload_theme_cute.setImageResource(R.drawable.upload_theme_coral_cute)
                }
            } catch (e: Exception) {
            }
        }

        view.btn_product_upload_theme_spring.setOnClickListener {

            try {
                if (checkOverSize(activity.themes) || checkSameValue("봄", activity.themes)) {
                    //do nothing
                } else {
                    activity.themes.add("봄")
                    view.btn_product_upload_theme_spring.setImageResource(R.drawable.upload_theme_coral_spring)
                }
            } catch (e: Exception) {
            }
        }
        view.btn_product_upload_theme_summer.setOnClickListener {

            try {
                if (checkOverSize(activity.themes) || checkSameValue("여름", activity.themes)) {
                    //do nothing
                } else {
                    activity.themes.add("여름")
                    view.btn_product_upload_theme_summer.setImageResource(R.drawable.upload_theme_coral_summer)
                }
            } catch (e: Exception) {
            }
        }
        view.btn_product_upload_theme_fall.setOnClickListener {

            try {
                if (checkOverSize(activity.themes) || checkSameValue("가을", activity.themes)) {
                    //do nothing
                } else {
                    activity.themes.add("가을")
                    view.btn_product_upload_theme_fall.setImageResource(R.drawable.upload_theme_coral_fall)
                }
            } catch (e: Exception) {
            }
        }
        view.btn_product_upload_theme_winter.setOnClickListener {

            try {
                if (checkOverSize(activity.themes) || checkSameValue("겨울", activity.themes)) {
                    //do nothing
                } else {
                    activity.themes.add("겨울")
                    view.btn_product_upload_theme_winter.setImageResource(R.drawable.upload_theme_coral_winter)
                }
            } catch (e: Exception) {
            }
        }

        view.btn_product_upload_theme_finish.setOnClickListener{
            try {
                activity.setTag()
                onDestroyView()
            } catch (e: Exception) {
            }
        }

        return view
    }

    private fun checkOverSize(tags: ArrayList<String>): Boolean {
        if (tags.size >= 3) {
            toast("최대로 선택할 수 있는 해시태그의 개수는 3개 입니다." + tags.size)
            return true
        }
        return false
    }

    private fun checkSameValue(tag: String, tags: ArrayList<String>): Boolean {
        for (i in 0 until tags.size) {
            if (tags.get(i).equals(tag)) {

                toast("이미 선택한 해시태그입니다." + tags.get(i))
                return true
            }
        }
        return false
    }
}