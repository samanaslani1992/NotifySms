package com.example.smsnotify.domain.useCase

import com.example.smsnotify.domain.model.SMS

interface SmsUseCase {

    fun sendSMS(sms: SMS): Boolean

    fun receiveSMS(onReceive: (sms: SMS) -> Unit)
    fun savePhoneNumber(phoneNumber: String)


}