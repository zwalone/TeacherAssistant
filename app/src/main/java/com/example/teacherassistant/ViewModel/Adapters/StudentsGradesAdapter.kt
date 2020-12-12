package com.example.teacherassistant.ViewModel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.StudentGrade
import com.example.teacherassistant.Model.StudentGradeCourse
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.CallBackStudentGradeInterface
import kotlinx.android.synthetic.main.student_grades_one_row.view.*

class StudentsGradesAdapter(var grades: LiveData<List<StudentGradeCourse>>,
                            val onGradeClick: CallBackStudentGradeInterface)
    : RecyclerView.Adapter<StudentsGradesAdapter.StudentsGradesHolder>() {

        inner class StudentsGradesHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsGradesHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_grades_one_row, parent, false)
        return StudentsGradesHolder(view)
    }

    override fun onBindViewHolder(holder: StudentsGradesHolder, position: Int) {

        holder.itemView.StudentGradesGradeIntTextView.text = grades.value!!.get(position).grade.grade.toString()
        holder.itemView.StudentGradesCourseNameTextView.text = grades.value?.get(position)?.course?.name

        holder.itemView.StudentGradesShowDetailsButton.setOnClickListener { view ->
            onGradeClick.onClickStudentGrade(
                StudentGrade(student = grades.value?.get(position)?.student!!,
                    grade = grades.value?.get(position)?.grade!!)
            )
            view.findNavController().navigate(R.id.action_studentGradesList_to_viewGrade)
        }

        holder.itemView.setOnClickListener{
            onGradeClick.onClickStudentGrade(
                StudentGrade(student = grades.value?.get(position)?.student!!,
                    grade = grades.value?.get(position)?.grade!!)
            )
        }
    }

    override fun getItemCount(): Int {
        return grades.value?.size?:0
    }
}