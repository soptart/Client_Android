package com.artoo.sopt23.artoo_client_android.Activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.activity_mypage_help.*

class MypageHelpActivity : AppCompatActivity() {
    lateinit var help_list_btn1 : LinearLayout
    lateinit var help_list_btn2 : LinearLayout
    lateinit var help_list_btn3 : LinearLayout
    lateinit var help_list_btn4 : LinearLayout
    lateinit var help_list_btn5 : LinearLayout
    lateinit var help_list_btn6 : LinearLayout
    lateinit var help_list_btn7 : LinearLayout
    lateinit var help_list_btn8 : LinearLayout
    lateinit var help_list_btn9 : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_help)

        val onClick = View.OnClickListener { v: View? ->
            try {
                when(v!!.id) {

                    R.id.help_list_btn1 -> {
                        if(expandable_a1.visibility == View.GONE) {
                            help_list_arrow1.setBackgroundResource(R.drawable.arrow_up_black)
                            help_list_q1.setTextColor(Color.parseColor("#ff6f61"))
                            expandable_a1.visibility = View.VISIBLE
                        } else {
                            help_list_arrow1.setBackgroundResource(R.drawable.arrow_down_black)
                            help_list_q1.setTextColor(Color.parseColor("#434343"))
                            expandable_a1.visibility = View.GONE
                        }

                    }
                    R.id.help_list_btn2 -> {
                        if(expandable_a2.visibility == View.GONE) {
                            help_list_arrow2.setBackgroundResource(R.drawable.arrow_up_black)
                            help_list_q2.setTextColor(Color.parseColor("#ff6f61"))
                            expandable_a2.visibility = View.VISIBLE
                        } else {
                            help_list_arrow2.setBackgroundResource(R.drawable.arrow_down_black)
                            help_list_q2.setTextColor(Color.parseColor("#434343"))
                            expandable_a2.visibility = View.GONE
                        }

                    }
                    R.id.help_list_btn3 -> {
                        if(expandable_a3.visibility == View.GONE) {
                            help_list_arrow3.setBackgroundResource(R.drawable.arrow_up_black)
                            help_list_q3.setTextColor(Color.parseColor("#ff6f61"))
                            expandable_a3.visibility = View.VISIBLE
                        } else {
                            help_list_arrow3.setBackgroundResource(R.drawable.arrow_down_black)
                            help_list_q3.setTextColor(Color.parseColor("#434343"))
                            expandable_a3.visibility = View.GONE
                        }

                    }
                    R.id.help_list_btn4 -> {
                        if(expandable_a4.visibility == View.GONE) {
                            help_list_arrow4.setBackgroundResource(R.drawable.arrow_up_black)
                            help_list_q4.setTextColor(Color.parseColor("#ff6f61"))
                            expandable_a4.visibility = View.VISIBLE
                        } else {
                            help_list_arrow4.setBackgroundResource(R.drawable.arrow_down_black)
                            help_list_q4.setTextColor(Color.parseColor("#434343"))
                            expandable_a4.visibility = View.GONE
                        }

                    }
                    R.id.help_list_btn5 -> {
                        if(expandable_a5.visibility == View.GONE) {
                            help_list_arrow5.setBackgroundResource(R.drawable.arrow_up_black)
                            help_list_q5.setTextColor(Color.parseColor("#ff6f61"))
                            expandable_a5.visibility = View.VISIBLE
                        } else {
                            help_list_arrow5.setBackgroundResource(R.drawable.arrow_down_black)
                            help_list_q5.setTextColor(Color.parseColor("#434343"))
                            expandable_a5.visibility = View.GONE
                        }

                    }
                    R.id.help_list_btn6 -> {
                        if(expandable_a6.visibility == View.GONE) {
                            help_list_arrow6.setBackgroundResource(R.drawable.arrow_up_black)
                            help_list_q6.setTextColor(Color.parseColor("#ff6f61"))
                            expandable_a6.visibility = View.VISIBLE
                        } else {
                            help_list_arrow6.setBackgroundResource(R.drawable.arrow_down_black)
                            help_list_q6.setTextColor(Color.parseColor("#434343"))
                            expandable_a6.visibility = View.GONE
                        }

                    }
                    R.id.help_list_btn7 -> {
                        if(expandable_a7.visibility == View.GONE) {
                            help_list_arrow7.setBackgroundResource(R.drawable.arrow_up_black)
                            help_list_q7.setTextColor(Color.parseColor("#ff6f61"))
                            expandable_a7.visibility = View.VISIBLE
                        } else {
                            help_list_arrow7.setBackgroundResource(R.drawable.arrow_down_black)
                            help_list_q7.setTextColor(Color.parseColor("#434343"))
                            expandable_a7.visibility = View.GONE
                        }

                    }
                    R.id.help_list_btn8 -> {
                        if(expandable_a8.visibility == View.GONE) {
                            help_list_arrow8.setBackgroundResource(R.drawable.arrow_up_black)
                            help_list_q8.setTextColor(Color.parseColor("#ff6f61"))
                            expandable_a8.visibility = View.VISIBLE
                        } else {
                            help_list_arrow8.setBackgroundResource(R.drawable.arrow_down_black)
                            help_list_q8.setTextColor(Color.parseColor("#434343"))
                            expandable_a8.visibility = View.GONE
                        }

                    }
                    R.id.help_list_btn9 -> {
                        if(expandable_a9.visibility == View.GONE) {
                            help_list_arrow9.setBackgroundResource(R.drawable.arrow_up_black)
                            help_list_q9.setTextColor(Color.parseColor("#ff6f61"))
                            expandable_a9.visibility = View.VISIBLE
                        } else {
                            help_list_arrow9.setBackgroundResource(R.drawable.arrow_down_black)
                            help_list_q9.setTextColor(Color.parseColor("#434343"))
                            expandable_a9.visibility = View.GONE
                        }

                    }
                }
            } catch (e: Exception) {
            }
        }

        help_list_btn1 = findViewById(R.id.help_list_btn1)
        help_list_btn1.setOnClickListener(onClick)

        help_list_btn2 = findViewById(R.id.help_list_btn2)
        help_list_btn2.setOnClickListener(onClick)

        help_list_btn3 = findViewById(R.id.help_list_btn3)
        help_list_btn3.setOnClickListener(onClick)

        help_list_btn4 = findViewById(R.id.help_list_btn4)
        help_list_btn4.setOnClickListener(onClick)

        help_list_btn5 = findViewById(R.id.help_list_btn5)
        help_list_btn5.setOnClickListener(onClick)

        help_list_btn6 = findViewById(R.id.help_list_btn6)
        help_list_btn6.setOnClickListener(onClick)

        help_list_btn7 = findViewById(R.id.help_list_btn7)
        help_list_btn7.setOnClickListener(onClick)

        help_list_btn8 = findViewById(R.id.help_list_btn8)
        help_list_btn8.setOnClickListener(onClick)

        help_list_btn9 = findViewById(R.id.help_list_btn9)
        help_list_btn9.setOnClickListener(onClick)
    }
}
