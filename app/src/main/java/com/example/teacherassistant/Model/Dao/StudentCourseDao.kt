package com.example.teacherassistant.Model.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.teacherassistant.Model.CourseWithStudents
import com.example.teacherassistant.Model.StudentCourseCrossRef
import com.example.teacherassistant.Model.StudentWithCourses

@Dao
interface StudentCourseDao {
    @Insert
    suspend fun insertStudentCourseCrossRef(crossRef: StudentCourseCrossRef)

    @Transaction
    @Query("SELECT * FROM Course WHERE  idc =:course_id")
    fun getStudentsOfCourse(course_id: Int):LiveData<List<CourseWithStudents>>

    @Transaction
    @Query("SELECT * FROM Student WHERE  ids =:student_id")
    fun getCoursesOfStudent(student_id: Int): LiveData<List<StudentWithCourses>>
}