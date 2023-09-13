package com.example.onlinenewspaper.business.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.onlinenewspaper.business.model.FavoriteModel

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): LiveData<List<FavoriteModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: FavoriteModel)

    @Delete
    fun deleteFavorite(favorite: FavoriteModel)
}