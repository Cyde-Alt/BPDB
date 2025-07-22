package com.example.bpbdapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bpbdapp.databinding.ActivityMemoDetailBinding

class MemoDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemoDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Get memo details from intent and display them

        binding.confirmButton.setOnClickListener {
            // TODO: Implement confirm memo logic
        }

        binding.reportButton.setOnClickListener {
            val intent = Intent(this, MemoReportActivity::class.java)
            startActivity(intent)
        }
    }
}
