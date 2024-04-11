package com.example.smsnotify.presenter.screen.reciveSms

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.smsnotify.R
import kotlin.random.Random

class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.provider.Telephony.SMS_RECEIVED") {
            val bundle = intent.extras
            if (bundle != null) {
                val pdus = bundle.get("pdus") as Array<*>
                val messages = arrayOfNulls<SmsMessage>(pdus.size)
                for (i in pdus.indices) {
                    val format = bundle.getString("format")
                    messages[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray, format)

                }
                val smsMessage = messages[0] // Assuming a single message
                val sender = smsMessage?.originatingAddress
                val messageBody = smsMessage?.messageBody

                Toast.makeText(context, "Sender: $sender\nMessage: $messageBody", Toast.LENGTH_LONG)
                    .show()
                // For example, you can send a broadcast, start a service, etc.
                showNotification(
                    context,
                    sender.toString(),
                    messageBody.toString(),
                )
            }
        }
    }
}

fun showNotification(context: Context, title: String, message: String) {

    val notificationManager = ContextCompat.getSystemService(
        context,
        NotificationManager::class.java
    ) as NotificationManager

    val notificationId = Random.nextInt()

    val builder = NotificationCompat.Builder(context, context.packageName)
        .setContentTitle(title)
        .setContentText(message)
        .setSmallIcon(R.drawable.baseline_sms_24)

    createNotificationChannel(context)
    notificationManager.notify(notificationId, builder.build())
}

private fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            context.packageName,
            context.getString(R.string.app_name),
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager: NotificationManager =
            context.getSystemService(NotificationManager::class.java) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

