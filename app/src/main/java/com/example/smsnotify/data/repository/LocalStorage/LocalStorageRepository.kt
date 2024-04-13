package com.example.smsnotify.data.repository.LocalStorage

interface LocalStorageRepository {
    fun savePhoneNumber(phoneNumber: String)
    fun getPhoneNumber(): String

}