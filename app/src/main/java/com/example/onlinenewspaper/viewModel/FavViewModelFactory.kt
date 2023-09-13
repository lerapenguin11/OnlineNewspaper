package com.example.onlinenewspaper.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onlinenewspaper.business.model.repos.NewsRepository

class FavViewModelFactory constructor(private val repository: NewsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) : T {

        //super.create(modelClass)
        return return if (modelClass.isAssignableFrom(SaveViewModel::class.java)) {
            SaveViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}