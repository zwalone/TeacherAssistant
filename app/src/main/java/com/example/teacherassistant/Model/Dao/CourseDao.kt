package com.example.teacherassistant.Model.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.Model.Course

@Dao
interface CourseDao {

    @Insert
    suspend fun insertCourse(course: Course)

    @Delete
    suspend fun deleteCourse(course: Course)

    @Update
    suspend fun updateCourse(course: Course)

    @Query("SELECT * FROM Course")
    fun allCourses(): LiveData<List<Course>>

    @Query("SELECT * FROM Course WHERE idc = :id")
    fun getCourseById(id: Int): Course


}