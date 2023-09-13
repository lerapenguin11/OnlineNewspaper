package com.example.onlinenewspaper.business.model.repos

import androidx.lifecycle.LiveData
import com.example.onlinenewspaper.business.database.FavoriteDao
import com.example.onlinenewspaper.business.database.NewsDao
import com.example.onlinenewspaper.business.model.FavoriteModel
import com.example.onlinenewspaper.business.model.NewsModel
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(private val newsDao: NewsDao, private val favoriteDao: FavoriteDao) {
    val allNews: LiveData<List<NewsModel>> = newsDao.getAllNews()
    val allFavorites: LiveData<List<FavoriteModel>> = favoriteDao.getAllFavorites()

    suspend fun insertNews(news: NewsModel) {
        newsDao.insertNews(news)
    }

    suspend fun deleteNews(news: NewsModel) {
        newsDao.deleteNews(news)
    }

    suspend fun insertFavorite(favorite: FavoriteModel) {
        withContext(Dispatchers.IO) {
            favoriteDao.insertFavorite(favorite)
        }
    }

    suspend fun deleteFavorite(favorite: FavoriteModel) {
        favoriteDao.deleteFavorite(favorite)
    }

    fun isNewsFavorite(newsId: Int): LiveData<Boolean> {
        return allFavorites.map { favorites ->
            favorites.any { it.newsId == newsId }
        }
    }
}