package com.example.smsnotify.data.repository.LocalStorage

import android.content.Context
import android.content.SharedPreferences
import com.example.smsnotify.R
import com.example.smsnotify.presentation.App

class LocalStorageRepositoryImpl(val app: App) : LocalStorageRepository {


    private val PREF_NAME = "Prefs"
    private val KEY_PHONE_NUMBER = "PHONE_NUMBER"


    private val prefs: SharedPreferences =
        app.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)


    override fun savePhoneNumber(phoneNumber: String) {

        prefs.edit().putString(KEY_PHONE_NUMBER, phoneNumber).apply()
    }

    override fun getPhoneNumber(): String {
        val fakePhoneNumber = app.getString(R.string.fake_phone_number)
        return prefs.getString(KEY_PHONE_NUMBER, fakePhoneNumber) ?: fakePhoneNumber
    }
}