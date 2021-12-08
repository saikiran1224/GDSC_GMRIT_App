package com.gmrit.gdsc.activities.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.gmrit.gdsc.R
import com.gmrit.gdsc.activities.onboarding.OnBoarding_3
import com.gmrit.gdsc.models.StudentData
import com.gmrit.gdsc.utils.AppPreferences
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    lateinit var btnSignUp: CardView
    lateinit var txtWelcomeBack: TextView
    lateinit var txtLogin: TextView

    private lateinit var auth: FirebaseAuth

    lateinit var editName: TextInputEditText
    lateinit var editEmailID: TextInputEditText
    lateinit var editJNTUNo: TextInputEditText
    lateinit var editPassword: TextInputEditText
    lateinit var editConfirmPassword: TextInputEditText

    lateinit var edtName: TextInputLayout
    lateinit var edtEmailID: TextInputLayout
    lateinit var edtJNTUNo: TextInputLayout
    lateinit var edtPassword: TextInputLayout
    lateinit var edtConfirmPassword: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setting color of Status Bar
        window.statusBarColor = getColor(R.color.white)

        setContentView(R.layout.activity_sign_up)

        AppPreferences.init(this)

        btnSignUp = findViewById(R.id.btnSignUp)
        txtWelcomeBack = findViewById(R.id.txtWelcomeBack)
        txtLogin = findViewById(R.id.txtLogin)

        auth = FirebaseAuth.getInstance()

        edtName = findViewById(R.id.edtFullName)
        edtEmailID = findViewById(R.id.edtEmailID)
        edtJNTUNo = findViewById(R.id.edtJNTUNum)
        edtPassword = findViewById(R.id.edtPassword)
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword)

        editName = findViewById(R.id.editName)
        editEmailID = findViewById(R.id.editEmailID)
        editJNTUNo = findViewById(R.id.editJNTU)
        editPassword = findViewById(R.id.editPassword)
        editConfirmPassword = findViewById(R.id.editConfirmPassword)

        btnSignUp.setOnClickListener {

            val name = editName.text.toString().trim()
            val emailID = editEmailID.text.toString().trim()
            val JNTUNo = editJNTUNo.text.toString().trim()
            val password = editPassword.text.toString().trim()
            val confirmPassword = editConfirmPassword.text.toString().trim()

            // Validating the above parameters
            if(name.isEmpty() || name.length<2) {
                edtName.error = "Please enter valid Name"
            } else if(emailID.isEmpty() or !isValidEmail(emailID)) {
                edtName.error = ""
                edtEmailID.error = "Please enter valid Email ID"
            } else if(JNTUNo.isEmpty() || JNTUNo.length < 9) {
                edtEmailID.error = ""
                edtJNTUNo.error = "Please enter valid JNTU No."
            } else if(password.isEmpty() || password.length<6) {
                edtJNTUNo.error = ""
                edtPassword.error = "Please enter Password of minimum of 6 characters"
            } else if(confirmPassword.isEmpty() || confirmPassword.length<6) {
                edtPassword.error = ""
                edtConfirmPassword.error = "Please enter Confirm Password"
            } else if(password != confirmPassword) {
                Toast.makeText(this, "Password and Confirm Password should be the same", Toast.LENGTH_LONG).show()
            } else {
                edtConfirmPassword.error = ""

                // For Firebase Auth
                // passing Parameters to Firebase Create Email and Password Account
                auth.createUserWithEmailAndPassword(emailID, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser

                            // If the user successfully authenticated then only we are going to add his information into Cloud Firestore
                            // For Cloud Firestore
                            val db = Firebase.firestore
                            val userData = StudentData(name, emailID, JNTUNo)
                            db.collection("Students_Data")
                                .add(userData)
                                .addOnSuccessListener {

                                    Toast.makeText(this, "Welcome to GDSC GMRIT!", Toast.LENGTH_LONG).show()

                                    // setting Student Data globally to facilitate One time Login
                                    AppPreferences.isLogin = true
                                    AppPreferences.studentName = name
                                    AppPreferences.studentEmail = emailID
                                    AppPreferences.studentJNTUNo = JNTUNo

                                    val intent = Intent(this, WelcomeActivity::class.java)
                                    startActivity(intent)
                                }
                                .addOnFailureListener{
                                    Toast.makeText(this, "Some Error Occurred! Please try again after sometime.", Toast.LENGTH_LONG).show()
                                }

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(baseContext, "" + task.exception!!.localizedMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }

        // setting Spannable Text for the above Text Views

        val spannable_1 = SpannableStringBuilder("Welcome Back to GDSC")
        spannable_1.setSpan(ForegroundColorSpan(getColor(R.color.lightRed)), 16, 20, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        txtWelcomeBack!!.text = spannable_1

        val spannable_3 = SpannableStringBuilder("Already having an account? Login here")
        spannable_3.setSpan(ForegroundColorSpan(getColor(R.color.darkBlue)), 27, 37, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        spannable_3.setSpan(UnderlineSpan(), 27, 37, 0)
        txtLogin!!.text = spannable_3

        txtLogin.setOnClickListener {
            val intent = Intent(this, OnBoarding_3::class.java)
            startActivity(intent)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}