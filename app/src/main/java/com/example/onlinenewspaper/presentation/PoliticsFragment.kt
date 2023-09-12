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
import com.example.onlinenewspaper.databinding.FragmentPoliticsBinding
import com.example.onlinenewspaper.databinding.FragmentTodayNewsBinding
import com.example.onlinenewspaper.presentation.adapter.NewsAdapter
import com.example.onlinenewspaper.viewModel.HomeViewModel

class PoliticsFragment : Fragment() {
    private var _binding : FragmentPoliticsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : HomeViewModel
    private val adapter = NewsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPoliticsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        observeDataPolitics()
        setPoliticsNews()

        return binding.root
    }

    private fun setPoliticsNews() {
        binding.tvTitleTodayTrendingNews.setText(R.string.politics_news_today_title)
        binding.tvTextNewsTrending.setText(R.string.politics_news_today_text)

        Glide.with(requireContext())
            .load("https://cdnn21.img.ria.ru/images/07e7/09/0b/1895530756_0:48:3221:1860_1920x0_80_0_0_223b48a6e016dbad47217a2a431d34d5.jpg.webp")
            .into(binding.iconNews)
    }

    private fun observeDataPolitics() {
        binding.rvTrending.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvTrending.adapter = adapter

        viewModel.getResultPoliticsNews().observe(viewLifecycleOwner, Observer {
            adapter.setItem(it)
        })
    }

}