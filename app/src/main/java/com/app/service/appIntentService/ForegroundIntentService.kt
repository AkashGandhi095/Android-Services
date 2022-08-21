package com.app.service.appIntentService

import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

private const val TAG = "ForegroundIntentService***"
/**
 * this is a foreground service using IntentService class.
 * this service destroyed (automatically) itself when the task is completed.
 */
class ForegroundIntentService :IntentService("AppForegroundIntentServiceClass") {

    override fun onCreate() {
        super.onCreate()
        // setup notification or resources
        createNotification()
    }

    override fun onHandleIntent(intent: Intent?) {
        var i = 0;
        while (i<200) {
            Log.d(TAG, "onHandleIntent: foregroundService running.. $i")
            i++
            Thread.sleep(200)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: foreground service is completed or destroyed!!")
    }


    private fun createNotification() {
        val notification = NotificationCompat.Builder(this , "service_channel")
            .setContentTitle("Intent Service")
            .setContentText("Intent service is running...")
            .setSmallIcon(android.R.drawable.presence_invisible)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        startForeground(1001 , notification)

    }
}