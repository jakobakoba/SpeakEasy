package com.bor96dev.speakeasy.app.screen.favorite

import androidx.lifecycle.ViewModel
import com.bor96dev.speakeasy.app.core.data.TranslationFavorite
import com.bor96dev.speakeasy.app.core.data.TranslationFavoriteDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val translationFavoriteDao: TranslationFavoriteDao
): ViewModel() {

    fun getFavorites(): Flow<List<TranslationFavorite>> {
        return translationFavoriteDao.getFavorites()
    }
}