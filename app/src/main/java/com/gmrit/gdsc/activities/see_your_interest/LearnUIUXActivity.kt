package com.gmrit.gdsc.activities.see_your_interest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.gmrit.gdsc.R

class LearnUIUXActivity : AppCompatActivity() {

    lateinit var imageProfilePhoto: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide the status bar.
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.statusBarColor = getColor(R.color.uiux_dev)
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        //actionBar?.hide()
        setContentView(R.layout.activity_learn_uiuxactivity)

        imageProfilePhoto = findViewById(R.id.imageProfilePhoto)

        Glide.with(this).load(getString(R.string.yeswanth_profile_pic)).into(imageProfilePhoto)



    }
}