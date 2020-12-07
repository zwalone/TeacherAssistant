package com.example.teacherassistant.Model.Repositories

import androidx.lifecycle.LiveData
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.Model.Dao.CourseDao

//VALIDACJA
class CourseRepository(private val courseDao: CourseDao) {
    val getAllCourses: LiveData<List<Course>> = courseDao.allCourses()

    suspend fun addCourse(course: Course){
        courseDao.insertCourse(course)
    }
    suspend fun removeCourse(course: Course){
        courseDao.deleteCourse(course)
    }
    suspend fun updateCourse(course: Course){
        courseDao.updateCourse(course)
    }
    fun getStudentById(id: Int): Course = courseDao.getCourseById(id)
}