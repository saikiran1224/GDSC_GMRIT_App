package com.gmrit.gdsc.activities.onboarding

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.gmrit.gdsc.BuildConfig
import com.gmrit.gdsc.R
import com.gmrit.gdsc.activities.general.MainActivity
import com.gmrit.gdsc.models.AppUtilsData
import com.gmrit.gdsc.models.EventDetailsData
import com.gmrit.gdsc.utils.AppPreferences
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_TIMER = 3000;

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide the status bar.
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.statusBarColor = android.R.color.white
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()
        setContentView(R.layout.activity_splash_screen)

        AppPreferences.init(this)

        Handler().postDelayed({

            if(AppPreferences.isLogin == true) {
                // User is already signed in, so navigate him directly to the Main Activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, OnBoardingActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, SPLASH_TIMER.toLong())
    }

}