package com.example.teacherassistant.Model.Repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.Model.Dao.CourseDao

//VALIDACJA
class CourseRepository(private val courseDao: CourseDao) {
    val getAllCourses: LiveData<List<Course>> = courseDao.allCourses()

    suspend fun addCourse(course: Course){
        courseDao.insertCourse(course)
        Log.v("course", "added")
    }
    suspend fun removeCourse(course: Course?){
        if (course != null){
            courseDao.deleteCourse(course)
        }
        Log.v("course", "removed")
    }
    suspend fun updateCourse(course: Course?){
        if(course != null){
            courseDao.updateCourse(course)
        }
        Log.v("course", "updated")
    }
    fun getCourseById(idc: Int): Course = courseDao.getCourseById(idc)
}