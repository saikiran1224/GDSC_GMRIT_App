package com.gmrit.gdsc.activities.general

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.gmrit.gdsc.R

class JoinGDSCActivity : AppCompatActivity() {

    lateinit var btnJoinOurCommunity: CardView

    lateinit var btnBackIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_gdscactivity)

        btnJoinOurCommunity = findViewById(R.id.btnJoinOurClub)
        btnBackIcon = findViewById(R.id.btnBackIcon)

        btnBackIcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnJoinOurCommunity.setOnClickListener {

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://gdsc.community.dev/gmr-institute-of-technology/"))
            startActivity(browserIntent)

        }
    }
}