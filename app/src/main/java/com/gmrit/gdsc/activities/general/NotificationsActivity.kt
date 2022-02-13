package com.gmrit.gdsc.activities.general

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.gmrit.gdsc.R

class NotificationsActivity : AppCompatActivity() {


    lateinit var backButtonIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = getColor(com.gmrit.gdsc.R.color.purple)

        setContentView(R.layout.activity_notifications)

        backButtonIcon = findViewById(R.id.backButtonIcon)

        backButtonIcon.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }



    }
}