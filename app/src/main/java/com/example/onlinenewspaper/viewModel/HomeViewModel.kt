package com.example.onlinenewspaper.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlinenewspaper.business.model.Constants
import com.example.onlinenewspaper.business.model.NewsModel
import com.example.onlinenewspaper.business.model.repos.HomeRepository

class HomeViewModel : ViewModel(){

    private val repository  = HomeRepository()

    fun getResultTrendingNews() : LiveData<MutableList<NewsModel>> {
        val mutableData = MutableLiveData<MutableList<NewsModel>>()
        repository.getNewsListReading(Constants.getTrendingNews()).observeForever { list ->
            mutableData.value = list
        }

        return mutableData
    }
}