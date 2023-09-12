package com.example.onlinenewspaper.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlinenewspaper.R
import com.example.onlinenewspaper.databinding.FragmentTodayNewsBinding
import com.example.onlinenewspaper.databinding.FragmentTrendingBinding
import com.example.onlinenewspaper.presentation.adapter.NewsAdapter
import com.example.onlinenewspaper.viewModel.HomeViewModel

class TodayNewsFragment : Fragment() {
    private var _binding : FragmentTodayNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : HomeViewModel
    private val adapter = NewsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTodayNewsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        observeDataToday()

        return binding.root
    }

    private fun observeDataToday() {
        binding.rvTrending.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvTrending.adapter = adapter

        viewModel.getResultTodayNews().observe(viewLifecycleOwner, Observer {
            adapter.setItem(it)
        })
    }
}