package com.example.teacherassistant.Model
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "grade_table",
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = ["ids"],
            childColumns = ["student_id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Course::class,
            parentColumns = ["idc"],
            childColumns = ["course_id"],
            onDelete = CASCADE
        )
    ])


data class Grade(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val student_id: Int,
    val course_id: Int,
    var grade: Int,
    var description: String,
    var date: Date
)