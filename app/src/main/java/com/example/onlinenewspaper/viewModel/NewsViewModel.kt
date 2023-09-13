package com.example.onlinenewspaper.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.onlinenewspaper.business.model.NewsModel
import com.example.onlinenewspaper.business.model.repos.NewsRepository

class NewsViewModel(private val repository: NewsRepository) : ViewModel(){

    val allNews: LiveData<List<NewsModel>> = repository.allNews

    suspend fun insertNews(news: NewsModel) {
        repository.insertNews(news)
    }

    suspend fun deleteNews(news: NewsModel) {
        repository.deleteNews(news)
    }

    fun isNewsFavorite(newsId: Int): LiveData<Boolean> {
        return repository.isNewsFavorite(newsId)
    }
}