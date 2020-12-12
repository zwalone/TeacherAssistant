package com.example.teacherassistant.ViewModel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.CallBackStudentInterface
import kotlinx.android.synthetic.main.add_student_to_course_one_row.view.*

class AddStudentToCourseAdapter(var students: LiveData<List<Student>>, val OnClick: CallBackStudentInterface)
    :RecyclerView.Adapter<AddStudentToCourseAdapter.StudentToCourseHolder>() {

    inner class StudentToCourseHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentToCourseHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.add_student_to_course_one_row, parent, false)
        return StudentToCourseHolder(view)
    }

    override fun getItemCount(): Int {
        return students.value?.size?: 0
    }

    override fun onBindViewHolder(holder: StudentToCourseHolder, position: Int) {
        holder.itemView.OneRowAddToCourseStudentName.text = students.value?.get(position)?.name.toString()
        holder.itemView.OneRowAddToCourseStudentSurname.text = students.value?.get(position)?.surname.toString()
        holder.itemView.AddStudentToCourseButton.setOnClickListener {
            OnClick.onStudentClick(students.value!![position])
        }
    }
}