package com.example.onlinenewspaper.presentation.onBoarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onlinenewspaper.R
import com.example.onlinenewspaper.databinding.FragmentThirdBinding
import com.example.onlinenewspaper.databinding.FragmentThirstBinding
import com.example.onlinenewspaper.presentation.MainActivity

class ThirdFragment : Fragment() {
    private var _binding : FragmentThirdBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.btNext.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        binding.btNext
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
    }
}