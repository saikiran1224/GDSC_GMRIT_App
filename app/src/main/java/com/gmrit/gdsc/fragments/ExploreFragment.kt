package com.gmrit.gdsc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class ExploreFragment : Fragment() {

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_explore, container, false)

        context?.let { AppPreferences.init(it) }

        txtStudentName = view.findViewById(R.id.txtStudentName)

        if(AppPreferences.isLogin == true) {

            txtStudentName.text = AppPreferences.studentName
        }

        recyclerBanners = view.findViewById(R.id.recyclerBanners)
        eventsDataList = ArrayList()

        recyclerUpcomingEvents = view.findViewById(R.id.upcomingRecycler)
        upcomingEventsList = ArrayList()

        recyclerPastEvents = view.findViewById(R.id.pastEventsRecycler)
        pastEventsList = ArrayList()


        // For Banners RecyclerView
        eventsDataList.add(BannerData("Find New Experience", "Get New Experience with GDSC App", R.drawable.find_new_exp))
        eventsDataList.add(BannerData("Find New Experience", "Get New Experience with GDSC App", R.drawable.find_new_exp))
        eventsDataList.add(BannerData("Find New Experience", "Get New Experience with GDSC App", R.drawable.find_new_exp))

        bannersAdapter = context?.let { BannersAdapter(it, eventsDataList) }!!
        recyclerBanners.adapter = bannersAdapter
        recyclerBanners.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerBanners.setHasFixedSize(true)


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

}