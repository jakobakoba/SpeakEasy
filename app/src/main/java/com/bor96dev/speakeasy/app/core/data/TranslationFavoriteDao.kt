package com.bor96dev.speakeasy.app.core.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TranslationFavoriteDao {

    @Insert
    suspend fun saveFavorite(favorite: TranslationFavorite)

    @Query("SELECT * FROM translation_favorite ORDER BY timestamp")
    fun getFavorites(): Flow<List<TranslationFavorite>>

}