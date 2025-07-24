package com.example.bpbdapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.bpbdapp.databinding.ActivityTaskDetailBinding

import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar

class TaskDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskDetailBinding
    private lateinit var viewModel: TaskDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(TaskDetailViewModel::class.java)

        val taskId = intent.getStringExtra("TASK_ID")
        if (taskId == null) {
            finish() // or show an error
            return
        }

        observeViewModel()
        viewModel.getTaskDetails(taskId)

        val sharedPref = getSharedPreferences("BPBDApp", Context.MODE_PRIVATE)
        val userRole = sharedPref.getString("user_role", "operator")

        binding.reportButton.setOnClickListener {
            val intent = Intent(this, TaskReportActivity::class.java)
            intent.putExtra("TASK_ID", taskId)
            startActivity(intent)
        }
    }

    private fun observeViewModel() {
        viewModel.task.observe(this) { task ->
            binding.taskTitle.text = task.title
            binding.taskDescription.text = task.description
            // TODO: Display other task details
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.errorMessage.observe(this) { errorMessage ->
            Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
        }
    }
}
