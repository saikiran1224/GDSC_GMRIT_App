package com.gmrit.gdsc.activities.general

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.gmrit.gdsc.R
import com.gmrit.gdsc.utils.AppPreferences
import org.w3c.dom.Text

class StudentProfileActivity : AppCompatActivity() {

    lateinit var closeIcon: ImageView

    lateinit var txtProfileShortName: TextView
    lateinit var txtStudentName: TextView
    lateinit var txtStudentEmailID: TextView


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(R.color.light_light_blue)

        setContentView(R.layout.activity_student_profile)

        AppPreferences.init(this)

        closeIcon = findViewById(R.id.closeIcon)

        txtProfileShortName = findViewById(R.id.txtProfileShortName)
        txtStudentName = findViewById(R.id.txtStudentName)
        txtStudentEmailID = findViewById(R.id.txtStudentEmailID)

        // Setting Name and Email ID

        val studentName = AppPreferences.studentName.toString()

        txtStudentName.text = studentName
        txtStudentEmailID.text = AppPreferences.studentEmail.toString()

        // Setting Short Name using the help of Indexes
        val count = studentName.split(" ").toTypedArray()
        if(count.size == 1) {
            txtProfileShortName.text = studentName[0].toString()
        } else {
            val index = studentName.lastIndexOf(' ')
            val firstName = index.let { it1 -> studentName.substring(0, it1) }
            val lastName = index.plus(1).let { it1 ->
                studentName.substring(it1)
            }
            txtProfileShortName.text = firstName.toString()[0] + lastName[0].toString()
        }


        closeIcon.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
        }






    }
}