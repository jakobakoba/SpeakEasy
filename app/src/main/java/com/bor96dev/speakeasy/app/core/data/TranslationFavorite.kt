package com.bor96dev.speakeasy.app.core.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "translation_favorite")
data class TranslationFavorite(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val sourceText: String,
    val translatedText: String,
    val timestamp: Long = Date().time
)