package com.artoo.sopt23.artoo_client_android.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artoo.sopt23.artoo_client_android.Activity.ProductUploadActivity
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_mypage_product.view.*

class MypageProductFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_mypage_product, container, false)
        val intent = Intent(context, ProductUploadActivity::class.java)

        /*//작품개수 0개 이상일 때,
        rl_mypage_product_nothing.visibility = View.GONE
        rl_mypage_product.visibility = View.VISIBLE*/

        view.btn_mypage_prodcut_nothing_add.setOnClickListener {
            startActivity(intent)
        }
        view.btn_mypage_product_add.setOnClickListener {
            startActivity(intent)
        }
        return view
    }

}
