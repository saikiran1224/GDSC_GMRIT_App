package com.gmrit.gdsc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmrit.gdsc.R
import com.gmrit.gdsc.adapters.OnGoingEventsAdapter
import com.gmrit.gdsc.adapters.UpcomingEventsAdapter
import com.gmrit.gdsc.models.OnGoingEventData
import com.gmrit.gdsc.models.UpcomingEventData

class OnGoingFragment : Fragment() {

    lateinit var onGoingEventsRecyclerView: RecyclerView

    lateinit var onGoingEventsList: ArrayList<OnGoingEventData>
    lateinit var onGoingEventsAdapter: OnGoingEventsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_on_going, container, false)

        onGoingEventsRecyclerView = view.findViewById(R.id.onGoingEventsRecyclerView)

        onGoingEventsList = ArrayList()

        // For UpcomingEvents RecyclerView
        onGoingEventsList.add(OnGoingEventData("Android Study Jams","Keep yourself updated with the new launch", "Find New Experience","Get new experience with GDSC App", R.drawable.kotlin_icon))
        onGoingEventsList.add(OnGoingEventData("Android Study Jams","Keep yourself updated with the new launch", "Find New Experience","Get new experience with GDSC App", R.drawable.kotlin_icon))
        onGoingEventsList.add(OnGoingEventData("Android Study Jams","Keep yourself updated with the new launch", "Find New Experience","Get new experience with GDSC App", R.drawable.kotlin_icon))

        onGoingEventsAdapter = OnGoingEventsAdapter(requireContext(), onGoingEventsList)
        onGoingEventsRecyclerView.adapter = onGoingEventsAdapter
        onGoingEventsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        onGoingEventsRecyclerView.setHasFixedSize(true)

        return view
    }

}