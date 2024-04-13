package com.example.smsnotify.domain.useCase

import com.example.smsnotify.data.repository.LocalStorage.LocalStorageRepository
import com.example.smsnotify.data.repository.device.DeviceRepository
import com.example.smsnotify.domain.model.SMS

class SmsUseCaseImpl(
    val deviceRepository: DeviceRepository,
    val localStorageRepository: LocalStorageRepository,
) : SmsUseCase {
    override fun sendSMS(
        sms: SMS,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {

        deviceRepository.sendSms(sms.phoneNumber, sms.message, onSuccess, onError)
    }

    override fun receiveSMS(onReceive: (sms: SMS) -> Unit) {

        return deviceRepository.receiveSms(onReceive = { phoneNumber, message ->

            if (phoneNumber == localStorageRepository.getPhoneNumber())
                onReceive(SMS(phoneNumber, message))
        })
    }

    override fun savePhoneNumber(phoneNumber: String) {
        localStorageRepository.savePhoneNumber(phoneNumber)
    }


}