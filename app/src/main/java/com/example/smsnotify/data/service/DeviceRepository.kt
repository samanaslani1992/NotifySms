package com.example.smsnotify.data.service

interface DeviceRepository {

    fun sendSms(phoneNumber: String, message: String): Boolean
    fun receiveSms(onReceive: (phoneNumber: String, message: String) -> Unit)
}