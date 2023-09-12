package com.example.onlinenewspaper.business.model

import com.example.onlinenewspaper.R

object Constants {

    fun getTrendingNews() : MutableList<NewsModel>{
        val trendingNewsList = mutableListOf<NewsModel>()

        val news1 = NewsModel("https://cdnn21.img.ria.ru/images/07e7/07/0a/1883379316_0:320:3072:2048_1920x0_80_0_0_82a8292192b5a09b41e52932ff1cd55a.jpg.webp",
        R.string.trending_top_title1, R.string.trending_top_text1, R.string.trending_top_date1)
        trendingNewsList.add(news1)

        return trendingNewsList
    }

    fun getTodayNews() : MutableList<NewsModel>{
        val todayNewsList = mutableListOf<NewsModel>()

        val news1 = NewsModel("https://cdnn21.img.ria.ru/images/07e7/07/0a/1883379316_0:320:3072:2048_1920x0_80_0_0_82a8292192b5a09b41e52932ff1cd55a.jpg.webp",
            R.string.today_news_title1, R.string.today_news_text1, R.string.today_news_date1)
        todayNewsList.add(news1)

        return todayNewsList
    }

    fun getPoliticsNews() : MutableList<NewsModel>{
        val todayNewsList = mutableListOf<NewsModel>()

        val news1 = NewsModel("https://cdnn21.img.ria.ru/images/07e7/09/0b/1895670191_0:2:3072:1729_1920x0_80_0_0_5c2f68ec8b3a6cae58ea8580d9c7fadc.jpg.webp",
            R.string.politic_news_title1, R.string.politic_news_text1, R.string.politic_news_date1)
        todayNewsList.add(news1)

        return todayNewsList
    }
}