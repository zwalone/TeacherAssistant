package com.example.teacherassistant.ViewModel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.CallBackCourseInterface
import kotlinx.android.synthetic.main.course_one_row.view.*

class CoursesListAdapter(var courses: LiveData<List<Course>>, val onCourseClick: CallBackCourseInterface)
    :RecyclerView.Adapter<CoursesListAdapter.CourseHolder>()  {


    inner class CourseHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_one_row, parent, false)
        return CourseHolder(view)
    }

    override fun getItemCount(): Int {
        return courses.value?.size?: 0
    }

    override fun onBindViewHolder(holder: CourseHolder, position: Int) {
        holder.itemView.OneRowCoursesListName.text = courses.value?.get(position)?.name.toString()
        //holder.itemView.OneRowCoursesListName.text = courses.value?.get(position)?.idc.toString()


        holder.itemView.ShowStudentsButton.setOnClickListener {view ->
            onCourseClick.onCourseClick(courses.value!![position])
            view.findNavController().navigate(R.id.action_listCourses_to_studentCourseList)
        }

        //current course
        holder.itemView.setOnClickListener {
            onCourseClick.onCourseClick(courses.value!![position])
        }

    }
}