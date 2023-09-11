package com.example.onlinenewspaper.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onlinenewspaper.R
import com.example.onlinenewspaper.databinding.ActivityMainBinding
import com.example.onlinenewspaper.utilits.APP_ACTIVITY
import com.example.onlinenewspaper.utilits.replaceFragmentMain
import com.example.onlinenewspaper.utilits.setStatusBarGradiantMain

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
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
    }
}