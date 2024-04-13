package com.example.smsnotify.domain.useCase

import android.util.Log
import com.example.smsnotify.data.repository.LocalStorage.LocalStorageRepository
import com.example.smsnotify.data.repository.device.DeviceRepository
import com.example.smsnotify.domain.model.SMS

class SmsUseCaseImpl(
    val deviceRepository: DeviceRepository,
    val localStorageRepository: LocalStorageRepository,
) : SmsUseCase {
    override fun sendSMS(
        sms: SMS, onSuccess: (String) -> Unit, onError: (String) -> Unit
    ) {

        deviceRepository.sendSms(sms.phoneNumber, sms.message, onSuccess, onError)
    }

    override fun smsReceive(receivedSms: SMS, onReceive: () -> Unit) {


        if (receivedSms.phoneNumber == localStorageRepository.getPhoneNumber())
            onReceive()

    }


    override fun savePhoneNumber(phoneNumber: String) {
        localStorageRepository.savePhoneNumber(phoneNumber)
    }

    override fun getPhoneNumber(): String {
        return localStorageRepository.getPhoneNumber()
    }
}