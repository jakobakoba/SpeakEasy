package com.bor96dev.speakeasy.app.core.domain.translation

import com.bor96dev.speakeasy.app.core.TranslationApi
import com.bor96dev.speakeasy.app.core.TranslationResponse
import com.bor96dev.speakeasy.app.core.domain.LanguageCode
import javax.inject.Inject

class TranslationUseCase @Inject constructor(
    private val translationApi: TranslationApi
) {
    suspend fun translate(sl: LanguageCode, dl: LanguageCode, text: String): TranslationResponse{
        return translationApi.translate(sl.code, dl.code, text)
    }
}