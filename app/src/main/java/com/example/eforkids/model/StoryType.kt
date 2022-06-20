package com.example.eforkids.model

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StoryType(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nameType: String,
    val status: Int
)