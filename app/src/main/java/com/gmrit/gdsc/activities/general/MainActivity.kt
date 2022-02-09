package com.gmrit.gdsc.activities.general

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gmrit.gdsc.R
import com.gmrit.gdsc.fragments.ExploreFragment
import com.gmrit.gdsc.fragments.MyEventsFragment
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging


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

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            //val msg = getString(R.string.msg_token_fmt, token)
            Log.d("TAG", token.toString())
            //Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })

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

    override fun onBackPressed() {
        super.onBackPressed()
    }
}