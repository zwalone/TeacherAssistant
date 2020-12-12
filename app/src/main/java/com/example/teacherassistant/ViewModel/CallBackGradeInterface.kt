package com.example.teacherassistant.ViewModel

import com.example.teacherassistant.Model.Grade
import com.example.teacherassistant.Model.StudentGrade

interface CallBackStudentGradeInterface {
    fun onClickStudentGrade(studentGrade: StudentGrade)
}