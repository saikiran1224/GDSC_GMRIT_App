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
import com.gmrit.gdsc.R
import com.gmrit.gdsc.utils.OnSwipeTouchListener

class OnBoarding_2 : AppCompatActivity() {

    lateinit var txtImproveYourSkills : TextView
    lateinit var relativeImprove: RelativeLayout

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setting color of Status Bar
        window.statusBarColor = getColor(R.color.lightRed)


        setContentView(R.layout.activity_on_boarding2)

        txtImproveYourSkills = findViewById(R.id.txtImproveYourSkills)
        relativeImprove = findViewById(R.id.relativeImprove)

        // setting Span for Find New Experience Text
        val spannable = SpannableStringBuilder("Improve your Skills")
        spannable.setSpan(
            ForegroundColorSpan(Color.WHITE),
            13, // start
            19, // end
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        txtImproveYourSkills.text = spannable

        with(relativeImprove) {
            // implementing onTouchListener to detect onSwipeRightEvent
            setOnTouchListener(object : OnSwipeTouchListener() {

                @SuppressLint("ClickableViewAccessibility")
                override fun onSwipeLeft() {
                    super.onSwipeLeft()
                    val intent = Intent(this@OnBoarding_2, OnBoarding_3::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.left_in, R.anim.left_out)
                }

                override fun onSwipeRight() {
                    super.onSwipeRight()
                    val intent = Intent(this@OnBoarding_2, OnBoarding_1::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.right_in, R.anim.right_out)

                }

            })
        }

    }
}