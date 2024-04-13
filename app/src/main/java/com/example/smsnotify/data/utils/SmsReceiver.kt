package com.example.smsnotify.data.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsMessage
import com.example.smsnotify.utils.showNotification

class SmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.provider.Telephony.SMS_RECEIVED") {
            val messages = getSmsMessages(intent.extras)
            val firstMessage = messages.firstOrNull()

            if (firstMessage != null) {
                val sender = firstMessage.originatingAddress ?: ""
                val messageBody = firstMessage.messageBody
                showNotification(context, sender, messageBody)
            }
        }
    }

    private fun getSmsMessages(extras: Bundle?): Array<SmsMessage> {
        if (extras == null) {
            return emptyArray()
        }

        val pdus = extras.get("pdus") as Array<*>
        val messages = arrayOfNulls<SmsMessage>(pdus.size)
        val format = extras.getString("format")

        for (i in pdus.indices) {
            messages[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray, format)
        }

        return messages.filterNotNull().toTypedArray()
    }
}
