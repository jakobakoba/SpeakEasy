package com.bor96dev.speakeasy.app.core.domain.history

import com.bor96dev.speakeasy.app.core.data.TranslationHistory
import com.bor96dev.speakeasy.app.core.data.TranslationHistoryDao
import javax.inject.Inject

class SaveHistoryUseCase @Inject constructor(
    private val translationHistoryDao: TranslationHistoryDao
) {
    suspend fun saveHistory (sourceText: String, translatedText: String){
        translationHistoryDao.insertHistory(TranslationHistory(sourceText = sourceText, translatedText = translatedText))
    }
}