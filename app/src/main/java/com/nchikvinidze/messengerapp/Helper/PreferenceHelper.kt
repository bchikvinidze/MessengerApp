package com.nchikvinidze.messengerapp.Helper
import android.content.Context

class PreferenceHelper(context: Context)
{
    private val User_NickName = "userNickName"
    private var User_Details = "userDetails"

    private val preferences = context.getSharedPreferences(User_Details, Context.MODE_PRIVATE)

    var userName: String? = null
        get() = preferences.getString(User_NickName, null)
        set(value) {
            preferences.edit().putString(User_NickName, value).commit()
            field = value
        }
}