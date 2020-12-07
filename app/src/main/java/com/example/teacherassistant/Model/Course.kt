package com.example.teacherassistant.Model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Course(
    @PrimaryKey(autoGenerate = true)
    val idc: Int = 0,
    var name: String
)