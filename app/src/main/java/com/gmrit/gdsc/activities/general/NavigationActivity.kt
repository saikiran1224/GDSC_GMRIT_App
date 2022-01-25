package com.gmrit.gdsc.activities.general

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.gmrit.gdsc.R
import com.gmrit.gdsc.utils.OnSwipeTouchListener

class NavigationActivity : AppCompatActivity() {

    lateinit var txtViewProfile: TextView
    lateinit var txtOurTeam: TextView

    lateinit var closeIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(R.color.light_light_blue)

        setContentView(R.layout.activity_navigation)

        txtViewProfile = findViewById(R.id.txtViewProfile)
        txtOurTeam = findViewById(R.id.txtOurTeam)
        closeIcon = findViewById(R.id.closeIcon)

        closeIcon.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.left_to_right,R.anim.right_to_left)

            finish()

        }

        txtViewProfile.setOnClickListener {

            val intent = Intent(this, StudentProfileActivity::class.java)
            startActivity(intent)

        }

        txtOurTeam.setOnClickListener {
            val intent = Intent(this, OurTeamActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_right,R.anim.right_to_left)
        finish()

    }
}