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
        val view = inflater.inflate(R.layout.fragment_student_add_edit, container, false)
        mStudentAddEditVM = ViewModelProvider(requireActivity()).get(StudentVM::class.java)

        if(mStudentAddEditVM.currentStudent != null){
            view.EditStudentName.setText(mStudentAddEditVM.currentStudent!!.name)
            view.EditStudentSurname.setText(mStudentAddEditVM.currentStudent!!.surname)
        }

        view.floatingAddStudentButton.setOnClickListener {
            InsertStudentToDatabase()
        }
        view.floatingUpdateStudentButton.setOnClickListener {
            UpdateStudentToDatabase()
        }
        return view
    }

    fun InsertStudentToDatabase(){
        val name = EditStudentName.text.toString()
        val surname = EditStudentSurname.text.toString()
        if(InputCheck(name, surname)){
            val student = Student(name = name, surname =surname)
            mStudentAddEditVM.addStudent(student)
            Toast.makeText(requireContext(), "Added", Toast.LENGTH_LONG).show()
            mStudentAddEditVM.currentStudent = null
            findNavController().navigate(R.id.action_studentAddEdit_to_listStudents)
        }else{
            Toast.makeText(requireContext(), "Text field is empty", Toast.LENGTH_LONG).show()
        }
    }

    fun UpdateStudentToDatabase(){
        if(mStudentAddEditVM.currentStudent != null){
            mStudentAddEditVM.currentStudent!!.name = EditStudentName.text.toString()
            mStudentAddEditVM.currentStudent!!.surname =EditStudentSurname.text.toString()
            mStudentAddEditVM.updateStudent(mStudentAddEditVM.currentStudent!!)
            mStudentAddEditVM.currentStudent = null
            findNavController().navigate(R.id.action_studentAddEdit_to_listStudents)
        }else{
            Toast.makeText(requireContext(), "Student must be selected", Toast.LENGTH_LONG).show()
        }
    }

    fun InputCheck(name: String, surname: String): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(surname))
    }
}