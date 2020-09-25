package com.example.loginapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavArgs
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapp.R
import com.example.loginapp.adapters.CursosRVAdapter
import com.example.loginapp.adapters.EstudiantesRVAdapter
import com.example.loginapp.viewmodel.CourseViewModel
import com.example.loginapp.viewmodel.StudentViewModel
import kotlinx.android.synthetic.main.fragment_cursos.view.*
import kotlinx.android.synthetic.main.fragment_cursos_lista.*
import kotlinx.android.synthetic.main.fragment_cursos_lista.view.*
import kotlinx.android.synthetic.main.fragment_cursos_lista.view.cursosRV

class CursosFragment : Fragment() {
    private val args : CursosFragmentArgs by navArgs()
    private var adapter : EstudiantesRVAdapter? = null
    private val courseVM : CourseViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cursos, container, false)

        view.findViewById<Button>(R.id.createStudent).setOnClickListener { onCreateStudent() }
        courseVM.getCourseInfo(args.cursoId.toString())
        courseVM.courseDetailLiveData.observe(viewLifecycleOwner, Observer { course ->
            adapter = EstudiantesRVAdapter(course.students, ::navigate)
            view.estudiantesRV.layoutManager = LinearLayoutManager(context)
            view.estudiantesRV.adapter = adapter
            view.professor.text = "Profesor: " + course.professor.name
            view.professorDetails.setOnClickListener() { navigateProfessor(course.professor.id) }
            // Log.d("Cursos", courseVM.courses.toString())
        })

        return view
    }

    private fun navigate (student : Int){
        val action = CursosFragmentDirections.actionCursosFragmentToStudentFragment(student)
        view?.findNavController()?.navigate(action)
    }

    private fun navigateProfessor (professor : Int){
        val action = CursosFragmentDirections.actionCursosFragmentToProfessorFragment(professor)
        view?.findNavController()?.navigate(action)
    }

    private fun onCreateStudent(){
        courseVM.createStudent(args.cursoId.toString())
    }

}