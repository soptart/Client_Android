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
    lateinit var btn_apply_ex : TextView

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
            var intent = Intent(activity?.applicationContext, ApplyExhibitionActivity::class.java)
            startActivity(intent)
        }

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        RecyclerView()
    }

    fun getExhibitionMain (view: View) {

        var getExhibition = ArrayList<MainExhibitionData>()

        val getExhibitionDisplayResponse = networkService.getExhibitionDisplayResponse()
        getExhibitionDisplayResponse.enqueue(object : Callback<GetExhibitionDisplayResponse> {
            override fun onFailure(call: Call<GetExhibitionDisplayResponse>?, t: Throwable?) {
                Log.d("getExhibition","main Exhibition fail")
            }

            override fun onResponse(call: Call<GetExhibitionDisplayResponse>?, response: Response<GetExhibitionDisplayResponse>?) {
                if(response!!.isSuccessful) {
                    getExhibition = response.body()!!.data

                    mainExhibitionAdapter = MainExhibitionAdapter(getExhibition)
                    view.rv_main_ex.adapter = mainExhibitionAdapter
                    view.rv_main_ex.layoutManager = LinearLayoutManager(activity)


                }
            }

        })
    }

    private fun RecyclerView () {

        var ex_img_url: ArrayList<String> = ArrayList()

//        dataList.add(MainExhibitionData("http://img.newspim.com/news/2018/03/31/1803311718414850.jpg"))
//        dataList.add(MainExhibitionData("http://www.newsa.co.kr/news/photo/201809/187929_146645_3554.jpg"))
//        dataList.add(MainExhibitionData("https://www.nemopan.com/files/attach/images/2582/269/848/493724be0c020.jpg"))

//        ex_img_url.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQOYHP7M32I82Zb9vZ30wwcO8p1RQ_Ge0MJ1c6GrN5L_-go5dfDdg")
//        ex_img_url.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5pWedPM7wG9iSUQaQVNSuhykxwugoiSLobMClqboRk0UxGGnO")
//        ex_img_url.add("https://www.nemopan.com/files/attach/images/2582/269/848/493724be0c020.jpg")

//        dataList.add(MainExhibitionData(ex_img_url))


//        var linearLayoutManager = LinearLayoutManager(activity)
//        linearLayoutManager.isAutoMeasureEnabled = true
//        rv_main_ex.layoutManager = linearLayoutManager
//        rv_main_ex.isNestedScrollingEnabled = false
    }


    fun init(view: View) {
        btn_apply_ex = view.findViewById(R.id.btn_apply_ex)
    }
}

