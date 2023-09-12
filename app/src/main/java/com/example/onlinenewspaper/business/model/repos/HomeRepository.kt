package com.example.onlinenewspaper.business.model.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onlinenewspaper.business.model.NewsModel

class HomeRepository {

    fun getNewsListReading(news: MutableList<NewsModel>): LiveData<MutableList<NewsModel>> {
        val mutableData = MutableLiveData<MutableList<NewsModel>>()
        val list = mutableListOf<NewsModel>()

        for (i in news){
            val icon = i.icon
            val title = i.title
            val text = i.text
            val date = i.date

            val listNewsModel = NewsModel(
                icon = icon, title = title,
                text = text, date = date
            )
            list.add(listNewsModel)
        }

        mutableData.value = list

        return mutableData
    }
}