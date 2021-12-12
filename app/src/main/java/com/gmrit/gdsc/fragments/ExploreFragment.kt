package com.gmrit.gdsc.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.Interpolator
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.gmrit.gdsc.R
import com.gmrit.gdsc.adapters.BannersAdapter
import com.gmrit.gdsc.adapters.PastEventsAdapter
import com.gmrit.gdsc.adapters.UpcomingEventsAdapter
import com.gmrit.gdsc.models.BannerData
import com.gmrit.gdsc.models.PastEventData
import com.gmrit.gdsc.models.UpcomingEventData
import com.gmrit.gdsc.utils.AppPreferences
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

import androidx.viewpager.widget.ViewPager
import java.lang.IllegalArgumentException
import java.lang.reflect.Field
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates


class ExploreFragment : Fragment() {

    lateinit var txtStudentName: TextView

    lateinit var bannersViewPager2: ViewPager2
    lateinit var bannersAdapter: BannersAdapter
    lateinit var eventsDataList: ArrayList<BannerData>
    lateinit var dotsIndicator: WormDotsIndicator

    lateinit var recyclerUpcomingEvents: RecyclerView
    lateinit var upcomingEventsAdapter: UpcomingEventsAdapter
    lateinit var upcomingEventsList: ArrayList<UpcomingEventData>

    lateinit var recyclerPastEvents: RecyclerView
    lateinit var pastEventsAdapter: PastEventsAdapter
    lateinit var pastEventsList: ArrayList<PastEventData>

    private var TIME_LIMIT by Delegates.notNull<Long>()

    lateinit var swipeTimer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TIME_LIMIT = 2000

    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_explore, container, false)

        context?.let { AppPreferences.init(it) }

        txtStudentName = view.findViewById(R.id.txtStudentName)

        if(AppPreferences.isLogin == true) {

            txtStudentName.text = AppPreferences.studentName
        }

        bannersViewPager2 = view.findViewById(R.id.bannersViewPager)
        eventsDataList = ArrayList<BannerData>()

        // Dots Indicator
        dotsIndicator = view.findViewById<WormDotsIndicator>(R.id.dots_indicator)

        recyclerUpcomingEvents = view.findViewById(R.id.upcomingRecycler)
        upcomingEventsList = ArrayList()

        recyclerPastEvents = view.findViewById(R.id.pastEventsRecycler)
        pastEventsList = ArrayList()


        loadBannersData()


        // For UpcomingEvents RecyclerView
        upcomingEventsList.add(UpcomingEventData("Android Study Jams","Keep yourself updated with the new launch", "Find New Experience","Get new experience with GDSC App", R.drawable.kotlin_icon))
        upcomingEventsList.add(UpcomingEventData("Android Study Jams","Keep yourself updated with the new launch", "Find New Experience","Get new experience with GDSC App", R.drawable.kotlin_icon))
        upcomingEventsList.add(UpcomingEventData("Android Study Jams","Keep yourself updated with the new launch", "Find New Experience","Get new experience with GDSC App", R.drawable.kotlin_icon))

        upcomingEventsAdapter = UpcomingEventsAdapter(requireContext(), upcomingEventsList)
        recyclerUpcomingEvents.adapter = upcomingEventsAdapter
        recyclerUpcomingEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerUpcomingEvents.setHasFixedSize(true)


        // For PastEvents Recycler View
        pastEventsList.add(PastEventData("Android Study Jams","Keep yourself updated with the new launch", "Find New Experience","Get new experience with GDSC App", R.drawable.kotlin_icon))
        pastEventsList.add(PastEventData("Android Study Jams","Keep yourself updated with the new launch", "Find New Experience","Get new experience with GDSC App", R.drawable.kotlin_icon))
        pastEventsList.add(PastEventData("Android Study Jams","Keep yourself updated with the new launch", "Find New Experience","Get new experience with GDSC App", R.drawable.kotlin_icon))

        pastEventsAdapter = PastEventsAdapter(requireContext(), pastEventsList)
        recyclerPastEvents.adapter = pastEventsAdapter
        recyclerPastEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerPastEvents.setHasFixedSize(true)


        return view
    }

    private fun loadBannersData() {

        // For Banners ViewPager
        eventsDataList.add(BannerData("Find New Experience", "Get New Experience with GDSC App", R.drawable.find_new_exp))
        eventsDataList.add(BannerData("Find New Experience", "Get New Experience with GDSC App", R.drawable.find_new_exp))
        eventsDataList.add(BannerData("Find New Experience", "Get New Experience with GDSC App", R.drawable.find_new_exp))

        bannersAdapter = context?.let { BannersAdapter(it, eventsDataList) }!!

        bannersViewPager2.adapter = bannersAdapter
        bannersViewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        bannersViewPager2.offscreenPageLimit = 2

        startViewPagerScrolling()

        dotsIndicator.setViewPager2(viewPager2 = bannersViewPager2)
    }

    fun startViewPagerScrolling() {

        var currentPage = 0
        var NUM_PAGES = 3

        NUM_PAGES = eventsDataList.size

        // Auto start of viewpager
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            bannersViewPager2.setCurrentItem(currentPage++, true)
        }


       swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }

            override fun cancel(): Boolean {
                handler.removeCallbacks(Update)
                return true
            }
        }, TIME_LIMIT, TIME_LIMIT)

    }

    override fun onDetach() {
        super.onDetach()
        swipeTimer.cancel()
    }

}