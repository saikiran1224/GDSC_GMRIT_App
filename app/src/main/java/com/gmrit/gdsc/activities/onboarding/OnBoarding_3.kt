package com.gmrit.gdsc.activities.onboarding

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.gmrit.gdsc.R
import com.gmrit.gdsc.activities.auth.SignUpActivity
import com.gmrit.gdsc.activities.auth.WelcomeActivity
import com.gmrit.gdsc.models.StudentData
import com.gmrit.gdsc.utils.AppPreferences
import com.gmrit.gdsc.utils.OnSwipeTouchListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class OnBoarding_3 : AppCompatActivity() {


    lateinit var txtJoinOurCommunity : TextView
    lateinit var relativeJoinOurCommunity: RelativeLayout
    lateinit var btnContinue: CardView

    lateinit var auth: FirebaseAuth

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setting color of Status Bar
        window.statusBarColor = getColor(R.color.lightYellow)

        setContentView(R.layout.activity_on_boarding3)

        AppPreferences.init(this)

        txtJoinOurCommunity = findViewById(R.id.txtJoinOurCommunity)
        relativeJoinOurCommunity = findViewById(R.id.relativeJoinOur)
        btnContinue = findViewById(R.id.btnContinue)

        auth = FirebaseAuth.getInstance()


        // setting Span for Find New Experience Text
        val spannable = SpannableStringBuilder("Join our Community")
        spannable.setSpan(ForegroundColorSpan(Color.WHITE), 9, 18, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        txtJoinOurCommunity.text = spannable

        // implementing Swipe Event
        with(relativeJoinOurCommunity) {
            // implementing onTouchListener to detect onSwipeRightEvent
            setOnTouchListener(object : OnSwipeTouchListener() {

                override fun onSwipeRight() {
                    super.onSwipeRight()
                    val intent = Intent(this@OnBoarding_3, OnBoarding_2::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.right_in, R.anim.right_out)
                }

            })
        }

        // calling to show BottomSheetDialog on clicking Button
        btnContinue.setOnClickListener { showLoginBottomSheet() }

    }

    private fun showLoginBottomSheet() {

        val bottomSheetDialog = BottomSheetDialog(this,R.style.BottomSheetDialog )
        bottomSheetDialog.setContentView(R.layout.sign_in_bottom_sheet)
        bottomSheetDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        bottomSheetDialog.setCanceledOnTouchOutside(true)

        val txtWelcomeBack = bottomSheetDialog.findViewById<TextView>(R.id.txtWelcomeBack)
        val txtForgotPass = bottomSheetDialog.findViewById<TextView>(R.id.txtForgetPassword)
        val txtSignUpHere = bottomSheetDialog.findViewById<TextView>(R.id.txtSignUp)
        val btnLogin = bottomSheetDialog.findViewById<CardView>(R.id.btnLogin)

        val edtEmailID = bottomSheetDialog.findViewById<TextInputLayout>(R.id.materialEmailID)
        val edtPassword = bottomSheetDialog.findViewById<TextInputLayout>(R.id.materialPassword)

        val editEmailID = bottomSheetDialog.findViewById<TextInputEditText>(R.id.editEmailID)
        val editPassword = bottomSheetDialog.findViewById<TextInputEditText>(R.id.editPassword)



        // setting Spannable Text for the above Text Views

        val spannable_1 = SpannableStringBuilder("Welcome Back to GDSC")
        spannable_1.setSpan(ForegroundColorSpan(getColor(R.color.lightRed)), 16, 20, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        txtWelcomeBack!!.text = spannable_1

        // setting Span for Find New Experience Text
        val spannable_2 = SpannableStringBuilder("Forget Password ? Click here")
        spannable_2.setSpan(ForegroundColorSpan(getColor(R.color.darkBlue)), 18, 28, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        txtForgotPass!!.text = spannable_2

        // setting Span for Find New Experience Text
        val spannable_3 = SpannableStringBuilder("Don't have an account? Signup here")
        spannable_3.setSpan(ForegroundColorSpan(getColor(R.color.darkBlue)), 23, 34, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        spannable_3.setSpan(UnderlineSpan(), 23, 34, 0)
        txtSignUpHere!!.text = spannable_3

        txtSignUpHere.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        btnLogin!!.setOnClickListener {

            val emailID = editEmailID?.text.toString().trim()
            val password = editPassword?.text.toString().trim()

            if(emailID.isEmpty()) {
                edtEmailID!!.error = "Please enter Email ID"
            } else if(password.isEmpty()) {
                edtEmailID!!.error = ""
                edtPassword!!.error = "Please enter Password"
            } else {
                edtPassword!!.error = ""
                auth.signInWithEmailAndPassword(emailID, password).addOnCompleteListener(this) {
                        task ->
                    if(task.isSuccessful) {

                        // User signed in Successfully
                        val user = auth.currentUser
                        val db = Firebase.firestore
                        db.collection("Students_Data")
                            .whereEqualTo("emailID", emailID)
                            .get()
                            .addOnSuccessListener {
                                documents ->
                                if(documents.size() == 1) {
                                    for(document in documents) {
                                        val studentData = document.toObject<StudentData>()

                                        AppPreferences.isLogin = true
                                        AppPreferences.studentName = studentData.name
                                        AppPreferences.studentJNTUNo = studentData.jntuNo
                                        AppPreferences.studentEmail = studentData.emailID

                                        val intent = Intent(this, WelcomeActivity::class.java)
                                        Toast.makeText(this, "Welcome, " + AppPreferences.studentName +" back to GDSC GMRIT!", Toast.LENGTH_LONG).show()
                                        startActivity(intent)

                                    }

                                } else {
                                    Toast.makeText(this, "Some Error Occurred! Please try again. EMail Duplication", Toast.LENGTH_LONG).show()
                                }

                            }
                    } else {

                        // Invalid Username or Password
                        Toast.makeText(baseContext, "Invalid Username and Password", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }

        bottomSheetDialog.show()
    }


}