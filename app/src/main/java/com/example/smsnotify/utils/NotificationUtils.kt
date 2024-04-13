package com.example.smsnotify.utils

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.smsnotify.R
import kotlin.random.Random


fun showNotification(context: Context, title: String, message: String) {
    val notificationManager = getNotificationManager(context)
    val notificationId = Random.nextInt()

    val builder = NotificationCompat.Builder(context, context.packageName).setContentTitle(title)
        .setContentText(message).setSmallIcon(R.drawable.baseline_sms_24)

    notificationManager.notify(notificationId, builder.build())
}

private fun getNotificationManager(context: Context): NotificationManager {
    return ContextCompat.getSystemService(
        context, NotificationManager::class.java
    ) as NotificationManager
}