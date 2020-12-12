package com.example.teacherassistant.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.Adapters.StudentCourseAdapter
import com.example.teacherassistant.ViewModel.CallBackStudentInterface
import com.example.teacherassistant.ViewModel.FragmentVM.CourseVM
import com.example.teacherassistant.ViewModel.FragmentVM.StudentCourseVM
import com.example.teacherassistant.ViewModel.FragmentVM.StudentVM
import kotlinx.android.synthetic.main.fragment_student_course_list.*
import kotlinx.android.synthetic.main.fragment_student_course_list.view.*

class StudentCourseList : Fragment(), CallBackStudentInterface {

    private lateinit var mCourse: CourseVM
    private lateinit var mStudent: StudentVM
    private lateinit var mStudentCourse: StudentCourseVM
    private lateinit var myAdapter: StudentCourseAdapter
    private lateinit var myLinearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_student_course_list, container, false)

        mCourse = ViewModelProvider(requireActivity()).get(CourseVM::class.java)
        mStudent = ViewModelProvider(requireActivity()).get(StudentVM::class.java)
        mStudentCourse = ViewModelProvider(requireActivity()).get(StudentCourseVM::class.java)
        myLinearLayoutManager = LinearLayoutManager(context)

        mStudentCourse.currentCourse = mCourse.currentCourse!!

        mStudentCourse.getSsOC(mCourse.currentCourse)

        myAdapter = StudentCourseAdapter(mStudentCourse.readAllStudentsofCourse,this)

        mStudentCourse.readAllStudentsofCourse.observe(viewLifecycleOwner, Observer {
            myAdapter.notifyDataSetChanged()
        })

        view.RemoveStudentFromCourseButton.setOnClickListener {
            mStudentCourse.removeStudentCourseCrossRef()
        }

        view.AddStudentToCourseButton.setOnClickListener {
            findNavController().navigate(R.id.action_studentCourseList_to_addStudentToCourseList)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = RVStudentCourse.apply {
            this.layoutManager = myLinearLayoutManager
            this.adapter = myAdapter
        }
    }

    override fun onStudentClick(student: Student) {
        mStudentCourse.currentStudent = student
    }
}