package com.example.smsnotify.domain.useCase

import com.example.smsnotify.data.repository.LocalStorageRepository
import com.example.smsnotify.data.service.DeviceRepository
import com.example.smsnotify.domain.model.SMS

class SmsUseCaseImpl(
    val deviceRepository: DeviceRepository,
    val localStorageRepository: LocalStorageRepository,
    ) : SmsUseCase {
    override fun sendSMS(sms: SMS): Boolean {

        return deviceRepository.sendSms(sms.phoneNumber, sms.message)
    }

    override fun receiveSMS(onReceive: (sms: SMS) -> Unit) {

        return deviceRepository.receiveSms(onReceive = { phoneNumber, message ->

            if (phoneNumber.equals(localStorageRepository.getPhoneNumber()))
                onReceive(SMS(phoneNumber, message))
        })
    }

    override fun savePhoneNumber(phoneNumber: String) {
        localStorageRepository.savePhoneNumber(phoneNumber)
    }


}