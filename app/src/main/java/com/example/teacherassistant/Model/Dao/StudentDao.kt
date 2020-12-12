package com.example.teacherassistant.Model.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.Model.Student

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Query("SELECT * FROM Student")
    fun allStudents():LiveData<List<Student>>

    @Query("SELECT * FROM Student WHERE ids = :id")
    fun getStudentById(id: Int): Student


}