package com.example.teacherassistant.Model.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.Model.StudentCourseCrossRef

@Dao
interface StudentCourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentCourseCrossRef(crossRef: StudentCourseCrossRef)

    @Delete
    suspend fun deleteStudentCourseCrossRef(crossRef: StudentCourseCrossRef)

    @Transaction
    @Query("SELECT * FROM Student  join StudentCourseCrossRef on StudentCourseCrossRef.ids = Student.ids where StudentCourseCrossRef.idc = :course_id")
    fun getStudentsOfCourse(course_id: Int): LiveData<List<Student>>

    @Transaction
    @Query("SELECT Student.ids, Student.name , Student.surname  FROM Student left join StudentCourseCrossRef on Student.ids = StudentCourseCrossRef.ids where StudentCourseCrossRef.idc is null  or StudentCourseCrossRef.idc !=:course_id")
    fun getStudentsOutOfCourse(course_id: Int): LiveData<List<Student>>

}