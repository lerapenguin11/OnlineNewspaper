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
}