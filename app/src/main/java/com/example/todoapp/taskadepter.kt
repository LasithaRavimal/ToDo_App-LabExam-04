package com.example.todoapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class taskadepter(private var task:List<task>,context: Context):
    RecyclerView.Adapter<taskadepter.taskviewhelper>() {


    private val db:database= database(context)




    class taskviewhelper(taskview:View):RecyclerView.ViewHolder(taskview){

        val namev:TextView=taskview.findViewById(R.id.nameview)

        val prv:TextView=taskview.findViewById(R.id.prview)
        val desv:TextView=taskview.findViewById(R.id.desview)
        val datev:TextView=taskview.findViewById(R.id.dateview)
        val updatebutton:ImageView=taskview.findViewById(R.id.updatebutton)
        val deletebutton:ImageView=taskview.findViewById(R.id.deletebutton)








    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): taskviewhelper {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.taskview,parent,false)
        return taskviewhelper(view)



    }

    override fun getItemCount(): Int=task.size

    override fun onBindViewHolder(holder:taskviewhelper, position: Int) {
        val task=task[position]

        holder.namev.text=task.name
        holder.prv.text=task.priority
        holder.desv.text=task.description
        holder.datev.text=task.date

        holder.updatebutton.setOnClickListener{

            val intent=Intent(holder.itemView.context,UpdateActivity::class.java).apply{

                putExtra("task_id",task.id)
            }

            holder.itemView.context.startActivity(intent)

        }

        holder.deletebutton.setOnClickListener{

            db.deletetask(task.id)
            refreshdata(db.viewtasks())
            Toast.makeText(holder.itemView.context, "Deleted", Toast.LENGTH_SHORT).show()




        }


    }

    fun refreshdata(newtask:List<task>){

        task=newtask
        notifyDataSetChanged()


    }

}