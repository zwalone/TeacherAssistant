package com.example.teacherassistant.View

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.teacherassistant.Model.Grade
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.FragmentVM.CourseVM
import com.example.teacherassistant.ViewModel.FragmentVM.GradeVM
import com.example.teacherassistant.ViewModel.FragmentVM.StudentCourseVM
import com.example.teacherassistant.ViewModel.FragmentVM.StudentVM
import kotlinx.android.synthetic.main.fragment_add_grade.*
import kotlinx.android.synthetic.main.fragment_add_grade.view.*
import java.text.SimpleDateFormat
import java.util.*

class AddGrade : Fragment(){

    var GradeNum: Int? = null;
    private lateinit var mStudentCourse: StudentCourseVM
    private lateinit var mCourse: CourseVM
    private lateinit var mGrade: GradeVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_grade, container, false)

        mStudentCourse = ViewModelProvider(requireActivity()).get(StudentCourseVM::class.java)
        mCourse = ViewModelProvider(requireActivity()).get(CourseVM::class.java)
        mGrade = ViewModelProvider(requireActivity()).get((GradeVM::class.java))

        val spinner:Spinner = view.findViewById(R.id.NumbersSpinner)
        ArrayAdapter.createFromResource(
            view.context,
            R.array.SpinnerNumbers,
            android.R.layout.simple_spinner_item
        ).also {  adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                GradeNum = parent.getItemAtPosition(position).toString().toInt()
                Log.v("grade", GradeNum.toString())
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        view.AproveGradeButton.setOnClickListener {
            AddGrade()
        }
        return view
    }

    fun AddGrade(){
        if (GradeNum != null){
            val text: String = DescriptionText.text.toString()
            val date: Date = Date()
            mGrade.insertGrade(Grade(student_id = mStudentCourse.currentStudent!!.ids,
                course_id = mCourse.currentCourse!!.idc, grade = GradeNum!!, description = text, date = date ))

            view?.findNavController()?.navigate(R.id.action_addGrade_to_studentCourseList)
        }else{
            Toast.makeText(requireContext(),"Select Mark", Toast.LENGTH_LONG).show()
        }
    }
}