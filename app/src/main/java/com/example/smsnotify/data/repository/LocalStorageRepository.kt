package com.example.smsnotify.data.repository

interface LocalStorageRepository {
    fun savePhoneNumber(phoneNumber: String)
    fun getPhoneNumber(): String

}