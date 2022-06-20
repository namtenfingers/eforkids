package com.example.eforkids.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Topic(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val imagePath: String
)