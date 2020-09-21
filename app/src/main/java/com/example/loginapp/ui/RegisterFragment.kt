package com.example.loginapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.loginapp.R
import com.example.loginapp.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val loginViewModel: LoginViewModel by activityViewModels()
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        loginViewModel.getLogged().observe(viewLifecycleOwner, Observer { logged ->
            if (logged == true) {
                navController.navigate(R.id.userFragment)
            }
        })
        view.findViewById<Button>(R.id.saveButton).setOnClickListener {
            if (editTextTextPassword.text.toString() == passwordRegisterText2.text.toString()) {
                loginViewModel.setCredentials(
                    usernameRegisterText.text.toString(),
                    editTextTextPassword.text.toString()
                );
                loginViewModel.setLogged(true)
            }else{
                Toast.makeText(view.context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }


}