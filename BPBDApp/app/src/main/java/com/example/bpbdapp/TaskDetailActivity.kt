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
        ThemeManager.applyTheme(this)
        binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this).get(TaskDetailViewModel::class.java)

        val taskId = intent.getStringExtra("TASK_ID")
        if (taskId == null) {
            finish() // or show an error
            return
        }

        observeViewModel()
        viewModel.getTaskDetails(taskId)
    }

    private fun observeViewModel() {
        viewModel.task.observe(this) { task ->
            binding.toolbarLayout.title = task.title
            binding.taskTitle.text = task.title
            binding.taskDescription.text = task.description
            binding.taskLocation.text = task.location
            binding.disasterType.text = task.disaster_type

            // Setup FAB based on user role and task status
            // TODO: Implement proper role-based logic
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.errorMessage.observe(this) { errorMessage ->
            Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
