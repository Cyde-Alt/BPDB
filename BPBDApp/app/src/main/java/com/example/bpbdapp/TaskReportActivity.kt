package com.example.bpbdapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bpbdapp.databinding.ActivityTaskReportBinding

class TaskReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.uploadButton.setOnClickListener {
            // TODO: Implement file upload logic
        }

        binding.submitButton.setOnClickListener {
            // TODO: Implement report submission logic
        }
    }
}
