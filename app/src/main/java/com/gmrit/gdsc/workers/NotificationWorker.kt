package com.gmrit.gdsc.workers

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.gmrit.gdsc.models.NotificationData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.RemoteMessage
import java.text.SimpleDateFormat
import java.util.*

class NotificationWorker (appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {
    override fun doWork(): Result {

        // Do the work here--in this case, upload the images.
         val notificationTitle = inputData.getString("title")
        val notificationMessage = inputData.getString("message")

        // Indicate whether the work finished successfully with the Result
        return try {
            storeNotificationData(notificationTitle.toString(), notificationMessage.toString())
            Result.success()
        }catch (throwable: Throwable) {
            Result.failure()
        }
    }

    private fun storeNotificationData(title: String, message: String) {


        Log.d("TAG","Storing Notification Data $title and $message")
        // retrieving current Date and Time
        val currentDate: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        val currentTime: String = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())

        // sending Notification Data to Firebase
        val  db = Firebase.firestore
        val notificationData = NotificationData(title, message, currentDate + " "+currentTime)
        db.collection("Notification_Data")
            .add(notificationData)
            .addOnSuccessListener {

            }.addOnFailureListener {
                Toast.makeText(applicationContext,"Some Network Error Occurred!", Toast.LENGTH_LONG).show()
            }


    }

}