package com.gmrit.gdsc.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.Interpolator
import android.widget.ImageView
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
import com.bumptech.glide.load.ImageHeaderParser
import com.gmrit.gdsc.activities.general.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import java.lang.IllegalArgumentException
import java.lang.reflect.Field
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates


class ExploreFragment : Fragment() {

    lateinit var txtStudentName: TextView

    lateinit var bannersViewPager2: ViewPager2
    lateinit var bannersAdapter: BannersAdapter
    lateinit var bannersDataList: ArrayList<BannerData>
    lateinit var dotsIndicator: WormDotsIndicator

    lateinit var recyclerUpcomingEvents: RecyclerView
    lateinit var upcomingEventsAdapter: UpcomingEventsAdapter
    lateinit var upcomingEventsList: ArrayList<UpcomingEventData>

    lateinit var recyclerPastEvents: RecyclerView
    lateinit var pastEventsAdapter: PastEventsAdapter
    lateinit var pastEventsList: ArrayList<PastEventData>

    // See your Interest
    lateinit var imageLearnWebDev: ImageView
    lateinit var imageLearnUIUXDev: ImageView
    lateinit var imageLearnProgrammingDev: ImageView
    lateinit var imageLearnAndroidDev: ImageView

    // Share your Idea
    lateinit var btnShareYourIdea: ImageView

    // Navigation
    lateinit var iconMenu: ImageView

    lateinit var firebaseStorage: FirebaseStorage
    lateinit var storageRef: StorageReference

    private var TIME_LIMIT by Delegates.notNull<Long>()

    lateinit var swipeTimer: Timer

    lateinit var carousel: ImageCarousel

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

        //bannersViewPager2 = view.findViewById(R.id.bannersViewPager)
        bannersDataList = ArrayList<BannerData>()

        swipeTimer = Timer()
        TIME_LIMIT = 2000

        // Create a storage reference from our app
        // Firebase Storage
        firebaseStorage = Firebase.storage
        storageRef = firebaseStorage.reference


        // Dots Indicator
        dotsIndicator = view.findViewById<WormDotsIndicator>(R.id.dots_indicator)

        carousel = view.findViewById(R.id.carousel)
        carousel.registerLifecycle(lifecycle)


        recyclerUpcomingEvents = view.findViewById(R.id.upcomingRecycler)
        upcomingEventsList = ArrayList()

        recyclerPastEvents = view.findViewById(R.id.pastEventsRecycler)
        pastEventsList = ArrayList()

        // See your Interest
        imageLearnWebDev = view.findViewById(R.id.imageLearnWebDev)
        imageLearnAndroidDev = view.findViewById(R.id.imageLearnAndroidDev)
        imageLearnProgrammingDev = view.findViewById(R.id.imageLearnProgrammingDev)
        imageLearnUIUXDev = view.findViewById(R.id.imageLearnUIUXDev)

        // Share your Idea
        btnShareYourIdea = view.findViewById(R.id.btnShareYourIdea)

        // Navigation Related
        iconMenu = view.findViewById(R.id.iconMenu)



        iconMenu.setOnClickListener {
            val intent = Intent(context, NavigationActivity::class.java)
            startActivity(intent)
        }


        btnShareYourIdea.setOnClickListener {
            val intent = Intent(context, ShareYourIdeaActivity::class.java)
            startActivity(intent)
        }

        imageLearnWebDev.setOnClickListener {
            val intent = Intent(context, LearnWebDevActivity::class.java)
            startActivity(intent)
        }

        imageLearnAndroidDev.setOnClickListener {
            val intent = Intent(context, LearnAndroidDevActivity::class.java)
            startActivity(intent)
        }

        imageLearnProgrammingDev.setOnClickListener {
            val intent = Intent(context, LearnProgrammingActivity::class.java)
            startActivity(intent)
        }

        imageLearnUIUXDev.setOnClickListener {
            val intent = Intent(context, LearnUIUXActivity::class.java)
            startActivity(intent)
        }

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

        val list = mutableListOf<CarouselItem>()

        // Create a child reference
// imagesRef now points to "images"
        val imagesRef: StorageReference = storageRef.child("intro_posters")
        val imageRef: StorageReference = storageRef

        list.add(CarouselItem("https://firebasestorage.googleapis.com/v0/b/gdsc-gmrit.appspot.com/o/intro_posters%2F3.png?alt=media&token=81024419-c30d-42eb-907d-54e8f3d803eb"))


        imagesRef.listAll().addOnSuccessListener {

            list.clear()
            for (item in it.items) {
                imageRef.child(item.path).downloadUrl.addOnSuccessListener { it ->
                    // For Banners ViewPager
                    Log.d("TAG",it.toString())
                    list.add(CarouselItem(it.toString()))
                    //list.add(CarouselItem("https://firebasestorage.googleapis.com/v0/b/gdsc-gmrit.appspot.com/o/intro_posters%2F3.png?alt=media&token=81024419-c30d-42eb-907d-54e8f3d803eb"))
                    //bannersDataList.add(BannerData(item.name, it.toString()))
                    carousel.setData(list)
                }
            }

            Log.d("TAG","hello "+list.toString())
            //carousel.setData(list)

            //Log.d("AL",bannersDataList.toString())







           // ViewPager used to Load Banners
           /* bannersAdapter = BannersAdapter(context!!,bannersDataList)

            bannersViewPager2.adapter = bannersAdapter
            bannersViewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            bannersViewPager2.offscreenPageLimit = 2

            startViewPagerScrolling()

            dotsIndicator.setViewPager2(viewPager2 = bannersViewPager2)*/


        }.addOnFailureListener {
            Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
        }


    }

    fun startViewPagerScrolling() {

        var currentPage = 0
        var NUM_PAGES = 3

       // NUM_PAGES = bannersDataList.size

        // Auto start of viewpager
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            bannersViewPager2.setCurrentItem(currentPage++, true)
        }



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