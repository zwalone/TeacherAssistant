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
import com.example.teacherassistant.ViewModel.Adapters.StudentListAdapter
import com.example.teacherassistant.ViewModel.CallBackStudentInterface
import com.example.teacherassistant.ViewModel.FragmentVM.StudentVM
import kotlinx.android.synthetic.main.fragment_list_students.*
import kotlinx.android.synthetic.main.fragment_list_students.view.*

class ListStudents : Fragment(), CallBackStudentInterface {

    private lateinit var mStudentVM: StudentVM
    private lateinit var myAdapter: StudentListAdapter
    private lateinit var myLayoutManager:LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_students, container, false)

        mStudentVM = ViewModelProvider(requireActivity()).get(StudentVM::class.java)
        myLayoutManager = LinearLayoutManager(context)
        myAdapter = StudentListAdapter(mStudentVM.readAllStudents, this)

        mStudentVM.readAllStudents.observe(viewLifecycleOwner, Observer { t ->
            myAdapter.notifyDataSetChanged()
        })

        view.RemoveStudentButton.setOnClickListener {
            mStudentVM.removeStudent()
        }

        view.AEditStudentButton.setOnClickListener {
            findNavController().navigate(R.id.action_listStudents_to_studentAddEdit)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = RVStudentsList.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }
    }

    override fun onStudentClick(student: Student) {
        mStudentVM.currentStudent = student
    }
}