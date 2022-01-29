package com.gmrit.gdsc.fragments

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.res.AssetManager
import android.graphics.Typeface
import android.graphics.fonts.Font
import android.graphics.fonts.FontFamily
import android.graphics.fonts.FontStyle
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.gmrit.gdsc.R
import com.gmrit.gdsc.adapters.BannersAdapter
import com.gmrit.gdsc.adapters.EventsAdapter
import com.gmrit.gdsc.models.BannerData
import com.gmrit.gdsc.utils.AppPreferences
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

import com.gmrit.gdsc.activities.general.*
import com.gmrit.gdsc.activities.see_your_interest.LearnAndroidDevActivity
import com.gmrit.gdsc.activities.see_your_interest.LearnProgrammingActivity
import com.gmrit.gdsc.activities.see_your_interest.LearnUIUXActivity
import com.gmrit.gdsc.activities.see_your_interest.LearnWebDevActivity
import com.gmrit.gdsc.models.EventDetailsData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates
import android.text.style.TypefaceSpan
import androidx.cardview.widget.CardView


class ExploreFragment : Fragment() {

    lateinit var txtStudentName: TextView

    lateinit var bannersViewPager2: ViewPager2
    lateinit var bannersAdapter: BannersAdapter
    lateinit var bannersDataList: ArrayList<BannerData>
    lateinit var dotsIndicator: WormDotsIndicator

    lateinit var recyclerUpcomingEvents: RecyclerView
    lateinit var recyclerPastEvents: RecyclerView

    lateinit var upcomingEventsAdapter: EventsAdapter
    lateinit var pastEventsAdapter: EventsAdapter

    lateinit var pastEventsList: ArrayList<EventDetailsData>
    lateinit var upcomingEventsList: ArrayList<EventDetailsData>

    // See your Interest
    lateinit var imageLearnWebDev: ImageView
    lateinit var imageLearnUIUXDev: ImageView
    lateinit var imageLearnProgrammingDev: ImageView
    lateinit var imageLearnAndroidDev: ImageView

    // Share your Idea
    lateinit var btnShareYourIdea: ImageView

    // Navigation
    lateinit var iconMenu: ImageView
    lateinit var iconNotifications: ImageView

    // Join our GDSC Community
    lateinit var txtJoinOurGDSC: TextView
    lateinit var cardJoinGDSC: CardView

    lateinit var firebaseStorage: FirebaseStorage
    lateinit var storageRef: StorageReference

    private var TIME_LIMIT by Delegates.notNull<Long>()

    lateinit var swipeTimer: Timer

    lateinit var carousel: ImageCarousel

    lateinit var eventDetailsList: ArrayList<EventDetailsData>

    lateinit var txtUpcomingSeeAll: TextView
    lateinit var txtPastSeeAll: TextView

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

        eventDetailsList = ArrayList()

        txtJoinOurGDSC = view.findViewById(R.id.txtJoinOurGDSC)

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
        iconNotifications = view.findViewById(R.id.iconNotifications)

        txtUpcomingSeeAll = view.findViewById(R.id.txtSeeAllUpcoming)
        txtPastSeeAll = view.findViewById(R.id.txtSeeAllPast)

        cardJoinGDSC = view.findViewById(R.id.cardJoinCommunity)

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
            requireActivity().overridePendingTransition(R.anim.left_to_right,R.anim.right_to_left)
        }

        cardJoinGDSC.setOnClickListener {
            val intent = Intent(context, JoinGDSCActivity::class.java)
            startActivity(intent)
        }

        iconNotifications.setOnClickListener {
            val intent = Intent(context, NotificationsActivity::class.java)
            startActivity(intent)
        }

        txtUpcomingSeeAll.setOnClickListener {
            val intent = Intent(context, SeeAllEventsActivity::class.java)
            intent.putExtra("typeOfEvent","Upcoming")
            startActivity(intent)
        }

        txtPastSeeAll.setOnClickListener {
            val intent = Intent(context, SeeAllEventsActivity::class.java)
            intent.putExtra("typeOfEvent","Past")
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

        // Loading the Banners Data
        loadBannersData()

        // Loading Events Data
        loadEventDetailsData()


        return view
    }

    @SuppressLint("SimpleDateFormat")
    private fun loadEventDetailsData() {

        val db = Firebase.firestore

        db.collection("Events_Data")
            .addSnapshotListener { value, e ->

                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    Toast.makeText(context,e.localizedMessage,Toast.LENGTH_LONG).show()
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


                // Initialising RecyclerViews and Adapters
                pastEventsAdapter = EventsAdapter(requireContext(), pastEventsList)
                recyclerPastEvents.adapter = pastEventsAdapter
                recyclerPastEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                recyclerPastEvents.setHasFixedSize(true)

                upcomingEventsAdapter = EventsAdapter(requireContext(),upcomingEventsList)
                recyclerUpcomingEvents.adapter = upcomingEventsAdapter
                recyclerUpcomingEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                recyclerUpcomingEvents.setHasFixedSize(true)


            }
    }

    private fun loadBannersData() {

        val list = mutableListOf<CarouselItem>()

        // Create a child reference
        // imagesRef now points to "images"
        val imagesRef: StorageReference = storageRef.child("intro_posters")
        val imageRef: StorageReference = storageRef

        imagesRef.listAll().addOnSuccessListener {

            list.clear()
            for (item in it.items) {
                imageRef.child(item.path).downloadUrl.addOnSuccessListener { it ->
                    // For Banners ViewPager
                    Log.d("TAG",it.toString())
                    list.add(CarouselItem(it.toString()))

                    carousel.setData(list)
                }
            }


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