package com.app.service

import android.app.IntentService
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.app.service.appIntentService.AppIntentServiceClass
import com.app.service.appIntentService.ForegroundIntentService
import com.app.service.databinding.ActivityIntentServiceBinding

class IntentServiceActivity : AppCompatActivity() {
    private lateinit var binding :ActivityIntentServiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntentServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Manage background service
        binding.serviceBtn.setOnClickListener {
            startBackgroundIntentService()
        }


        // Manage foreground service
        binding.foregroundServiceBtn.setOnClickListener {
            startForegroundIntentService()
        }


        binding.startBtn.setOnClickListener {
            Intent(this , MainActivity ::class.java).also { startActivity(it) }
        }

    }

    private fun startBackgroundIntentService() {
        val intentService = Intent(this , AppIntentServiceClass ::class.java)
        startService(intentService)
    }

    private fun startForegroundIntentService() {
        val intent = Intent(this , ForegroundIntentService ::class.java)
        ContextCompat.startForegroundService(this , intent)
    }


}