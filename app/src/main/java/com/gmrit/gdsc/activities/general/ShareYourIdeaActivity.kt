package com.gmrit.gdsc.activities.general

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.gmrit.gdsc.R
import com.gmrit.gdsc.models.ShareYourIdeaData
import com.gmrit.gdsc.utils.AppPreferences
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class ShareYourIdeaActivity : AppCompatActivity() {

    lateinit var txtHaveAnIdea: TextView
    lateinit var txtShareYourIdea: TextView

    lateinit var relativeLayout: RelativeLayout

    lateinit var btnbackButton: ImageView

    lateinit var editName: TextInputEditText
    lateinit var editEmailID: TextInputEditText
    lateinit var editIdeaDesc: TextInputEditText

    lateinit var edtIdea: TextInputLayout

    lateinit var btnSubmit: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(R.color.ide_blue)

        setContentView(R.layout.activity_share_your_idea)

        AppPreferences.init(this)

        txtHaveAnIdea = findViewById(R.id.txtHaveAnIdea)
        txtShareYourIdea = findViewById(R.id.txtShareYourIdea)
        btnbackButton = findViewById(R.id.backButtonIcon)

        relativeLayout = findViewById(R.id.relativeLayout)

        edtIdea = findViewById(R.id.edtIdea)

        editName = findViewById(R.id.editName)
        editEmailID = findViewById(R.id.editEmailID)
        editIdeaDesc = findViewById(R.id.editIdeaDescription)

        btnSubmit = findViewById(R.id.btnSubmit)

        // setting Spannable Text for the above Text Views

        val spannable_1 = SpannableStringBuilder("Have an idea! share with us")
        spannable_1.setSpan(ForegroundColorSpan(getColor(R.color.idea_orange)), 8, 12, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        txtHaveAnIdea!!.text = spannable_1

        val spannable_2 = SpannableStringBuilder("Share your idea with us and lets help together to build the product")
        spannable_2.setSpan(ForegroundColorSpan(getColor(R.color.idea_orange)), 50, spannable_2.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        txtShareYourIdea!!.text = spannable_2

        btnbackButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // setting Data taking from Shared Preferences and setting on EditText
        if(AppPreferences.studentName!!.isNotEmpty() && AppPreferences.studentEmail!!.isNotEmpty()) {
            editName.setText(AppPreferences.studentName.toString())
            editName.isEnabled = false
            editEmailID.setText(AppPreferences.studentEmail.toString())
            editEmailID.isEnabled = false
        }

        // sending Idea data to Cloud Firestore
        btnSubmit.setOnClickListener {

            val studentName = editName.text.toString()
            val studentEmail = editEmailID.text.toString()
            val ideaDesc = editIdeaDesc.text.toString()

            if(studentName.isEmpty() && studentEmail.isEmpty()) {
                Toast.makeText(this, "Unable to fetch your Name and Email ID!. Please enter them manually",Toast.LENGTH_LONG).show()
                editName.isEnabled = true
                editName.setText("")

                editEmailID.isEnabled = true
                editEmailID.setText("")
            } else if(ideaDesc.isEmpty()) {
                edtIdea.error = "Please narrate your Idea here"
            } else {
                edtIdea.error = ""

                // sending data to CloudFirestore
                sendDataToFirestore(studentName, studentEmail, ideaDesc)
            }

        }
    }

    private fun sendDataToFirestore(studentName: String, studentEmail: String, ideaDesc: String) {

        // creating an instance for Firebase Firestore
        val db = Firebase.firestore
        val ideaDescData = ShareYourIdeaData(studentName,studentEmail,ideaDesc)
        db.collection("ideas_data")
            .add(ideaDescData)
            .addOnSuccessListener {
                Snackbar.make(relativeLayout,"Thanks for submitting your Idea! We will get back to you soon until then stay tuned",Snackbar.LENGTH_LONG).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }.addOnFailureListener {
                Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
            }



    }
}