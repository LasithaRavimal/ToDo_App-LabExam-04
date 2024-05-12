package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todoapp.databinding.ActivityAddtasksBinding
import com.example.todoapp.databinding.ActivityUpdateBinding

class UpdateActivity :AppCompatActivity() {


    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: database
    private var taskid: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = database(this)

        taskid = intent.getIntExtra("task_id", -1)
        if (taskid == -1) {
            finish()
            return

        }

        val students = db.getbyid(taskid)
        binding.updatename.setText(students.name)
        binding.updateprioroty.setText(students.priority)
        binding.updatetaskdes2.setText(students.description)
        binding.updatedate.setText(students.date)


        binding.updatebutton.setOnClickListener {

            val newname = binding.updatename.text.toString()
            val newpr = binding.updateprioroty.text.toString()
            val newdes = binding.updatetaskdes2.text.toString()
            val newdate = binding.updatedate.text.toString()
            val updatestudent = task(taskid, newname, newpr, newdes,newdate)
            db.updatetask(updatestudent)
            finish()
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()


        }


        binding.button4.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)




        }


    }
}