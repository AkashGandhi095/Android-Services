package com.app.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.app.service.appService.AppForegroundService
import com.app.service.appService.AppService
import com.app.service.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //createNotificationChannel()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.serviceBtn.setOnClickListener {
            startBackgroundService()
        }

        binding.startBtn.setOnClickListener {
            startActivity(Intent(this , MainActivity ::class.java))
        }

        binding.foregroundServiceBtn.setOnClickListener {
            startingForegroundService()
        }

        binding.stopBackgroundBtn.setOnClickListener {
            stopBackground()
        }

        binding.stopForegroundBtn.setOnClickListener {
            stopForegroundService()
        }

    }

    private fun startBackgroundService() {
        val intent = Intent(this , AppService ::class.java);
        intent.putExtra("time" , 100)
        startService(intent)
    }

    private fun startingForegroundService() {
        val intent = Intent(this , AppForegroundService::class.java)
        intent.putExtra("time" , 100)
        ContextCompat.startForegroundService(this , intent)
    }

    private fun stopBackground() {
        val intent = Intent(this , AppService::class.java)
        intent.putExtra("time" , 100)
        stopService(intent)
    }

    private fun stopForegroundService() {
        val intent = Intent(this , AppForegroundService::class.java)
        intent.putExtra("time" , 100)
        stopService(intent)
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