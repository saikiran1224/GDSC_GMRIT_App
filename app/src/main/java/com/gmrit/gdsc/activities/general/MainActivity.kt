package com.gmrit.gdsc.activities.general

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.gmrit.gdsc.BuildConfig
import com.gmrit.gdsc.R
import com.gmrit.gdsc.activities.onboarding.OnBoardingActivity
import com.gmrit.gdsc.fragments.ExploreFragment
import com.gmrit.gdsc.fragments.MyEventsFragment
import com.gmrit.gdsc.models.AppUtilsData
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {

    private val exploreFragment: ExploreFragment = ExploreFragment()
    private val myEventsFragment: MyEventsFragment = MyEventsFragment()

    lateinit var bottomNavigationView: BottomNavigationView

    lateinit var appUtilsData: AppUtilsData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(com.gmrit.gdsc.R.color.purple)
        setContentView(com.gmrit.gdsc.R.layout.activity_main)

        // function to check the Version of App
        checkAppVersion()


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
                R.id.ic_explore -> {
                    fragment = exploreFragment
                }

                R.id.ic_pages -> {
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

    // Checking the App Version to notify about the Update
    private fun checkAppVersion() {

        // Retrieving the App Utils data from Firestore
        val db = Firebase.firestore

        val docRef = db.collection("App_Utils").document("i05QAVI35JELY9nDstF5")
        docRef.get().addOnSuccessListener {

            val versionName = it.data!!.get("Version").toString()
            val versionCode = it.data!!.get("VersionCode").toString()

            if(getAppVersionFromString(versionName) > getAppVersionFromString(BuildConfig.VERSION_NAME)) {
                // Newer version of App is available
                showUpdateAppDialog()
            }

        }

    }

    private fun showUpdateAppDialog() {

        val updateAppDialog = Dialog(this)
        updateAppDialog.setContentView(R.layout.update_app_dialog)
        updateAppDialog.setCancelable(false)
        updateAppDialog.setCanceledOnTouchOutside(false)
        updateAppDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        updateAppDialog.findViewById<CardView>(R.id.btnProceed).setOnClickListener {

            // Navigate to Playstore Store Listing
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
            } catch (e: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
            }

        }

        updateAppDialog.findViewById<CardView>(R.id.btnRemindLater).setOnClickListener {
            updateAppDialog.dismiss()
        }

        updateAppDialog.show()


    }

    fun getAppVersionFromString(version: String): Int { // "2.3.5"
        val versions = version.split(".")
        val major = versions[0].toInt() * 10000 // 20000
        val minor = versions[1].toInt() * 1000 // 3000
        val patch = versions[2].toInt() * 100 // 500
        return major + minor + patch
    }


}