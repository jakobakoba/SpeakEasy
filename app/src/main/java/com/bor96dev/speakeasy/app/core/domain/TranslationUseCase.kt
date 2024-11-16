package com.bor96dev.speakeasy.app.core.domain

import com.bor96dev.speakeasy.app.core.TranslationApi
import javax.inject.Inject

class TranslationUseCase @Inject constructor(
    private val translationApi: TranslationApi
) {
    suspend fun translate(){

    }
}