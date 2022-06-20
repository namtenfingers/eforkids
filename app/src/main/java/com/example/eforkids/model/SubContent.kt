package com.example.eforkids.model

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SubContent(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val idContent: Int,
    val idSubOfSub: Int,
    val name: String,
    val mean: String,
    val imagePath: String,
    var status: Int = 0,
    var imgChecker: String = "",
    val spelling: String = "",
    val detail: String = ""
)