package com.example.onlinenewspaper.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.onlinenewspaper.presentation.PoliticsFragment
import com.example.onlinenewspaper.presentation.TodayNewsFragment
import com.example.onlinenewspaper.presentation.TrendingFragment

class HomePagerAdapter(
    fragment : FragmentManager,
    lifecycle : Lifecycle
) : FragmentStateAdapter(fragment, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0){
            TrendingFragment()
        }
        else if (position == 1){
            TodayNewsFragment()
        } else{
            PoliticsFragment()
        }
    }
}