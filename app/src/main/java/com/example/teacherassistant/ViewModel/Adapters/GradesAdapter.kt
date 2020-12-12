package com.example.teacherassistant.ViewModel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.StudentGrade
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.CallBackStudentGradeInterface
import kotlinx.android.synthetic.main.grade_one_row.view.*

class GradesAdapter(var grades: LiveData<List<StudentGrade>>,
                    val onGradeClick: CallBackStudentGradeInterface)
    :RecyclerView.Adapter<GradesAdapter.GradesHolder>() {

        inner class GradesHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradesHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.grade_one_row, parent, false)
        return GradesHolder(view)
    }

    override fun onBindViewHolder(holder: GradesHolder, position: Int) {
        holder.itemView.OneRowGradeIntTextView.text = grades.value?.get(position)?.grade?.grade.toString()
        holder.itemView.OneRowGradeNameTextView.text = grades.value?.get(position)?.student?.name.toString()
        holder.itemView.OneRowGradeSurnameTextView.text = grades.value?.get(position)?.student?.surname.toString()

        holder.itemView.ShowDetailsButton.setOnClickListener {view ->
            onGradeClick.onClickStudentGrade(grades.value!!.get(position))
            view.findNavController().navigate(R.id.action_listGrades_to_viewGrade)
        }
    }

    override fun getItemCount(): Int {
        return grades.value?.size ?: 0
    }


}