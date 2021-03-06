package com.example.teacherassistant.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.teacherassistant.R
import kotlinx.android.synthetic.main.fragment_start_menu.view.*

class StartMenu : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start_menu, container, false)

        view.GTCoursesButton.setOnClickListener {
            findNavController().navigate(R.id.action_startMenu_to_listCourses)
        }

        view.GTStudentsButton.setOnClickListener {
            findNavController().navigate(R.id.action_startMenu_to_listStudents)
        }
        view.GTRaportButton.setOnClickListener {
            findNavController().navigate(R.id.action_startMenu_to_listGrades)
        }
        return view
    }

}