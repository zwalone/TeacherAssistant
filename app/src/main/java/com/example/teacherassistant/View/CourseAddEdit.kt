package com.example.teacherassistant.View

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.FragmentVM.CourseVM
import kotlinx.android.synthetic.main.fragment_course_add_edit.*
import kotlinx.android.synthetic.main.fragment_course_add_edit.view.*
import kotlinx.android.synthetic.main.fragment_student_add_edit.*

class CourseAddEdit : Fragment() {

    private lateinit var mCourseVM: CourseVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_course_add_edit, container, false)

        mCourseVM = ViewModelProvider(requireActivity()).get(CourseVM::class.java)
        //course view
        if(mCourseVM.currentCourse != null){
            view.EditCourseName.setText(mCourseVM.currentCourse!!.name)
        }

        //button
        view.floatingAddCourseButton.setOnClickListener {
            InsertCourseToDatabase()
        }
        view.floatingUpdateCourseButton.setOnClickListener{
            UpdateCourseToDatabase()
        }

        return view
    }

    fun InsertCourseToDatabase(){
        val name = EditCourseName.text.toString()
        if(InputCheck(name)){
            //create course
            val course = Course(name = name)
            //add to database
            mCourseVM.addCourse(course)
            //Toast report
            Toast.makeText(requireContext(), "Added", Toast.LENGTH_LONG).show()
            //current course = null
            mCourseVM.currentCourse = null
            //navigate back
            findNavController().navigate(R.id.action_courseAddEdit_to_listCourses)
        }else{
            Toast.makeText(requireContext(), "Text field is empty", Toast.LENGTH_LONG).show()
        }
    }

    fun UpdateCourseToDatabase(){
        if(mCourseVM.currentCourse != null){
            //Set Data to current course
            mCourseVM.currentCourse!!.name = EditCourseName.text.toString()
            //update student
            mCourseVM.updateCourse(mCourseVM.currentCourse!!)
            //current student = null
            mCourseVM.currentCourse = null
            //navigate back
            findNavController().navigate(R.id.action_courseAddEdit_to_listCourses)
        }else{
            Toast.makeText(requireContext(), "Course must be selected", Toast.LENGTH_LONG).show()
        }
    }

    fun InputCheck(name: String?): Boolean{
        return !(TextUtils.isEmpty(name))
    }
}