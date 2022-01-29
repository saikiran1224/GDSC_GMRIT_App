package com.gmrit.gdsc.activities.see_your_interest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.gmrit.gdsc.R

class LearnWebDevActivity : AppCompatActivity() {

    lateinit var imageProfilePhoto: ImageView

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide the status bar.
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.statusBarColor = getColor(R.color.pink_web)
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        //actionBar?.hide()
        setContentView(R.layout.activity_learn_web_dev)

        imageProfilePhoto = findViewById(R.id.imageProfilePhoto)

        Glide.with(this).load(getString(R.string.vinaysriram_pic)).into(imageProfilePhoto)


    }
}