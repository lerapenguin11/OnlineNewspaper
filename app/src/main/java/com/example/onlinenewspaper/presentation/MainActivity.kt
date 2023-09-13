package com.example.onlinenewspaper.presentation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import com.example.onlinenewspaper.R
import com.example.onlinenewspaper.databinding.ActivityMainBinding
import com.example.onlinenewspaper.utilits.APP_ACTIVITY
import com.example.onlinenewspaper.utilits.CheckInactiveWorker
import com.example.onlinenewspaper.utilits.replaceFragmentMain
import com.example.onlinenewspaper.utilits.setStatusBarGradiantMain
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val lastActiveTimeKey = "lastActiveTime"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        APP_ACTIVITY = this
        setStatusBarGradiantMain(this)
        setContentView(binding.root)

        replaceFragmentMain(HomeFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home -> replaceFragmentMain(HomeFragment())
                R.id.save -> replaceFragmentMain(SaveFragment())
            }
            true
        }

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val currentTimeString = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(
            Calendar.getInstance().time)
        editor.putString(lastActiveTimeKey, currentTimeString)
        editor.apply()

        val lastActiveTime = sharedPreferences.getString(lastActiveTimeKey, "")

        val inputData = workDataOf("lastActiveTime" to lastActiveTime)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresCharging(false)
            .build()

        val checkInactiveWorkRequest = PeriodicWorkRequestBuilder<CheckInactiveWorker>(1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .setInputData(inputData)
            .build()

        val workManager = WorkManager.getInstance(applicationContext)
        workManager.enqueueUniquePeriodicWork("CheckInactiveWork", ExistingPeriodicWorkPolicy.KEEP, checkInactiveWorkRequest)

        workManager.getWorkInfoByIdLiveData(checkInactiveWorkRequest.id)
            .observe(this, androidx.lifecycle.Observer { workInfo ->
                if (workInfo.state == WorkInfo.State.SUCCEEDED || workInfo.state == WorkInfo.State.FAILED){
                    val updatedLastActiveTimeString = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().time)
                    editor.putString(lastActiveTimeKey, updatedLastActiveTimeString)
                    editor.apply()
                }
            })
    }
}