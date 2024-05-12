package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todoapp.databinding.ActivityAddtasksBinding
//import com.example.todoapp.databinding.ActivityMainBinding

class Addtasks : AppCompatActivity() {
    private lateinit var binding: ActivityAddtasksBinding
    private lateinit var db:database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddtasksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= database(this)
        binding.add.setOnClickListener {

            val name=binding.taskname.text.toString()
            val des=binding.taskdes2.text.toString()
            val pr=binding.prioroty.text.toString()
            val date=binding.date.text.toString()
            val task=task(0,name,pr,des,date)
            db.addtasks(task)
            finish()
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
        }
        binding.button4.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)




        }

    }
}