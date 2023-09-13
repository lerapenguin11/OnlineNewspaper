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
import com.example.onlinenewspaper.business.database.AppDatabase
import com.example.onlinenewspaper.business.model.repos.NewsRepository
import com.example.onlinenewspaper.databinding.FragmentHomeBinding
import com.example.onlinenewspaper.databinding.FragmentSaveBinding
import com.example.onlinenewspaper.presentation.adapter.FavoriteAdapter
import com.example.onlinenewspaper.presentation.adapter.HomePagerAdapter
import com.example.onlinenewspaper.viewModel.FavViewModelFactory
import com.example.onlinenewspaper.viewModel.NewsViewModel
import com.example.onlinenewspaper.viewModel.NewsViewModelFactory
import com.example.onlinenewspaper.viewModel.SaveViewModel

class SaveFragment : Fragment() {
    private var _binding : FragmentSaveBinding? = null
    private val binding get() = _binding!!
    private val adapter = FavoriteAdapter()
    private lateinit var viewModelFav : SaveViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSaveBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dao = AppDatabase.getDatabase(application).newsDao()
        val favoriteDao = AppDatabase.getDatabase(application).favoriteDao()
        val repository = NewsRepository(dao, favoriteDao)

        val viewModelFactoryFav = FavViewModelFactory(repository)
        viewModelFav = ViewModelProvider(this, viewModelFactoryFav).get(SaveViewModel::class.java)

        observeDataSave()

        return binding.root
    }

    private fun observeDataSave() {
        binding.rvTrending.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvTrending.adapter = adapter

        viewModelFav.allFavorites.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                // on below line we are updating our list.
                adapter.setItem(it)
            }
        })
    }
}