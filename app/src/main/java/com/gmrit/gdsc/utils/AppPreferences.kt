package com.gmrit.gdsc.utils

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast

object AppPreferences {

    private const val NAME = "GDSC GMRIT"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    //SharedPreferences variables
    private val IS_LOGIN = Pair("is_login", false)

    private val STUD_NAME = Pair("studentName", "")
    private val STUD_JNTU_NO = Pair("studentJNTUNo", "")
    private val STUD_EMAIL_ID = Pair("studentEmailID", "")

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }


    //an inline function to put variable and save it
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    fun showToast(context: Context, message: String?) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }

    //SharedPreferences variables getters/setters
    var isLogin: Boolean?
        get() = preferences.getBoolean(IS_LOGIN.first, IS_LOGIN.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_LOGIN.first, value!!)
        }

    var studentName: String?
        get() = preferences.getString(STUD_NAME.first, STUD_NAME.second)
        set(value) = preferences.edit {
            it.putString(STUD_NAME.first, value)
        }

    var studentJNTUNo: String?
        get() = preferences.getString(STUD_JNTU_NO.first, STUD_JNTU_NO.second)
        set(value) = preferences.edit {
            it.putString(STUD_JNTU_NO.first, value)
        }

    var studentEmail: String?
        get() = preferences.getString(STUD_EMAIL_ID.first, STUD_EMAIL_ID.second)
        set(value) = preferences.edit {
            it.putString(STUD_EMAIL_ID.first, value)
        }
}