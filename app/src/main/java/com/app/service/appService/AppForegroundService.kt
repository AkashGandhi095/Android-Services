package com.app.service.appService

import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.app.service.R
import kotlin.math.log

private const val TAG = "ForegroundService***"
class AppForegroundService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: called")
        createNotification()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread {
            var i=0;
            while (i<400) {
                Log.d(TAG, "onStartCommand: $startId , $i")
                i++;
                Thread.sleep(500)
            }
        }.start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null;
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: stopForegroundService")
    }


    private fun createNotification() {
        val builder = NotificationCompat.Builder(this , "service_channel")
            .setSmallIcon(android.R.drawable.alert_dark_frame)
            .setContentTitle("Foreground Service")
            .setContentTitle("App Foreground Service is running...")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        this.startForeground(101 , builder.build())

        /** below code create simple notification if we use still show crash **/
        //NotificationManagerCompat.from(this).notify(101 , builder.build())

    }


}