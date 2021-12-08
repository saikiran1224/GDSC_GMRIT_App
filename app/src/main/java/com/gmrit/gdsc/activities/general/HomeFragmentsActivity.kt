package com.gmrit.gdsc.activities.general

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.gmrit.gdsc.R
import com.gmrit.gdsc.fragments.ExploreFragment
import com.gmrit.gdsc.fragments.MyEventsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragmentsActivity : AppCompatActivity() {

    private val exploreFragment: ExploreFragment = ExploreFragment()
    private val myEventsFragment: MyEventsFragment = MyEventsFragment()

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(R.color.purple)

        setContentView(R.layout.activity_home_fragments)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        replaceFragment(exploreFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.ic_explore -> {
                    replaceFragment(exploreFragment)
                    true
                }

                R.id.ic_pages -> {
                    replaceFragment(myEventsFragment)
                    true
                }
                else -> {false}
            }
        }

    }

    // This method is used to replace Fragment
    private fun replaceFragment(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer,fragment)
        transaction.commit()
    }
}