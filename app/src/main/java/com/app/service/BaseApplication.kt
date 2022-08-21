package com.app.service

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.widget.Toast

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "Base Application Created!!", Toast.LENGTH_SHORT).show()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val descriptionText = "Service app"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("service_channel", "ServiceApp", importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}