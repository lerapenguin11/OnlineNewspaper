package com.example.onlinenewspaper.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.onlinenewspaper.R
import com.example.onlinenewspaper.databinding.FragmentHomeBinding
import com.example.onlinenewspaper.presentation.adapter.HomePagerAdapter
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : HomePagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        adapter = HomePagerAdapter(requireFragmentManager(), lifecycle)

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.page1))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.page2))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.page3))

        binding.viewPager2.adapter = adapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

        return binding.root
    }
}