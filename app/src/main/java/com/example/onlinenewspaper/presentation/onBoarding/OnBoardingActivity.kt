package com.example.onlinenewspaper.presentation.onBoarding

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.work.*
import com.example.onlinenewspaper.R
import com.example.onlinenewspaper.presentation.MainActivity
import com.example.onlinenewspaper.utilits.APP_ACTIVITY_ONBOARDING
import com.example.onlinenewspaper.utilits.CheckInactiveWorker
import com.example.onlinenewspaper.utilits.setStatusBarGradiantOnBoarding
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class OnBoardingActivity : AppCompatActivity() {
    private val lastActiveTimeKey = "lastActiveTime"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        APP_ACTIVITY_ONBOARDING = this
        setStatusBarGradiantOnBoarding(this)
        setViewPager()

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

        val checkInactiveWorkRequest = PeriodicWorkRequestBuilder<CheckInactiveWorker>(1, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .setInputData(inputData)
            .build()

        val workManager = WorkManager.getInstance(applicationContext)
        workManager.enqueueUniquePeriodicWork("CheckInactiveWork", ExistingPeriodicWorkPolicy.KEEP, checkInactiveWorkRequest)

        workManager.getWorkInfoByIdLiveData(checkInactiveWorkRequest.id)
            .observe(this, androidx.lifecycle.Observer { workInfo ->
                if (workInfo.state == WorkInfo.State.SUCCEEDED || workInfo.state == WorkInfo.State.FAILED){
                    val updatedLastActiveTimeString = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(
                        Calendar.getInstance().time)
                    editor.putString(lastActiveTimeKey, updatedLastActiveTimeString)
                    editor.apply()
                }
            })
    }

    private fun setViewPager() {
        val fragList = ArrayList<Fragment>()
        fragList.add(ThirstFragment())
        fragList.add(SecondFragment())
        fragList.add(ThirdFragment())

        val adapterViewPager = OnBoardingNewAdapter(
            fragList,
            this.supportFragmentManager,
            lifecycle
        )
        val close = findViewById<ImageView>(R.id.finish)

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        viewPager.adapter = adapterViewPager
        val indicator = findViewById<DotsIndicator>(R.id.dots_indicator)
        indicator.attachTo(viewPager)

        close.setOnClickListener { v: View? ->
            val intent = Intent(
                this@OnBoardingActivity,
                MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    @SuppressLint("NotificationPermission")
    fun showNotificationInStatusBar() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager?

        // Создание канала уведомлений (для Android 8.0 и выше)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "my_channel_id"
            val channelName = "My Channel"
            val channelDescription = "Description of My Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(channelId, channelName, importance)
            channel.description = channelDescription

            notificationManager!!.createNotificationChannel(channel)
        }

        // Создание уведомления
        val notificationBuilder = NotificationCompat.Builder(applicationContext, "my_channel_id")
            .setSmallIcon(R.drawable.ic_home)
            .setContentTitle("Notification Title")
            .setContentText("Notification Text")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Отображение уведомления
        notificationManager!!.notify(notificationId, notificationBuilder.build())
    }

    companion object {
        private const val notificationId = 1
    }
}