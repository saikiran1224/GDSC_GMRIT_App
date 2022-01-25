package com.gmrit.gdsc.activities.general

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.gmrit.gdsc.R
import org.w3c.dom.Text

class EventProfileActivity : AppCompatActivity() {

    lateinit var eventName: String
    lateinit var eventTagLine: String
    lateinit var eventDate: String
    lateinit var aboutTheEvent: String
    lateinit var eventPosterUrl: String
    lateinit var eventInstructorName: String
    lateinit var eventInstructorPhotoUrl: String
    lateinit var eventPreRequisites: String
    lateinit var thingsYouWillLearn: String
    lateinit var eventRegisterUrl: String

    // Views
    lateinit var coverImageView: ImageView
    lateinit var txtEventName: TextView
    lateinit var txtEventTagLine: TextView
    lateinit var txtInstructorName: TextView
    lateinit var instructorImageView: ImageView
    lateinit var txtAboutTheEvent: TextView
    lateinit var txtThingsYouWillLearn: TextView
    lateinit var txtEventPrerequisites:TextView

    lateinit var btnEnrollNow: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide the status bar.
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.statusBarColor = getColor(R.color.blue_button_clr)
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()

        setContentView(R.layout.activity_event_profile)

        coverImageView = findViewById(R.id.coverImage)
        txtEventName = findViewById(R.id.txtEventName)
        txtEventTagLine = findViewById(R.id.txtEventTagLine)
        txtAboutTheEvent = findViewById(R.id.txtDescAbout)
        txtEventPrerequisites = findViewById(R.id.txtDescPrerequisites)
        txtThingsYouWillLearn = findViewById(R.id.txtDescThings)
        txtInstructorName = findViewById(R.id.txtInstructorName)
        instructorImageView = findViewById(R.id.instructorImage)
        btnEnrollNow = findViewById(R.id.btnEnrollNow)


        // Retreving the paramters that are received from Intent
        getEventDetailsFromIntent()

        Glide.with(this).load(eventPosterUrl).into(coverImageView)
        txtEventName.text = eventName
        txtEventTagLine.text = eventTagLine
        txtInstructorName.text = eventInstructorName
        Glide.with(this).load(eventInstructorPhotoUrl).into(instructorImageView)
        txtAboutTheEvent.text = aboutTheEvent
        txtThingsYouWillLearn.text = thingsYouWillLearn
        txtEventPrerequisites.text = eventPreRequisites

        btnEnrollNow.setOnClickListener {
            // Pass Intent to Browser

        }


    }

    private fun getEventDetailsFromIntent() {
        val intent = intent
        eventDate = intent.getStringExtra("eventDate")!!
        eventName = intent.getStringExtra("eventName")!!
        eventTagLine = intent.getStringExtra("eventTagLine")!!
        eventPosterUrl = intent.getStringExtra("eventPosterUrl")!!
        aboutTheEvent = intent.getStringExtra("aboutTheEvent")!!
        eventRegisterUrl = intent.getStringExtra("eventRegisterUrl")!!
        eventPreRequisites = intent.getStringExtra("eventPreRequisites")!!
        eventInstructorName = intent.getStringExtra("eventInstructorName")!!
        eventInstructorPhotoUrl = intent.getStringExtra("eventInstructorPhotoUrl")!!
        thingsYouWillLearn = intent.getStringExtra("thingsYouWillLearn")!!
    }
}