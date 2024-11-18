package com.bor96dev.speakeasy.app.screen.translation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bor96dev.speakeasy.app.core.domain.LanguageCode
import com.bor96dev.speakeasy.app.core.domain.favorite.SaveFavoriteUseCase
import com.bor96dev.speakeasy.app.core.domain.history.SaveHistoryUseCase
import com.bor96dev.speakeasy.app.core.domain.translation.TranslationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslationViewModel @Inject constructor(
    private val translationUseCase: TranslationUseCase,
    private val saveHistoryUseCase: SaveHistoryUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
): ViewModel() {
    private val _uiState = MutableStateFlow(TranslationUiState())
    val uiState: StateFlow<TranslationUiState> = _uiState

    fun updateInputText(text: String){
        _uiState.update {it.copy(inputText = text)}
    }

    fun clearInputText(){
        _uiState.update { it.copy(inputText = "") }
    }

    fun updateSourceLanguage(language:LanguageCode){
        _uiState.update{it.copy(sourceLanguage = language)}
    }
    fun updateTargetLanguage(language:LanguageCode){
        _uiState.update{it.copy(targetLanguage = language)}
    }

    fun swapLanguages(){
        _uiState.update {
            it.copy(
                sourceLanguage = it.targetLanguage,
                targetLanguage = it.sourceLanguage
            )
        }
    }

    fun translateText() {
        viewModelScope.launch {
            val result = translationUseCase.translate(
                sl = _uiState.value.sourceLanguage,
                dl = _uiState.value.targetLanguage,
                text = _uiState.value.inputText
            )
            _uiState.update {it.copy(translatedText = result.translations.possibleTranslations.firstOrNull() ?: uiState.value.inputText)}
            saveHistoryUseCase.saveHistory(_uiState.value.inputText, _uiState.value.translatedText.orEmpty())
        }
    }

    fun saveFavorite(){
        viewModelScope.launch{
            saveFavoriteUseCase.saveFavorite(_uiState.value.inputText, _uiState.value.translatedText.orEmpty())
        }
    }
}

data class TranslationUiState(
    val sourceLanguage: LanguageCode = LanguageCode.ENGLISH,
    val targetLanguage: LanguageCode = LanguageCode.RUSSIAN,
    val inputText: String = "",
    val translatedText: String? = null
)
