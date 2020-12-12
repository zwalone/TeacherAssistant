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

class CourseAddEdit : Fragment() {

    private lateinit var mCourseVM: CourseVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_course_add_edit, container, false)

        mCourseVM = ViewModelProvider(requireActivity()).get(CourseVM::class.java)
        if(mCourseVM.currentCourse != null){
            view.EditCourseName.setText(mCourseVM.currentCourse!!.name)
        }

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
            val course = Course(name = name)
            mCourseVM.addCourse(course)
            Toast.makeText(requireContext(), "Added", Toast.LENGTH_LONG).show()
            mCourseVM.currentCourse = null
            findNavController().navigate(R.id.action_courseAddEdit_to_listCourses)
        }else{
            Toast.makeText(requireContext(), "Text field is empty", Toast.LENGTH_LONG).show()
        }
    }

    fun UpdateCourseToDatabase(){
        if(mCourseVM.currentCourse != null){
            mCourseVM.currentCourse!!.name = EditCourseName.text.toString()
            mCourseVM.updateCourse(mCourseVM.currentCourse!!)
            mCourseVM.currentCourse = null
            findNavController().navigate(R.id.action_courseAddEdit_to_listCourses)
        }else{
            Toast.makeText(requireContext(), "Course must be selected", Toast.LENGTH_LONG).show()
        }
    }

    fun InputCheck(name: String?): Boolean{
        return !(TextUtils.isEmpty(name))
    }
}