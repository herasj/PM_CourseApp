package com.example.loginapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.R
import com.example.loginapp.model.course.Course
import com.example.loginapp.model.student.Student
import kotlinx.android.synthetic.main.curso_item.view.*
import kotlinx.android.synthetic.main.estudiante_item.view.*

class EstudiantesRVAdapter (private val estudiantes: List<Student>, private val navigate : (estudianteId: Int) -> Unit) : RecyclerView.Adapter<EstudiantesRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EstudiantesRVAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.estudiante_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = estudiantes.size

    override fun onBindViewHolder(holder: EstudiantesRVAdapter.ViewHolder, position: Int) {
        val item = estudiantes[position]
        holder.name.text = item.name
        holder.btn.setOnClickListener {
            navigate(item.id)
        }
    }

    inner class ViewHolder (val eView : View) : RecyclerView.ViewHolder(eView){
        val btn : Button = eView.e_detailButton
        val name : TextView = eView.estudiante_name
    }
}