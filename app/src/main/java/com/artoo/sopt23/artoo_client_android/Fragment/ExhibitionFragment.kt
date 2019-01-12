package com.artoo.sopt23.artoo_client_android.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Adapter.MainExhibitionAdapter
import com.artoo.sopt23.artoo_client_android.Activity.ApplyExhibitionActivity

import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_exhibition.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetExhibitionDisplayResponse
import com.artoo.sopt23.artoo_client_android.Data.MainExhibitionData
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService
import kotlinx.android.synthetic.main.fragment_exhibition.view.*

class ExhibitionFragment : Fragment() {

    lateinit var mainExhibitionAdapter: MainExhibitionAdapter
    lateinit var btn_apply_ex : RelativeLayout
    lateinit var exhibition_403 : LinearLayout

    val dataList:ArrayList<MainExhibitionData> by lazy{
        ArrayList<MainExhibitionData>()
    }
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_exhibition, container, false)

        getExhibitionMain(view)

        init(view)

        btn_apply_ex.setOnClickListener { v: View? ->
            try {
                var intent = Intent(activity?.applicationContext, ApplyExhibitionActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    fun getExhibitionMain (view: View) {

        var getExhibition : ArrayList<MainExhibitionData>

        val getExhibitionDisplayResponse = networkService.getExhibitionDisplayResponse()
        getExhibitionDisplayResponse.enqueue(object : Callback<GetExhibitionDisplayResponse> {
            override fun onFailure(call: Call<GetExhibitionDisplayResponse>?, t: Throwable?) {
                Log.d("getExhibition","main Exhibition fail")
            }

            override fun onResponse(call: Call<GetExhibitionDisplayResponse>?, response: Response<GetExhibitionDisplayResponse>?) {
                if(response!!.isSuccessful) {

                    Log.d("status_nm", response.body()!!.status.toString())

                    when(response.body()!!.status) {
                        200 -> {
                            getExhibition = response.body()!!.data
                            Log.d("fragment1", response.body()!!.data.size.toString())

                            mainExhibitionAdapter = MainExhibitionAdapter(getExhibition)
                            view.rv_main_ex.adapter = mainExhibitionAdapter
                            view.rv_main_ex.layoutManager = LinearLayoutManager(activity)
                        }

                        204 -> {
                            exhibition_403.visibility = View.VISIBLE
                            rv_main_ex.visibility = View.GONE
                        }
                    }



                }
            }

        })
    }


    fun init(view: View) {
        btn_apply_ex = view.findViewById(R.id.btn_apply_ex)
        exhibition_403 = view.findViewById(R.id.exhibition_403)
    }
}

