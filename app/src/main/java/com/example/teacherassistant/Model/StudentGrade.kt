package com.example.teacherassistant.Model

import androidx.room.Embedded

data class StudentGrade(
    @Embedded
    var student: Student,
    @Embedded
    var grade: Grade
)
