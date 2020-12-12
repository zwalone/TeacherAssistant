package com.example.teacherassistant.Model.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.Model.Grade
import com.example.teacherassistant.Model.StudentGrade
import com.example.teacherassistant.Model.StudentGradeCourse
import java.util.*

@Dao
interface GradeDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrade(grade: Grade)

    @Delete
    suspend fun deleteGrade(grade: Grade)

    @Update
    suspend fun updateGrade(grade: Grade)

    @Query("SELECT * FROM grade_table join Student on grade_table.student_id = Student.ids WHERE date > :today and date < :dayBefore")
    fun getTodayGrade(today: Date, dayBefore: Date):LiveData<List<StudentGrade>>

    @Query("SELECT s.ids as s_ids, s.name as s_name, s.surname as s_surname,  gt.course_id, gt.student_id ,gt.date ,gt.description ,gt.grade, gt.id, c.name as c_name, c.idc as c_idc  FROM grade_table as gt join Student as s on gt.student_id = s.ids join Course as c on gt.course_id = c.idc WHERE student_id =:ids")
    fun getStudentGrades(ids : Int):LiveData<List<StudentGradeCourse>>

    @Query("SELECT * FROM GRADE_TABLE")
    fun getAllGrades():LiveData<List<Grade>>
}