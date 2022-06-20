package com.example.eforkids.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Content(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val imagePath: String,
    var counterTrue: Int
)