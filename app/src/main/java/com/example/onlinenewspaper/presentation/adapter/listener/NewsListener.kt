package com.example.onlinenewspaper.presentation.adapter.listener

import com.example.onlinenewspaper.business.model.NewsModel

interface NewsListener {

    fun getDetailsNews(new : NewsModel)
}