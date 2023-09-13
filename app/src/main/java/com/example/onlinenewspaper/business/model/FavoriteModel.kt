package com.example.onlinenewspaper.business.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "newsId")
    val newsId: Int,
    @ColumnInfo(name = "icon")
    val icon : String,
    @ColumnInfo(name = "title")
    val title : Int,
    @ColumnInfo(name = "text")
    val text : Int,
    @ColumnInfo(name = "date")
    val date : Int
)
