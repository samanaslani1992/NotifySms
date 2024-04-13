package com.example.smsnotify.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.smsnotify.R
import kotlin.random.Random


fun showNotification(context: Context, title: String, message: String) {


    createNotificationChannel(context)

    val notificationManager = getNotificationManager(context)
    val notificationId = Random.nextInt()


    val builder = NotificationCompat.Builder(context, context.packageName)
        .setContentTitle(title)
        .setContentText(message)
        .setSmallIcon(R.drawable.baseline_sms_24)

    notificationManager.notify(notificationId, builder.build())
}

private fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelName = context.getString(R.string.sms_notify_channel)
        val channelDescription = context.getString(R.string.channel_for_sms_notifications)

        val importance = NotificationManager.IMPORTANCE_DEFAULT

        val channel = NotificationChannel(context.packageName, channelName, importance).apply {
            description = channelDescription
        }

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }
}

private fun getNotificationManager(context: Context): NotificationManager {
    return ContextCompat.getSystemService(
        context, NotificationManager::class.java
    ) as NotificationManager
}