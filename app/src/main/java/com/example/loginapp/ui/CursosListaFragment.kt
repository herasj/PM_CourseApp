package com.example.loginapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapp.R
import com.example.loginapp.adapters.CursosRVAdapter
import com.example.loginapp.model.course.Course
import com.example.loginapp.viewmodel.CourseViewModel
import kotlinx.android.synthetic.main.fragment_cursos_lista.*
import kotlinx.android.synthetic.main.fragment_cursos_lista.view.*

class CursosListaFragment : Fragment() {
    val cursos = mutableListOf<Course>()
    private var adapter : CursosRVAdapter? = null
    private var param2: String? = null
    private val courseVM : CourseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cursos_lista, container, false)
        view.findViewById<Button>(R.id.createCourse).setOnClickListener{ onCreateCourse() }
        courseVM.getCourses()
        courseVM.coursesLiveData.observe(viewLifecycleOwner, Observer { logged ->
            adapter = CursosRVAdapter(courseVM.courses, ::navigate)
            view.cursosRV.layoutManager = LinearLayoutManager(context)
            view.cursosRV.adapter = adapter
            // Log.d("Cursos", courseVM.courses.toString())
        })
        return view
    }

    private fun navigate (curso : Int){
        val action = CursosListaFragmentDirections.actionCursosListaToCursosFragment(curso)
        view?.findNavController()?.navigate(action)
    }

    private fun onCreateCourse(){
        courseVM.createCourse()
    }
}