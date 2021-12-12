package com.gmrit.gdsc.activities.general

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gmrit.gdsc.R

class LearnAndroidDevActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide the status bar.
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.statusBarColor = getColor(R.color.android_dev)
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        //actionBar?.hide()
        setContentView(R.layout.activity_learn_android_dev)
    }
}