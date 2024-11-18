package com.bor96dev.speakeasy.app.screen.translation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bor96dev.speakeasy.app.core.domain.LanguageCode
import com.bor96dev.speakeasy.app.core.domain.favorite.SaveFavoriteUseCase
import com.bor96dev.speakeasy.app.screen.favorite.FavoriteViewModel
import com.bor96dev.speakeasy.app.ui.ChooseLanguage
import com.bor96dev.speakeasy.app.ui.TextInput
import com.bor96dev.speakeasy.app.ui.TranslateButton
import com.bor96dev.speakeasy.app.ui.TranslationResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TranslationScreen(
    viewModelTranslation: TranslationViewModel = hiltViewModel(),
) {
    val uiState = viewModelTranslation.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        TopAppBar(title = {Text(text = "Translation App")})

        ChooseLanguage(
            sourceLanguage = uiState.value.sourceLanguage,
            targetLanguage = uiState.value.targetLanguage,
            onSourceLanguageChange = {viewModelTranslation.updateSourceLanguage(it)},
            onTargetLanguageChange = {viewModelTranslation.updateTargetLanguage(it)},
            swapLanguages = {viewModelTranslation.swapLanguages()},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextInput(
            language = uiState.value.sourceLanguage.name,
            text = uiState.value.inputText,
            onTextChange = {viewModelTranslation.updateInputText(it)},
            onClearText = {viewModelTranslation.clearInputText()},
            modifier = Modifier.padding(horizontal = 16.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        TranslateButton(
            onTranslate = {viewModelTranslation.translateText()},
            modifier = Modifier.padding(horizontal = 16.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))


        uiState.value.translatedText?.let{
            TranslationResult(
                language = uiState.value.targetLanguage.name,
                result = it,
                modifier = Modifier.padding(horizontal = 16.dp),
                onSaveFavorite = {viewModelTranslation.saveFavorite()}
            )
        }
    }
}