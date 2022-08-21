package com.app.service.appIntentService

import android.annotation.SuppressLint
import android.app.IntentService
import android.content.Intent
import android.util.Log
import kotlin.math.log

private const val TAG = "IntentServiceClass***"

/**
 * this is a background service using IntentService class.
 * this service destroyed when the app is terminated or cleared from backstack.
 */
class AppIntentServiceClass() : IntentService("AppIntentServiceClass") {

    override fun onCreate() {
        super.onCreate()
        // for setup notification & resources
        Log.d(TAG, "onCreate: intent service")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // this function runs on main thread
        // if we add any long running operation here it gives ANR crash
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onHandleIntent(p0: Intent?) {
        // handle long running operation in a worker thread.
        var i=0;
        while (i<200) {
            Log.d(TAG, "onHandleIntent: $i")
            i++
            Thread.sleep(200)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: destroyed intent service!!")
        // to clear notification & resources
    }

}