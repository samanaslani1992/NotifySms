package com.example.smsnotify.data.repository.LocalStorage

import com.example.smsnotify.presentation.App

class LocalStorageRepositoryImpl(app: App) : LocalStorageRepository {
    override fun savePhoneNumber(phoneNumber: String) {


    }

    override fun getPhoneNumber(): String {
        return "+98123456789"
    }
}