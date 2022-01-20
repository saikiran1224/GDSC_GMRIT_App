package com.gmrit.gdsc.activities.general

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.gmrit.gdsc.fragments.ExploreFragment
import com.gmrit.gdsc.fragments.MyEventsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

import androidx.annotation.NonNull
import androidx.core.content.ContentProviderCompat.requireContext
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
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {

    private val exploreFragment: ExploreFragment = ExploreFragment()
    private val myEventsFragment: MyEventsFragment = MyEventsFragment()

    lateinit var bottomNavigationView: BottomNavigationView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(com.gmrit.gdsc.R.color.purple)
        setContentView(com.gmrit.gdsc.R.layout.activity_main)



       bottomNavigationView = findViewById(com.gmrit.gdsc.R.id.bottomNavigationView)

        replaceFragment(exploreFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

  private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var fragment: Fragment? = null
            when (item.itemId) {
                com.gmrit.gdsc.R.id.ic_explore -> {
                    fragment = exploreFragment
                }

                com.gmrit.gdsc.R.id.ic_pages -> {
                    fragment = myEventsFragment
                }
                else -> {

                }
            }
            replaceFragment(fragment!!)
        }

    // This method is used to replace Fragment
    private fun replaceFragment(fragment : Fragment): Boolean {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(com.gmrit.gdsc.R.id.fragmentContainer,fragment)
        transaction.commit()
        return true
    }
}