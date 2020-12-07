package com.example.teacherassistant.Model

import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CourseWithStudents(
    @Embedded val course: Course,
    @Relation(
        parentColumn = "idc",
        entityColumn = "ids",
        associateBy = Junction(StudentCourseCrossRef::class)
    )
    val students: List<Student>
)