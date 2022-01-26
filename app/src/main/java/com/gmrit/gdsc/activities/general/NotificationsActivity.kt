package com.gmrit.gdsc.activities.general

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gmrit.gdsc.R

class NotificationsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = getColor(com.gmrit.gdsc.R.color.purple)

        setContentView(R.layout.activity_notifications)



    }
}