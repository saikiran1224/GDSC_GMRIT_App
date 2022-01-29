package com.gmrit.gdsc.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.gmrit.gdsc.R
import com.gmrit.gdsc.activities.general.MainActivity
import com.gmrit.gdsc.activities.general.SeeAllEventsActivity
import com.gmrit.gdsc.models.NotificationData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class MyFirebaseMessagingService:FirebaseMessagingService() {

    private val ADMIN_CHANNEL_ID = "admin_channel"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        //startActivity(intent)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationID = Random.nextInt(3000)

        // Get Message details
        val title = remoteMessage.data["title"]
        val message = remoteMessage.data["message"]

        var intent: Intent? = null
        intent = Intent(this, MainActivity::class.java)






     /*   if(title!!.contains("Event")) {
            intent = Intent(this, SeeAllEventsActivity::class.java)
            intent.putExtra("typeOfEvent","Upcoming")
        } else {
        }*/

        /*
        Apps targeting SDK 26 or above (Android O) must implement notification channels and add its notifications
        to at least one of them. Therefore, confirm if version is Oreo or higher, then setup notification channel
      */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setupChannels(notificationManager)
        }

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.gdsc_logo)

        val notificationSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
            .setSmallIcon(R.drawable.gdsc_logo)
            .setLargeIcon(largeIcon)
            .setContentTitle(remoteMessage.data["title"])
            .setContentText(remoteMessage.data["message"])
            .setStyle(NotificationCompat.BigTextStyle().bigText(remoteMessage.data["message"]))
            .setAutoCancel(true)
            .setSound(notificationSoundUri)
            .setContentIntent(pendingIntent)

        //Set notification color to match your app color template
        notificationBuilder.setSmallIcon(R.drawable.gdsc_logo)
        notificationBuilder.color = resources.getColor(R.color.blue)
        if (false) {
            error("Assertion failed")
        }
        notificationManager.notify(notificationID, notificationBuilder.build())

      /*  // retrieving current Date and Time
        val currentDate: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        val currentTime: String = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())


        // sending Notification Data to Firebase
        val  db = Firebase.firestore
        val notificationData = NotificationData(title.toString(), message.toString(), currentDate + " "+currentTime)
        db.collection("Notification_Data")
            .add(notificationData)
            .addOnSuccessListener {

            }.addOnFailureListener {
                Toast.makeText(this,"Some Network Error Occurred!",Toast.LENGTH_LONG).show()
            }*/
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun setupChannels(notificationManager: NotificationManager?) {
        val adminChannelName: CharSequence = "New notification"
        val adminChannelDescription = "Device to devie notification"
        val adminChannel: NotificationChannel
        adminChannel = NotificationChannel(
            ADMIN_CHANNEL_ID,
            adminChannelName,
            NotificationManager.IMPORTANCE_HIGH
        )
        adminChannel.description = adminChannelDescription
        adminChannel.enableLights(true)
        adminChannel.lightColor = Color.RED
        adminChannel.enableVibration(true)
        notificationManager?.createNotificationChannel(adminChannel)
    }

    companion object {

        private const val TAG = "mFirebaseIIDService"
    }


}