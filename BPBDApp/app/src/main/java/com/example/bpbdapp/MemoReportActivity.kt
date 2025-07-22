package com.example.bpbdapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bpbdapp.databinding.ActivityMemoReportBinding

class MemoReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemoReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.uploadButton.setOnClickListener {
            // TODO: Implement file upload logic
        }

        binding.submitButton.setOnClickListener {
            // TODO: Implement report submission logic
        }
    }
}
