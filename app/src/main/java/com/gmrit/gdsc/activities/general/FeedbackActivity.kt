package com.gmrit.gdsc.activities.general

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.widget.NestedScrollView
import com.gmrit.gdsc.R
import com.gmrit.gdsc.models.FeedbackData
import com.gmrit.gdsc.utils.AppPreferences
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FeedbackActivity : AppCompatActivity() {

    lateinit var closeIcon: ImageView

    lateinit var edtFeedback: TextInputLayout
    lateinit var editFeedback: TextInputEditText

    lateinit var btnSubmit: CardView

    lateinit var nestedScrollView: NestedScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(R.color.light_light_blue)

        setContentView(R.layout.activity_feedback)

        AppPreferences.init(this)

        closeIcon = findViewById(R.id.closeIcon)

        nestedScrollView = findViewById(R.id.nestedScrollView)

        edtFeedback = findViewById(R.id.edtFeedback)
        editFeedback = findViewById(R.id.editFeedback)

        btnSubmit = findViewById(R.id.btnSubmit)

        btnSubmit.setOnClickListener {

            val feedbackMessage: String = editFeedback.text.toString()
            val studentName: String = AppPreferences.studentName!!
            val studentEmailID: String = AppPreferences.studentEmail!!

            if(studentName.isNotEmpty() && studentEmailID.isNotEmpty()) {
                if(feedbackMessage.isEmpty()) {
                    edtFeedback.error = "Please share your valuable feedback"
                } else {

                    edtFeedback.error = ""

                    // sending Data to Cloud Firestore
                    val db = Firebase.firestore
                    val feedbackData = FeedbackData(studentName, studentEmailID, feedbackMessage)
                    db.collection("Feedback_Data")
                        .add(feedbackData)
                        .addOnSuccessListener {
                            val intent = Intent(this, NavigationActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this,"Thanks for writing your feedback!",Toast.LENGTH_LONG).show()
                            Snackbar.make(nestedScrollView, "Feedback sent Successfully! We will get back to you soon...", Snackbar.LENGTH_LONG).show()

                        }
                        .addOnFailureListener {
                            val intent = Intent(this, NavigationActivity::class.java)
                            startActivity(intent)
                            Snackbar.make(nestedScrollView, "Some Error Occurred! Please try again...", Snackbar.LENGTH_LONG).show()
                        }
                }
            }

        }

        closeIcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}