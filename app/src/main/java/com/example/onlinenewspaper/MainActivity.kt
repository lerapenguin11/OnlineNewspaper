package com.example.onlinenewspaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onlinenewspaper.databinding.ActivityMainBinding
import com.example.onlinenewspaper.utilits.APP_ACTIVITY
import com.example.onlinenewspaper.utilits.setStatusBarGradiantMain

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        APP_ACTIVITY = this
        setStatusBarGradiantMain(this)
        setContentView(binding.root)
    }
}