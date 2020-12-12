package com.example.teacherassistant.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.FragmentVM.GradeVM
import kotlinx.android.synthetic.main.fragment_view_grade.view.*

class ViewGrade : Fragment() {

    lateinit var mGrade: GradeVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_grade, container, false)

        mGrade = ViewModelProvider(requireActivity()).get(GradeVM::class.java)

        view.GradeViewStudentName.text = mGrade.currentStudentGrade?.student?.name
        view.GradeViewStudentSurname.text = mGrade.currentStudentGrade?.student?.surname
        view.GradeViewGradeInt.text = mGrade.currentStudentGrade?.grade?.grade.toString()
        view.GradeViewGradeDescription.text = mGrade.currentStudentGrade?.grade?.description.toString()

        return view
    }


}