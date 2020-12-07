package com.example.teacherassistant.View

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.FragmentVM.StudentVM
import kotlinx.android.synthetic.main.fragment_student_add_edit.*
import kotlinx.android.synthetic.main.fragment_student_add_edit.view.*


class StudentAddEdit : Fragment() {

    private lateinit var  mStudentAddEditVM: StudentVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_student_add_edit, container, false)
        mStudentAddEditVM = ViewModelProvider(requireActivity()).get(StudentVM::class.java)

        //student view
        if(mStudentAddEditVM.currentStudent != null){
            Log.v("student", "textChanged")
            view.EditStudentName.setText(mStudentAddEditVM.currentStudent!!.name)
            view.EditStudentSurname.setText(mStudentAddEditVM.currentStudent!!.surname)
        }

        view.floatingAddButton.setOnClickListener {
            InsertStudentToDatabase()
        }
        view.floatingUpdateButton.setOnClickListener {
            UpdateStudentToDatabase()
        }
        return view
    }

    fun InsertStudentToDatabase(){
        val name = EditStudentName.text.toString()
        val surname = EditStudentSurname.text.toString()
        //Check inputs
        if(InputCheck(name, surname)){
            //create student
            val student = Student(name = name, surname =surname)
            //add to database
            mStudentAddEditVM.addStudent(student)
            //Toast report
            Toast.makeText(requireContext(), "Added", Toast.LENGTH_LONG).show()
            //current student = null
            mStudentAddEditVM.currentStudent = null
            //navigate back
            findNavController().navigate(R.id.action_studentAddEdit_to_listStudents)
        }else{
            Toast.makeText(requireContext(), "Text field is empty", Toast.LENGTH_LONG).show()
        }
    }

    fun UpdateStudentToDatabase(){
        if(mStudentAddEditVM.currentStudent != null){
            //Set Data to current student
            mStudentAddEditVM.currentStudent!!.name = EditStudentName.text.toString()
            mStudentAddEditVM.currentStudent!!.surname =EditStudentSurname.text.toString()
            //update student
            mStudentAddEditVM.updateStudent(mStudentAddEditVM.currentStudent!!)
            //current student = null
            mStudentAddEditVM.currentStudent = null
            //navigate back
            findNavController().navigate(R.id.action_studentAddEdit_to_listStudents)
        }else{
            Toast.makeText(requireContext(), "Student must be selected", Toast.LENGTH_LONG).show()
        }
    }

    fun InputCheck(name: String, surname: String): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty((surname)))
    }
}