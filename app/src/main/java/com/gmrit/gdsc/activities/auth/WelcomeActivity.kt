package com.gmrit.gdsc.activities.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.gmrit.gdsc.R
import com.gmrit.gdsc.activities.general.MainActivity

class WelcomeActivity : AppCompatActivity() {

    lateinit var btnNext: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setting color of Status Bar
        window.statusBarColor = getColor(R.color.blue)

        setContentView(R.layout.activity_welcome)

        btnNext = findViewById(R.id.btnNext)

        btnNext.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }
}