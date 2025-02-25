package com.example.smsnotify.domain.useCase

import com.example.smsnotify.domain.model.SMS

interface SmsUseCase {

    fun sendSMS(sms: SMS, onSuccess: (String) -> Unit, onError: (String) -> Unit)

    fun smsReceive(receivedSms: SMS, onReceive: ( ) -> Unit)

    fun savePhoneNumber(phoneNumber: String)
    fun getPhoneNumber(): String


}