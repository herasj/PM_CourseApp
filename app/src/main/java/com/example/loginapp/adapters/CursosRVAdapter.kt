package com.example.loginapp.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.R
import com.example.loginapp.model.course.Course
import kotlinx.android.synthetic.main.curso_item.*
import kotlinx.android.synthetic.main.curso_item.view.*

class CursosRVAdapter (private val cursos: List<Course>, private val navigate : (cursoId: Int) -> Unit ) : RecyclerView.Adapter<CursosRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursosRVAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.curso_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cursos.size

    override fun onBindViewHolder(holder: CursosRVAdapter.ViewHolder, position: Int) {
        val item = cursos[position]
        holder.name.text = item.name
        holder.btn.setOnClickListener {
            navigate(item.id)
        }
    }

    inner class ViewHolder (val cView : View) : RecyclerView.ViewHolder(cView){
        val btn : Button = cView.detailButton
        val name : TextView = cView.course_name
    }
}