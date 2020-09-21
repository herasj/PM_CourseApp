package com.example.loginapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapp.R
import com.example.loginapp.model.Post
import com.example.loginapp.viewmodel.LoginViewModel
import com.example.loginapp.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.android.synthetic.main.fragment_user.view.*
import kotlinx.android.synthetic.main.recycler_layout.*


class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val loginViewModel: LoginViewModel by activityViewModels()
        val postViewModel: PostViewModel by activityViewModels()
        val adapter = PostAdapter(ArrayList())

        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        requireView().postRecycler.adapter = adapter
        requireView().postRecycler.layoutManager = LinearLayoutManager(requireContext())
        loginViewModel.getLogged().observe(viewLifecycleOwner, Observer { logged ->
            if (logged == false) {
                navController.navigate(R.id.loginFragment2)
            }
            view.findViewById<Button>(R.id.logoutButton).setOnClickListener {
                loginViewModel.setLogged(false)
            }
        })

        view.findViewById<Button>(R.id.createPostButton).setOnClickListener {
            Log.d("Title", view.findViewById<TextView>(R.id.createTtitleText).text.toString())
            Log.d("Body", view.findViewById<TextView>(R.id.createMsg).text.toString())
            postViewModel.createPost(
                view.findViewById<TextView>(R.id.createTtitleText).text.toString(),
                view.findViewById<TextView>(R.id.createMsg).text.toString()
            )
        }
        postViewModel.postsLiveData.observe(viewLifecycleOwner, Observer {
            adapter.posts.clear()
            adapter.posts.addAll(it)
            adapter.notifyDataSetChanged()
        })

    }
}