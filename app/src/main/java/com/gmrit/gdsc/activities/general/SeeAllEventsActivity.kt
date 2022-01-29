package com.gmrit.gdsc.activities.general

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmrit.gdsc.R
import com.gmrit.gdsc.adapters.EventsAdapter
import com.gmrit.gdsc.models.EventDetailsData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SeeAllEventsActivity : AppCompatActivity() {

    lateinit var closeIcon: ImageView

    lateinit var eventsAdapter: EventsAdapter

    lateinit var eventsRecycler: RecyclerView

    lateinit var eventDetailsList: ArrayList<EventDetailsData>
    lateinit var upcomingEventsList: ArrayList<EventDetailsData>
    lateinit var pastEventsList: ArrayList<EventDetailsData>

    lateinit var typeOfEvents: String
    lateinit var txtTypeOfEvents: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_all_events)

        closeIcon = findViewById(R.id.closeIcon)
        txtTypeOfEvents = findViewById(R.id.txtTypeOfEvents)

        eventsRecycler = findViewById(R.id.eventsRecycler)
        eventDetailsList = ArrayList()
        upcomingEventsList = ArrayList()
        pastEventsList = ArrayList()

        // checking whether the intent is for Upcoming or Past Events
        val intent = intent
        typeOfEvents = intent.getStringExtra("typeOfEvent").toString()

        if(typeOfEvents.equals("Upcoming"))
            txtTypeOfEvents.setText("Upcoming Events")
        else if(typeOfEvents.equals("Past"))
            txtTypeOfEvents.setText("Past Events")
        else
            txtTypeOfEvents.setText("Events")


        closeIcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // loading Event Details Data
        loadEventDetailsData(typeOfEvents)

    }

    @SuppressLint("SimpleDateFormat")
    private fun loadEventDetailsData(typeOfEvents: String) {

        val db = Firebase.firestore

        db.collection("Events_Data")
            .addSnapshotListener { value, e ->

                if (e != null) {
                    Log.w(ContentValues.TAG, "Listen failed.", e)
                    Toast.makeText(this,e.localizedMessage, Toast.LENGTH_LONG).show()
                    return@addSnapshotListener
                }

                for(doc in value!!) {
                    // adding each document into eventDetailsList
                    eventDetailsList.add(doc.toObject<EventDetailsData>())
                }

                Log.d("TAG",""+eventDetailsList.toString())

                // Retrieving Current Date
                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
                val  date: String = simpleDateFormat.format(Date())
                val currentDate: Date = simpleDateFormat.parse(date)!!

                // Separate Events using the parameter of Current Date into UpcomingEventsList and PastEventsList
                for(event in eventDetailsList) {

                    val eventDate: Date = simpleDateFormat.parse(event.eventDate)!!

                    if (eventDate.before(currentDate))
                        pastEventsList.add(event)
                    else if (eventDate.after(currentDate))
                        upcomingEventsList.add(event)
                    else {
                        // If the event date is today, then add to Upcoming Events List
                        upcomingEventsList.add(event)
                    }

                }

                // Based on the eventStatus we will pass the PastEventsList or UpcomingEventsList
                if(typeOfEvents.equals("Upcoming")) {
                    eventsAdapter = EventsAdapter(this, upcomingEventsList)
                } else {
                    eventsAdapter = EventsAdapter(this, pastEventsList)
                }

                eventsRecycler.adapter = eventsAdapter
                eventsRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                eventsRecycler.setHasFixedSize(true)


            }
    }
}