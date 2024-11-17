package com.bor96dev.speakeasy.app.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Query

interface TranslationApi {

    @GET("translate")
    suspend fun translate(
        @Query("sl") sourceLanguage: String,
        @Query("dl") destinationLanguage: String,
        @Query("text") text: String
    ): TranslationResponse
}

@Serializable
data class TranslationResponse(
    @SerialName("source-language") val sourceLanguage: String,
    @SerialName("source-text") val sourceText: String,
    @SerialName("destination-language") val destinationLanguage: String,
    @SerialName("destination-text") val destinationText: String,
    val pronunciation: Pronunciation,
    val translations: Translations,
//    val definitions: List<Definition>,
    @SerialName("see-also") val seeAlso: String? // Если "see-also" может быть null
)

@Serializable
data class Pronunciation(
    @SerialName("source-text-phonetic") val sourceTextPhonetic: String?,
    @SerialName("source-text-audio") val sourceTextAudio: String,
    @SerialName("destination-text-audio") val destinationTextAudio: String
)

@Serializable
data class Translations(
    @SerialName("possible-translations") val possibleTranslations: List<String>,
    @SerialName("possible-mistakes") val possibleMistakes: String? // null-значение
)



//@Serializable
//data class Definition(
//    @SerialName("part-of-speech") val partOfSpeech: String,
//    val definition: String,
//    val example: String,
//    @SerialName("other-examples") val otherExamples: List<String>?,
//    val synonyms: Synonyms?
//)

@Serializable
data class Synonyms(
    @SerialName("") val synonymsList: List<String>
)