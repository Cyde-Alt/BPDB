package com.example.bpbdapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.bpbdapp.databinding.ActivityTaskDetailBinding

class TaskDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Get task details from intent and display them

        val sharedPref = getSharedPreferences("BPBDApp", Context.MODE_PRIVATE)
        val userRole = sharedPref.getString("user_role", "operator")

        if (userRole == "pimpinan") {
            binding.approvalButtons.visibility = View.VISIBLE
            binding.reportButton.visibility = View.GONE
        } else {
            binding.approvalButtons.visibility = View.GONE
            binding.reportButton.visibility = View.VISIBLE
        }

        binding.approveButton.setOnClickListener {
            // TODO: Implement approve task logic
        }

        binding.rejectButton.setOnClickListener {
            // TODO: Implement reject task logic
        }

        binding.reportButton.setOnClickListener {
            val intent = Intent(this, TaskReportActivity::class.java)
            startActivity(intent)
        }
    }
}
