package com.gmrit.gdsc.activities.general

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.gmrit.gdsc.R
import org.w3c.dom.Text

class ShareYourIdeaActivity : AppCompatActivity() {

    lateinit var txtHaveAnIdea: TextView
    lateinit var txtShareYourIdea: TextView

    lateinit var btnSubmit: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(R.color.ide_blue)

        setContentView(R.layout.activity_share_your_idea)


        txtHaveAnIdea = findViewById(R.id.txtHaveAnIdea)
        txtShareYourIdea = findViewById(R.id.txtShareYourIdea)

        btnSubmit = findViewById(R.id.btnSubmit)

        // setting Spannable Text for the above Text Views

        val spannable_1 = SpannableStringBuilder("Have an idea! share with us")
        spannable_1.setSpan(ForegroundColorSpan(getColor(R.color.idea_orange)), 8, 12, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        txtHaveAnIdea!!.text = spannable_1

        val spannable_2 = SpannableStringBuilder("Share your idea with us and lets help together to build the product")
        spannable_2.setSpan(ForegroundColorSpan(getColor(R.color.idea_orange)), 50, spannable_2.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        txtShareYourIdea!!.text = spannable_2




    }
}