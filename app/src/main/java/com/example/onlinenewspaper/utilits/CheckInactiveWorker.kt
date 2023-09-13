package com.example.onlinenewspaper.utilits

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.onlinenewspaper.R
import com.example.onlinenewspaper.presentation.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*


class CheckInactiveWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val lastActiveTimeString = inputData.getString("lastActiveTime")
            val lastActiveTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(
                lastActiveTimeString!!
            )

            val currentTime = Calendar.getInstance().time

            val timeDifference = currentTime.time - lastActiveTime.time

            val minutesInactive = timeDifference / (1000 * 60)

            if (minutesInactive >= 30) {
                sendNotification()
            }

            Result.success()
        } catch (e: Exception) {
            Log.e("CheckInactiveWorker", "Error checking inactivity", e)
            Result.failure()
        }
    }

    fun sendNotification() {
        APP_ACTIVITY.showNotificationInStatusBar()
        APP_ACTIVITY_ONBOARDING.showNotificationInStatusBar()
    }
}