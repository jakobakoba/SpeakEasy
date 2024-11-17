package com.bor96dev.speakeasy.app.screen.history

import androidx.lifecycle.ViewModel
import com.bor96dev.speakeasy.app.core.data.TranslationHistory
import com.bor96dev.speakeasy.app.core.data.TranslationHistoryDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val translationHistoryDao: TranslationHistoryDao
) : ViewModel(){
    fun getHistory(): Flow<List<TranslationHistory>> {
        return translationHistoryDao.getTranslationHistory()
    }
}