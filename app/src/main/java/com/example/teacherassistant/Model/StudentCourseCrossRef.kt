package com.example.teacherassistant.Model

import androidx.room.Entity

@Entity(primaryKeys = ["ids", "idc"])
data class StudentCourseCrossRef(
    val ids: Int,
    val idc: Int
)