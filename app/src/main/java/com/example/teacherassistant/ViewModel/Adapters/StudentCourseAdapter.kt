package com.example.teacherassistant.ViewModel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.CallBackStudentInterface
import kotlinx.android.synthetic.main.student_course_one_row.view.*

class StudentCourseAdapter(var students: LiveData<List<Student>>, val onClickStudent: CallBackStudentInterface)
    : RecyclerView.Adapter<StudentCourseAdapter.StudentCourseHolder>(){

    inner class StudentCourseHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentCourseHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_course_one_row, parent, false)

        return StudentCourseHolder(view)
    }

    override fun getItemCount(): Int {
        return students.value?.size?: 0
    }

    override fun onBindViewHolder(holder: StudentCourseHolder, position: Int) {
        holder.itemView.OneRowNameSC.text = students.value?.get(position)?.name.toString()
        holder.itemView.OneRowSurnameSC.text = students.value?.get(position)?.surname.toString()
        holder.itemView.OneRowAddGradeSC.setOnClickListener {view ->
            onClickStudent.onStudentClick(students.value!!.get(position))
            view.findNavController().navigate(R.id.action_studentCourseList_to_addGrade)
        }
        holder.itemView.setOnClickListener {
            onClickStudent.onStudentClick(students.value!!.get(position))
        }
    }
}