package com.example.onlinenewspaper.utilits

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class CheckInactiveWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val lastActiveTimeString = inputData.getString("lastActiveTime")
            val lastActiveTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(lastActiveTimeString)

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

    private fun sendNotification() {
        // Код для отправки уведомления пользователю
    }
}