package com.example.onlinenewspaper.business.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.onlinenewspaper.business.model.NewsModel

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAllNews(): LiveData<List<NewsModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: NewsModel)

    @Delete
    fun deleteNews(news: NewsModel)
}