package com.gmrit.gdsc.activities.general

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.gmrit.gdsc.R
import com.gmrit.gdsc.activities.onboarding.OnBoardingActivity
import com.gmrit.gdsc.utils.AppPreferences

class NavigationActivity : AppCompatActivity() {

    lateinit var txtViewProfile: TextView

    lateinit var txtOurTeam: TextView
    lateinit var txtExplore: TextView
    lateinit var txtIdeaSpot: TextView
    lateinit var txtAbout: TextView
    lateinit var txtFeedback: TextView

    lateinit var closeIcon: ImageView

    lateinit var btnLogout: CardView

    lateinit var studentName: String
    lateinit var txtStudentName: TextView
    lateinit var txtProfileShortName: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(R.color.light_light_blue)

        setContentView(R.layout.activity_navigation)

        AppPreferences.init(this)

        txtStudentName = findViewById(R.id.txtStudentName)
        txtProfileShortName = findViewById(R.id.txtProfileShortName)

        txtViewProfile = findViewById(R.id.txtViewProfile)

        txtOurTeam = findViewById(R.id.txtOurTeam)
        txtExplore = findViewById(R.id.txtExplore)
        txtIdeaSpot = findViewById(R.id.txtIdeaSpot)
        txtAbout = findViewById(R.id.txtAbout)
        txtFeedback = findViewById(R.id.txtFeedback)

        btnLogout = findViewById(R.id.btnLogout)


        closeIcon = findViewById(R.id.closeIcon)

        closeIcon.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            overridePendingTransition(R.anim.left_to_right,R.anim.right_to_left)
            

        }

        // Setting Names based on AppPreferences
        studentName = AppPreferences.studentName.toString()
        txtStudentName.text = studentName

        // Setting Short Name using the help of Indexes
        val count = studentName.split(" ").toTypedArray()
        if(count.size == 1) {
            txtProfileShortName.text = studentName[0].toString()
        } else {
            val index = studentName.lastIndexOf(' ')
            val firstName = index.let { it1 -> studentName.substring(0, it1) }
            val lastName = index.plus(1).let { it1 ->
                studentName.substring(it1)
            }
            txtProfileShortName.text = firstName.toString()[0] + lastName[0].toString()
        }

        txtViewProfile.setOnClickListener {

            val intent = Intent(this, StudentProfileActivity::class.java)
            startActivity(intent)

        }

        txtExplore.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }

        txtIdeaSpot.setOnClickListener {
            val intent = Intent(this, ShareYourIdeaActivity::class.java)
            startActivity(intent)
        }

        txtOurTeam.setOnClickListener {
            val intent = Intent(this, OurTeamActivity::class.java)
            startActivity(intent)
        }

        txtAbout.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://developers.google.com/community/gdsc"))
            startActivity(intent)
        }

        txtFeedback.setOnClickListener {
            val intent = Intent(this, FeedbackActivity::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            showLogoutDialog()
        }

    }

    private fun showLogoutDialog() {

        val logoutDialog = Dialog(this)
        logoutDialog.setContentView(R.layout.logout_dialog)
        logoutDialog.setCancelable(false)
        logoutDialog.setCanceledOnTouchOutside(false)
        logoutDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        logoutDialog.findViewById<CardView>(R.id.btnLogout).setOnClickListener {

            AppPreferences.isLogin = false
            AppPreferences.studentName = ""
            AppPreferences.studentEmail = ""
            AppPreferences.studentJNTUNo = ""

            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        logoutDialog.findViewById<CardView>(R.id.btnCancel).setOnClickListener {
            logoutDialog.dismiss()
        }

        logoutDialog.show()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_right,R.anim.right_to_left)
        finish()

    }
}