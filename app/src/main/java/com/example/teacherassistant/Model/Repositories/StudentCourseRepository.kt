package com.example.teacherassistant.Model.Repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.Model.CourseWithStudents
import com.example.teacherassistant.Model.Dao.StudentCourseDao
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.Model.StudentCourseCrossRef

class StudentCourseRepository(private val studentCourseDao: StudentCourseDao) {

    lateinit var readAllStudentsOfCourse : LiveData<List<Student>>
    lateinit var readAllStudentsOutOfCourse : LiveData<List<Student>>

    suspend fun addStudentCourseCrossRef(studentCourseCrossRef: StudentCourseCrossRef){
        studentCourseDao.insertStudentCourseCrossRef(studentCourseCrossRef)
        Log.v("sc", "added cross")
    }

    suspend fun removeStudentCourseCrossRef(studentCourseCrossRef: StudentCourseCrossRef?){
        if(studentCourseCrossRef != null){
            studentCourseDao.deleteStudentCourseCrossRef(studentCourseCrossRef)
        }
        Log.v("sc", "removed cross")
    }

     fun getStudentsOfCourse(course: Course){
         studentCourseDao.getStudentsOfCourse(course.idc)
     }
    fun getCoursesOfStudent(student: Student){
        studentCourseDao.getCoursesOfStudent(student.ids)
    }

    fun getStudentsOutOfCourse(course: Course){
        readAllStudentsOutOfCourse = studentCourseDao.getStudentsOutOfCourse(course.idc)
    }

    fun getSsOfC(course: Course){
        readAllStudentsOfCourse = studentCourseDao.getStudentsOfCourse(course.idc)
    }
}