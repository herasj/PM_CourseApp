package com.example.loginapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapp.R
import com.example.loginapp.adapters.EstudiantesRVAdapter
import com.example.loginapp.viewmodel.StudentViewModel
import kotlinx.android.synthetic.main.fragment_cursos.view.*
import kotlinx.android.synthetic.main.fragment_student.view.*

class StudentFragment : Fragment() {
    private val args : StudentFragmentArgs by navArgs()
    private val studentVm: StudentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student, container, false)
        // Inflate the layout for this fragment
        studentVm.getStudentInfo(args.studentId.toString())
        studentVm.studentDetailLiveData.observe(viewLifecycleOwner, Observer { student ->
            view.name.text = student.name
            view.username.text = student.username
            view.phone.text = student.phone
            view.email.text = student.email
            view.city.text = student.city
            view.country.text = student.country
            view.birthday.text = student.birthday
        })
        return view
    }
}