package com.example.onlinenewspaper.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinenewspaper.business.model.FavoriteModel
import com.example.onlinenewspaper.business.model.repos.NewsRepository
import kotlinx.coroutines.launch

class SaveViewModel(private val repository: NewsRepository) : ViewModel() {
    val allFavorites: LiveData<List<FavoriteModel>> = repository.allFavorites

    suspend fun insertFavorite(favorite: FavoriteModel) = viewModelScope.launch{
        repository.insertFavorite(favorite)
    }

    suspend fun deleteFavorite(favorite: FavoriteModel) {
        repository.deleteFavorite(favorite)
    }
}