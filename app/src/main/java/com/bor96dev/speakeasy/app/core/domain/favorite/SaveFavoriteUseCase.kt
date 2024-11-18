package com.bor96dev.speakeasy.app.core.domain.favorite

import com.bor96dev.speakeasy.app.core.data.TranslationFavorite
import com.bor96dev.speakeasy.app.core.data.TranslationFavoriteDao
import javax.inject.Inject

class SaveFavoriteUseCase @Inject constructor(
    private val translationFavoriteDao: TranslationFavoriteDao
) {
    suspend fun saveFavorite (sourceText: String, translatedText: String) {
        translationFavoriteDao.saveFavorite(TranslationFavorite(sourceText = sourceText, translatedText = translatedText))
    }
}