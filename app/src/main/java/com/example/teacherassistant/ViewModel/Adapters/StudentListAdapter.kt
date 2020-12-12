package com.example.teacherassistant.ViewModel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.CallBackStudentInterface
import com.example.teacherassistant.ViewModel.FragmentVM.StudentVM
import kotlinx.android.synthetic.main.student_one_row.view.*

class StudentListAdapter(var students: LiveData<List<Student>>, val OnStudentClick: CallBackStudentInterface)
    :RecyclerView.Adapter<StudentListAdapter.StudentHolder>() {

    inner class StudentHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_one_row, parent, false)
        
        return  StudentHolder(view)
    }

    override fun getItemCount(): Int {
        return  students.value?.size?: 0
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        holder.itemView.OneRowStudentListId.text = students.value?.get(position)?.ids.toString()
        holder.itemView.OneRowStudentListName.text = students.value?.get(position)?.name.toString()
        holder.itemView.OneRowStudentListSurname.text = students.value?.get(position)?.surname.toString()
        holder.itemView.ShowGradesButton.setOnClickListener { view ->
            OnStudentClick.onStudentClick(students.value!!.get(position))
            view.findNavController().navigate(R.id.action_listStudents_to_studentGradesList)
        }
        holder.itemView.setOnClickListener {
            OnStudentClick.onStudentClick(students.value!![position])
        }
    }
}
