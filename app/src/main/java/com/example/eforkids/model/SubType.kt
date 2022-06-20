package com.example.eforkids.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SubType(
    @PrimaryKey(autoGenerate = true)
    val storyId: Int,
    val typeId: Int,
    val subTypeId: Int,
    val name: String,
    val detail: String,
    val status: Int
)