package com.gmrit.gdsc.activities.general

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmrit.gdsc.R
import com.gmrit.gdsc.adapters.BannersAdapter
import com.gmrit.gdsc.adapters.PastEventsAdapter
import com.gmrit.gdsc.adapters.UpcomingEventsAdapter
import com.gmrit.gdsc.models.BannerData
import com.gmrit.gdsc.models.PastEventData
import com.gmrit.gdsc.models.UpcomingEventData
import com.gmrit.gdsc.utils.AppPreferences
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var txtStudentName: TextView

    lateinit var recyclerBanners: RecyclerView
    lateinit var bannersAdapter: BannersAdapter
    lateinit var eventsDataList: ArrayList<BannerData>

    lateinit var recyclerUpcomingEvents: RecyclerView
    lateinit var upcomingEventsAdapter: UpcomingEventsAdapter
    lateinit var upcomingEventsList: ArrayList<UpcomingEventData>

    lateinit var recyclerPastEvents: RecyclerView
    lateinit var pastEventsAdapter: PastEventsAdapter
    lateinit var pastEventsList: ArrayList<PastEventData>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(R.color.purple)

        setContentView(R.layout.activity_main)

        AppPreferences.init(this)

        txtStudentName = findViewById(R.id.txtStudentName)

        if(AppPreferences.isLogin == true) {

            txtStudentName.text = AppPreferences.studentName

            Toast.makeText(this, "Welcome, " + AppPreferences.studentName, Toast.LENGTH_LONG).show()
        }

        recyclerBanners = findViewById(R.id.recyclerBanners)
        eventsDataList = ArrayList()

        recyclerUpcomingEvents = findViewById(R.id.upcomingRecycler)
        upcomingEventsList = ArrayList()

        recyclerPastEvents = findViewById(R.id.pastEventsRecycler)
        pastEventsList = ArrayList()


        // For Banners RecyclerView
        eventsDataList.add(BannerData("Find New Experience", "Get New Experience with GDSC App", R.drawable.find_new_exp))
        eventsDataList.add(BannerData("Find New Experience", "Get New Experience with GDSC App", R.drawable.find_new_exp))
        eventsDataList.add(BannerData("Find New Experience", "Get New Experience with GDSC App", R.drawable.find_new_exp))

        bannersAdapter = BannersAdapter(this, eventsDataList)
        recyclerBanners.adapter = bannersAdapter
        recyclerBanners.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerBanners.setHasFixedSize(true)


        // For UpcomingEvents RecyclerView
        upcomingEventsList.add(UpcomingEventData("Android Study Jams","Keep yourself updated with the new launch", "Find New Experience","Get new experience with GDSC App", R.drawable.kotlin_icon))
        upcomingEventsList.add(UpcomingEventData("Android Study Jams","Keep yourself updated with the new launch", "Find New Experience","Get new experience with GDSC App", R.drawable.kotlin_icon))
        upcomingEventsList.add(UpcomingEventData("Android Study Jams","Keep yourself updated with the new launch", "Find New Experience","Get new experience with GDSC App", R.drawable.kotlin_icon))

        upcomingEventsAdapter = UpcomingEventsAdapter(this, upcomingEventsList)
        recyclerUpcomingEvents.adapter = upcomingEventsAdapter
        recyclerUpcomingEvents.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerUpcomingEvents.setHasFixedSize(true)


        // For PastEvents Recycler View
        pastEventsList.add(PastEventData("Android Study Jams","Keep yourself updated with the new launch", "Find New Experience","Get new experience with GDSC App", R.drawable.kotlin_icon))
        pastEventsList.add(PastEventData("Android Study Jams","Keep yourself updated with the new launch", "Find New Experience","Get new experience with GDSC App", R.drawable.kotlin_icon))
        pastEventsList.add(PastEventData("Android Study Jams","Keep yourself updated with the new launch", "Find New Experience","Get new experience with GDSC App", R.drawable.kotlin_icon))

        pastEventsAdapter = PastEventsAdapter(this, pastEventsList)
        recyclerPastEvents.adapter = pastEventsAdapter
        recyclerPastEvents.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerPastEvents.setHasFixedSize(true)









    }
}