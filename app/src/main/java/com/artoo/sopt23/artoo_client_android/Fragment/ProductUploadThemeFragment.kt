package com.artoo.sopt23.artoo_client_android.Fragment

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artoo.sopt23.artoo_client_android.Activity.ProductUploadActivity

import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_product_upload_theme.view.*
import org.jetbrains.anko.support.v4.toast

class ProductUploadThemeFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater!!.inflate(R.layout.fragment_product_upload_theme, container, false)

        val activity: ProductUploadActivity = activity as ProductUploadActivity

        for (i in 0 until activity.themes.size)
            Log.d(":::::product_upload_theme_frag:::::themes", activity.themes[i])

        //선택한 해시태그 색깔 바꿔서 띄우기 코드
//        for (i in 0 until activity.themes.size){
//            when(activity.themes.get(i)){
//                "앤틱한"->{
//
//                }
//                "심플한"->{
//
//                }
//            }
//        }

        //만약 이미 선택한 태그에 해당하는 버튼은 다른색으로 background, 누를 수 없게 처리
        //이미 선택한 해시태그와 선택중인 해시태그를 구분할 필요가 있음
        //toast 넘 구린데

       view.btn_product_upload_theme_antique.setOnClickListener {

           if (checkOverSize(activity.themes) || checkSameValue("엔틱한", activity.themes)) {
               //do nothing
           } else {
               activity.themes.add("엔틱한")
           }
        }
        view.btn_product_upload_theme_simple.setOnClickListener {

            if (checkOverSize(activity.themes) || checkSameValue("심플한", activity.themes)) {
                //do nothing
            } else {
                activity.themes.add("심플한")
            }
        }
        view.btn_product_upload_theme_cozy.setOnClickListener {

            if (checkOverSize(activity.themes) || checkSameValue("따뜻한", activity.themes)) {
                //do nothing
            } else {
                activity.themes.add("따뜻한")
            }
        }
        view.btn_product_upload_theme_fancy.setOnClickListener {

            if (checkOverSize(activity.themes) || checkSameValue("화려한", activity.themes)) {
                //do nothing
            } else {
                activity.themes.add("화려한")
            }
        }
        view.btn_product_upload_theme_profound.setOnClickListener {

            if (checkOverSize(activity.themes) || checkSameValue("심오한", activity.themes)) {
                //do nothing
            } else {
                activity.themes.add("심오한")
            }
        }
        view.btn_product_upload_theme_cute.setOnClickListener {

            if (checkOverSize(activity.themes) || checkSameValue("아기자기한", activity.themes)) {
                //do nothing
            } else {
                activity.themes.add("아기자기한")
            }
        }

        view.btn_product_upload_theme_spring.setOnClickListener {

            if (checkOverSize(activity.themes) || checkSameValue("봄", activity.themes)) {
                //do nothing
            } else {
                activity.themes.add("봄")
            }
        }
        view.btn_product_upload_theme_summer.setOnClickListener {

            if (checkOverSize(activity.themes) || checkSameValue("여름", activity.themes)) {
                //do nothing
            } else {
                activity.themes.add("여름")
            }
        }
        view.btn_product_upload_theme_fall.setOnClickListener {

            if (checkOverSize(activity.themes) || checkSameValue("가을", activity.themes)) {
                //do nothing
            } else {
                activity.themes.add("가을")
            }
        }
        view.btn_product_upload_theme_winter.setOnClickListener {

            if (checkOverSize(activity.themes) || checkSameValue("겨울", activity.themes)) {
                //do nothing
            } else {
                activity.themes.add("겨울")
            }
       }

        view.btn_product_upload_theme_finish.setOnClickListener{
            activity.setTag()
            onDestroyView()
        }

        return view
    }

    private fun checkOverSize(tags: ArrayList<String>): Boolean {
        if (tags.size >= 3) {
            toast("Maximum of Tags: 3, size=" + tags.size)
            return true
        }
        return false
    }

    private fun checkSameValue(tag: String, tags: ArrayList<String>): Boolean {
        for (i in 0 until tags.size) {
            if (tags.get(i).equals(tag)) {
                toast("Already Selected" + tags.get(i))
                return true
            }
        }
        return false
    }
}