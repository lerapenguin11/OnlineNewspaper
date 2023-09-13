package com.example.onlinenewspaper.presentation.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onlinenewspaper.R
import com.example.onlinenewspaper.databinding.FragmentSecondBinding
import com.example.onlinenewspaper.utilits.replaceFragmentOnBoarding

class SecondFragment : Fragment() {
    private var _binding : FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.btNext.setOnClickListener { replaceFragmentOnBoarding(SecondFragment()) }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}