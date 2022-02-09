package com.gmrit.gdsc.activities.see_your_interest

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.gmrit.gdsc.R
import com.gmrit.gdsc.activities.general.MainActivity

class LearnAndroidDevActivity : AppCompatActivity() {

    lateinit var imageProfilePhoto: ImageView

    lateinit var backButtonIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide the status bar.
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.statusBarColor = getColor(R.color.android_dev)
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        //actionBar?.hide()
        setContentView(R.layout.activity_learn_android_dev)

        imageProfilePhoto = findViewById(R.id.imageProfilePhoto)
        backButtonIcon = findViewById(R.id.backButtonIcon)

        Glide.with(this).load(getString(R.string.saikiran_profile_pic)).into(imageProfilePhoto)

        backButtonIcon.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }


    }
}