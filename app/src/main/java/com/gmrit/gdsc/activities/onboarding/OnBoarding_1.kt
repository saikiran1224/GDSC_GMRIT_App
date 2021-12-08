package com.gmrit.gdsc.activities.onboarding

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.gmrit.gdsc.R
import com.gmrit.gdsc.utils.OnSwipeTouchListener

class OnBoarding_1 : AppCompatActivity() {

    lateinit var txtFirstExperience : TextView
    lateinit var relativeOnBoarding_1: RelativeLayout

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setting color of Status Bar
        window.statusBarColor = getColor(R.color.lightBlue)

        setContentView(R.layout.activity_on_boarding1)

        txtFirstExperience = findViewById(R.id.txtFindNewExp)
        relativeOnBoarding_1 = findViewById(R.id.onBoardingRelative1)

        // setting Span for Find New Experience Text
        val spannable = SpannableStringBuilder("Find New Experience")
        spannable.setSpan(ForegroundColorSpan(Color.WHITE), 5, 9, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        txtFirstExperience.text = spannable


        // implementing onTouchListener to detect onSwipeRightEvent
        with(relativeOnBoarding_1) {

            // implementing onTouchListener to detect onSwipeRightEvent
            setOnTouchListener(object : OnSwipeTouchListener() {

                @SuppressLint("ClickableViewAccessibility")
                override fun onSwipeLeft() {
                    super.onSwipeLeft()
                    val intent = Intent(this@OnBoarding_1, OnBoarding_2::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.left_in, R.anim.left_out)

                }
            })
        }


    }
}