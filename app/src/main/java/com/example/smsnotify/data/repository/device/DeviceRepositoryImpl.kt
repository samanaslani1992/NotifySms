package com.example.smsnotify.data.repository.device

import android.telephony.SmsManager
import com.example.smsnotify.R
import com.example.smsnotify.presentation.App

class DeviceRepositoryImpl(val app: App) : DeviceRepository {
    override fun sendSms(
        phoneNumber: String,
        message: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {

        return try {
            val smsManager = app.getSystemService(SmsManager::class.java)

            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            onSuccess(app.getString(R.string.send_sms))
        } catch (e: Exception) {
            onError(e.message ?: app.getString(R.string.error_sending_sms))
        }


    }

  /*  override fun receiveSms(onReceive: (phoneNumber: String, message: String) -> Unit) {

    }*/
}