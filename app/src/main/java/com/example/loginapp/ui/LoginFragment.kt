package com.example.loginapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.loginapp.R
import com.example.loginapp.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val loginViewModel: LoginViewModel by activityViewModels()
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        loginViewModel.getLogged().observe(viewLifecycleOwner, Observer { logged ->
            if (logged == true) {
                navController.navigate(R.id.cursosLista)
            }
        })
        view.findViewById<Button>(R.id.saveButton).setOnClickListener {
            loginViewModel.login(usernameText.text.toString(), passwordText.text.toString())
            loginViewModel.setLogged(true)
        }
        view.findViewById<Button>(R.id.registerButton).setOnClickListener {
            navController.navigate(R.id.registerFragment)
        }
    }
}