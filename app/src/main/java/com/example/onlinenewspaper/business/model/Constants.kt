package com.example.onlinenewspaper.business.model

import com.example.onlinenewspaper.R

object Constants {

    fun getTrendingNews() : MutableList<NewsModel>{
        val trendingNewsList = mutableListOf<NewsModel>()

        val news1 = NewsModel(0, "https://cdnn21.img.ria.ru/images/07e7/07/0a/1883379316_0:320:3072:2048_1920x0_80_0_0_82a8292192b5a09b41e52932ff1cd55a.jpg.webp",
        R.string.trending_top_title1, R.string.trending_top_text1, R.string.trending_top_date1)
        trendingNewsList.add(news1)

        val news2 = NewsModel(3, "https://cdnn21.img.ria.ru/images/07e5/05/15/1733238966_0:0:3220:1811_1920x0_80_0_0_2ab05185fe59d3cc755288ed855e34aa.jpg.webp",
            R.string.trending_top_title2, R.string.trending_top_text2, R.string.trending_top_date2)
        trendingNewsList.add(news2)

        val news3 = NewsModel(4, "https://cdnn21.img.ria.ru/images/07e7/09/0c/1895863529_0:153:2933:1803_1920x0_80_0_0_0d45541658f43480bfec9f3a664c5861.jpg.webp",
            R.string.trending_top_title3, R.string.trending_top_text3, R.string.trending_top_date3)
        trendingNewsList.add(news3)

        return trendingNewsList
    }

    fun getTodayNews() : MutableList<NewsModel>{
        val todayNewsList = mutableListOf<NewsModel>()

        val news1 = NewsModel(1, "https://cdnn21.img.ria.ru/images/07e7/07/0a/1883379316_0:320:3072:2048_1920x0_80_0_0_82a8292192b5a09b41e52932ff1cd55a.jpg.webp",
            R.string.today_news_title1, R.string.today_news_text1, R.string.today_news_date1)
        todayNewsList.add(news1)

        val news2 = NewsModel(5, "https://cdnn21.img.ria.ru/images/07e7/08/02/1887776552_0:162:3225:1976_1920x0_80_0_0_497a0c72d01ee7173cf77678ce3830e0.jpg.webp",
            R.string.today_news_title2, R.string.today_news_text2, R.string.today_news_date2)
        todayNewsList.add(news2)

        val news3 = NewsModel(6, "https://cdnn21.img.ria.ru/images/147318/67/1473186743_0:280:3000:1968_1920x0_80_0_0_d0cb888102789017768e58a3e4d89e54.jpg.webp",
            R.string.today_news_title3, R.string.today_news_text3, R.string.today_news_date3)
        todayNewsList.add(news3)

        return todayNewsList
    }

    fun getPoliticsNews() : MutableList<NewsModel>{
        val todayNewsList = mutableListOf<NewsModel>()

        val news1 = NewsModel(2, "https://cdnn21.img.ria.ru/images/07e7/09/0b/1895670191_0:2:3072:1729_1920x0_80_0_0_5c2f68ec8b3a6cae58ea8580d9c7fadc.jpg.webp",
            R.string.politic_news_title1, R.string.politic_news_text1, R.string.politic_news_date1)
        todayNewsList.add(news1)

        val news2 = NewsModel(7, "https://cdnn21.img.ria.ru/images/07e7/09/05/1894242083_0:320:1122:951_1920x0_80_0_0_3e80f433576054014a089326f6fc9f16.jpg.webp",
            R.string.politic_news_title2, R.string.politic_news_text2, R.string.politic_news_date2)
        todayNewsList.add(news2)

        val news3 = NewsModel(8, "https://cdnn21.img.ria.ru/images/07e7/01/15/1846441394_660:28:2457:1039_1920x0_80_0_0_f6de5a068d7e92cf5a4fd034b1f8d94d.jpg.webp",
            R.string.politic_news_title3, R.string.politic_news_text3, R.string.politic_news_date3)
        todayNewsList.add(news3)

        val news4 = NewsModel(9, "https://cdnn21.img.ria.ru/images/07e5/0b/01/1757264686_0:199:3071:1927_1920x0_80_0_0_333218ca7c4326d8391645ad378c521d.jpg.webp",
            R.string.politic_news_title4, R.string.politic_news_text4, R.string.politic_news_date4)
        todayNewsList.add(news4)

        return todayNewsList
    }
}