package com.example.smsnotify.data.repository.device

interface DeviceRepository {

    fun sendSms(
        phoneNumber: String,
        message: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    )

    fun receiveSms(onReceive: (phoneNumber: String, message: String) -> Unit)
}