package com.example.teacherassistant.Model.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.Model.Grade

@Dao
interface GradeDao{

    @Insert
    suspend fun insertGrade(grade: Grade)

    @Delete
    suspend fun deleteGrade(grade: Grade)

    @Update
    suspend fun updateGrade(grade: Grade)

    @Query("SELECT * FROM grade_table WHERE date > DATE()")
    fun getTodayGrade():LiveData<List<Grade>>

    @Query("SELECT * FROM grade_table WHERE student_id =:ids")
    fun getStudentGrades(ids : Int):LiveData<List<Grade>>

    @Query("SELECT * FROM GRADE_TABLE")
    fun getAllGrades():LiveData<List<Grade>>
}