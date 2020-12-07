package com.example.teacherassistant.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey(autoGenerate = true)
    val ids: Int = 0,
    var name: String,
    var surname: String
)