package com.example.teacherassistant.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.StudentGrade
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.Adapters.StudentsGradesAdapter
import com.example.teacherassistant.ViewModel.CallBackStudentGradeInterface
import com.example.teacherassistant.ViewModel.FragmentVM.GradeVM
import com.example.teacherassistant.ViewModel.FragmentVM.StudentVM
import kotlinx.android.synthetic.main.fragment_student_grades_list.*
import kotlinx.android.synthetic.main.fragment_student_grades_list.view.*

class StudentGradesList : Fragment(), CallBackStudentGradeInterface {

    lateinit var mGrade: GradeVM
    lateinit var mStudent: StudentVM
    lateinit var myAdapter: StudentsGradesAdapter
    lateinit var myLinearLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_grades_list, container, false)

        mGrade = ViewModelProvider(requireActivity()).get(GradeVM::class.java)
        mStudent = ViewModelProvider(requireActivity()).get(StudentVM::class.java)

        myLinearLayoutManager = LinearLayoutManager(context)
        mGrade.readAllStudentGrades(mStudent.currentStudent)

        myAdapter = StudentsGradesAdapter(mGrade.readAllStudentGrade, this)

        mGrade.readAllStudentGrade.observe(viewLifecycleOwner, Observer {
            myAdapter.notifyDataSetChanged()
        })

        view.RemoveGradeButton.setOnClickListener{
            mGrade.deleteGrade(mGrade.currentGrade)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = RVStudentsGrades.apply {
            this.adapter = myAdapter
            this.layoutManager = myLinearLayoutManager
        }
    }

    override fun onClickStudentGrade(studentGrade: StudentGrade) {
        mGrade.currentStudentGrade = studentGrade
        mGrade.currentGrade = studentGrade.grade
    }


}