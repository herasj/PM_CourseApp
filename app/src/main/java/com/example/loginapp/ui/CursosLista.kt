package com.example.loginapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapp.R
import com.example.loginapp.adapters.CursosRVAdapter
import com.example.loginapp.model.course.Course
import com.example.loginapp.viewmodel.CourseViewModel
import kotlinx.android.synthetic.main.fragment_cursos_lista.view.*

class CursosLista : Fragment() {
    val cursos = mutableListOf<Course>()
    private var adapter : CursosRVAdapter? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cursos_lista, container, false)
        val courseVM : CourseViewModel by activityViewModels()
        courseVM.getCourses()
        adapter = CursosRVAdapter(courseVM.courses)
        view.cursosRV.layoutManager = LinearLayoutManager(context)
        view.cursosRV.adapter = adapter
        return view
    }
}