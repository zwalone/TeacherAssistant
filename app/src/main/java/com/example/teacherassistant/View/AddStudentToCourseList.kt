package com.example.teacherassistant.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.Adapters.AddStudentToCourseAdapter
import com.example.teacherassistant.ViewModel.CallBackStudentInterface
import com.example.teacherassistant.ViewModel.FragmentVM.CourseVM
import com.example.teacherassistant.ViewModel.FragmentVM.StudentCourseVM
import com.example.teacherassistant.ViewModel.FragmentVM.StudentVM
import kotlinx.android.synthetic.main.fragment_add_student_to_course_list.*

class AddStudentToCourseList : Fragment(), CallBackStudentInterface{

    private lateinit var mStudent: StudentVM
    private lateinit var mStudentCourse: StudentCourseVM
    private lateinit var mCourseVM: CourseVM
    private lateinit var myAdapter: AddStudentToCourseAdapter
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add_student_to_course_list, container, false)

        mStudent = ViewModelProvider(requireActivity()).get(StudentVM::class.java)
        mStudentCourse = ViewModelProvider(requireActivity()).get(StudentCourseVM::class.java)
        mCourseVM = ViewModelProvider(requireActivity()).get(CourseVM::class.java)

        myLayoutManager = LinearLayoutManager(context)
        mStudentCourse.currentCourse = mCourseVM.currentCourse
        mStudentCourse.getStudentsOutOfCourse(mStudentCourse.currentCourse)

        myAdapter = AddStudentToCourseAdapter(mStudentCourse.readAllStudentsOutofCourse, this)
        mStudentCourse.readAllStudentsOutofCourse.observe(viewLifecycleOwner, Observer {
            myAdapter.notifyDataSetChanged()
        } )

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = RVAddStudentToCourse.apply {
            this.adapter = myAdapter
            this.layoutManager = myLayoutManager
        }
    }
    override fun onStudentClick(student: Student) {
        mStudentCourse.AddStudentToCourse(student, mCourseVM.currentCourse)
    }

}