package com.example.teacherassistant.Model

import androidx.room.Embedded

data class StudentGradeCourse(
    @Embedded(prefix = "s_")
    var student: Student,
    @Embedded
    var grade: Grade,

    @Embedded(prefix = "c_")
    var course: Course
)