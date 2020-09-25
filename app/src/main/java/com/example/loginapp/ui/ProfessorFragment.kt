package com.example.loginapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.loginapp.R
import com.example.loginapp.viewmodel.ProfessorViewModel
import kotlinx.android.synthetic.main.fragment_student.view.*

class ProfessorFragment : Fragment() {
    private val args : ProfessorFragmentArgs by navArgs()
    private val professorVM : ProfessorViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_professor, container, false)
        professorVM.getProfessorInfo(args.professorId.toString())
        professorVM.professorDetailLiveData.observe(viewLifecycleOwner, Observer { professor ->
            view.name.text = professor.name
            view.username.text = professor.username
            view.phone.text = professor.phone
            view.email.text = professor.email
            view.city.text = professor.city
            view.country.text = professor.country
            view.birthday.text = professor.birthday
        })
        return view
    }

}