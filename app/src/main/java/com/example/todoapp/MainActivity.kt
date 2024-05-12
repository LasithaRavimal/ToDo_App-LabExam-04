package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

       private lateinit var binding: ActivityMainBinding
        private lateinit var db:database
        private lateinit var Taskadepter:taskadepter

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)


            binding=ActivityMainBinding.inflate(layoutInflater)
            setContentView(R.layout.activity_main)
            setContentView(binding.root)

            db= database(this)
            Taskadepter= taskadepter(db.viewtasks(),this)
           binding.taskRecyclerView.layoutManager=LinearLayoutManager(this)
           binding.taskRecyclerView.adapter=Taskadepter

            binding.add.setOnClickListener {
                val intent = Intent(this,Addtasks::class.java)
                startActivity(intent)
            }

        }


    override fun onResume() {
        super.onResume()
       Taskadepter.refreshdata(db.viewtasks())
    }
}
