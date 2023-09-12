package com.example.onlinenewspaper.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.onlinenewspaper.R
import com.example.onlinenewspaper.databinding.FragmentHomeBinding
import com.example.onlinenewspaper.databinding.FragmentTrendingBinding
import com.example.onlinenewspaper.presentation.adapter.NewsAdapter
import com.example.onlinenewspaper.viewModel.HomeViewModel

class TrendingFragment : Fragment() {
    private var _binding : FragmentTrendingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : HomeViewModel
    private val adapter = NewsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTrendingBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        observeDataTrending()
        setTodayTrending()

        return binding.root
    }

    private fun setTodayTrending() {
        binding.tvTitleTodayTrendingNews.setText(R.string.trending_today_title)
        binding.tvTextNewsTrending.setText(R.string.trending_today_text)

        Glide.with(requireContext())
            .load("https://forpost-sz.ru/sites/default/files/doc/2021/05/27/ycvycvycvv.jpg")
            .into(binding.iconNews)
    }

    private fun observeDataTrending() {
        binding.rvTrending.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvTrending.adapter = adapter

        viewModel.getResultTrendingNews().observe(viewLifecycleOwner, Observer {
            adapter.setItem(it)
        })
    }
}