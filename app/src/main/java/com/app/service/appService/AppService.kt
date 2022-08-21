package com.app.service.appService

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.lang.Exception
import kotlin.concurrent.thread

private const val TAG = "AppService***"
class AppService : Service() {

    private var thread :Thread?=null
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        thread = Thread {
            var i = 0
            while (i<400) {
                i++
                Thread.sleep(500)
                Log.d(TAG, "onStartCommand: $startId , $i}")
            }
        }
        thread?.start()
        return super.onStartCommand(intent, flags, startId)
    }


    override fun onBind(p0: Intent?): IBinder? {
        return null;
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: service is destroyed!!")
    }

}