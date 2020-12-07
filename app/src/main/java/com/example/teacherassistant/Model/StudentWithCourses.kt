package com.example.teacherassistant.Model

import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class StudentWithCourses(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "ids",
        entityColumn = "idc",
        associateBy = Junction(StudentCourseCrossRef::class)
    )
    val courses: List<Course>
)