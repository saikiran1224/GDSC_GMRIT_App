package com.gmrit.gdsc.activities.general

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.gmrit.gdsc.R

class NavigationActivity : AppCompatActivity() {

    lateinit var txtViewProfile: TextView
    lateinit var txtOurTeam: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(R.color.light_light_blue)

        setContentView(R.layout.activity_navigation)

        txtViewProfile = findViewById(R.id.txtViewProfile)
        txtOurTeam = findViewById(R.id.txtOurTeam)


        txtViewProfile.setOnClickListener {
            val intent = Intent(this, StudentProfileActivity::class.java)
            startActivity(intent)
        }

        txtOurTeam.setOnClickListener {
            val intent = Intent(this, OurTeamActivity::class.java)
            startActivity(intent)
        }

    }
}