package com.example.onlinenewspaper.presentation.onBoarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoardingNewAdapter(arList : ArrayList<Fragment>, fraManger : FragmentManager, lffc : Lifecycle)
    : FragmentStateAdapter(fraManger, lffc) {

    private val fragmentListOnBoarding = arList

    override fun getItemCount(): Int = fragmentListOnBoarding.size

    override fun createFragment(position: Int): Fragment = fragmentListOnBoarding[position]
}