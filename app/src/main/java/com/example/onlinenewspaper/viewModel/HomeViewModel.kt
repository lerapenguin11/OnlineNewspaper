package com.example.onlinenewspaper.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlinenewspaper.business.database.AppDatabase
import com.example.onlinenewspaper.business.database.NewsDao
import com.example.onlinenewspaper.business.model.Constants
import com.example.onlinenewspaper.business.model.NewsModel
import com.example.onlinenewspaper.business.model.repos.HomeRepository
import com.example.onlinenewspaper.business.model.repos.NewsRepository

class HomeViewModel() : ViewModel() {

    private val repository = HomeRepository()

    fun getResultTrendingNews(): LiveData<MutableList<NewsModel>> {
        val mutableData = MutableLiveData<MutableList<NewsModel>>()
        repository.getNewsListReading(Constants.getTrendingNews()).observeForever { list ->
            mutableData.value = list
        }

        return mutableData
    }

    fun getResultTodayNews(): LiveData<MutableList<NewsModel>> {
        val mutableData = MutableLiveData<MutableList<NewsModel>>()
        repository.getNewsListReading(Constants.getTodayNews()).observeForever { list ->
            mutableData.value = list
        }

        return mutableData
    }

    fun getResultPoliticsNews(): LiveData<MutableList<NewsModel>> {
        val mutableData = MutableLiveData<MutableList<NewsModel>>()
        repository.getNewsListReading(Constants.getPoliticsNews()).observeForever { list ->
            mutableData.value = list
        }

        return mutableData
    }
}