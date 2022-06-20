package com.example.eforkids.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
    val topicId: Int,
    @PrimaryKey(autoGenerate = true)
    val wordId: Int,
    val word: String,
    val mean: String,
    val imagePath: String
)