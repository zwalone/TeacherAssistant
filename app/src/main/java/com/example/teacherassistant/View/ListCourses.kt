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
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.Adapters.CoursesListAdapter
import com.example.teacherassistant.ViewModel.CallBackCourseInterface
import com.example.teacherassistant.ViewModel.FragmentVM.CourseVM
import kotlinx.android.synthetic.main.fragment_list_courses.*
import kotlinx.android.synthetic.main.fragment_list_courses.view.*

class ListCourses : Fragment(), CallBackCourseInterface{

    private lateinit var mCourseVM: CourseVM
    private lateinit var myAdapter: CoursesListAdapter
    private lateinit var myLinearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_courses, container, false)

        mCourseVM = ViewModelProvider(requireActivity()).get(CourseVM::class.java)
        myLinearLayoutManager = LinearLayoutManager(context)
        myAdapter = CoursesListAdapter(mCourseVM.readAllCourses, this)

        mCourseVM.readAllCourses.observe(viewLifecycleOwner, Observer{
            myAdapter.notifyDataSetChanged()
        })

        //Buttons
        view.RemoveCourseButton.setOnClickListener {
            mCourseVM.removeCourse()
        }
        view.AEditCourseButton.setOnClickListener {
            findNavController().navigate(R.id.action_listCourses_to_courseAddEdit)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = RVCoursesList.apply {
            this.layoutManager = myLinearLayoutManager
            this.adapter = myAdapter
        }
    }
    override fun onCourseClick(course: Course) {
        mCourseVM.currentCourse = course
        Log.v("course", course.idc.toString())
    }

}