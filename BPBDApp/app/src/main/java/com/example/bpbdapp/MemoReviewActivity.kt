package com.example.bpbdapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bpbdapp.databinding.ActivityMemoReviewBinding

class MemoReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemoReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Get memo report details from intent and display them

        binding.forwardButton.setOnClickListener {
            // TODO: Implement forward report logic
        }

        binding.returnButton.setOnClickListener {
            // TODO: Implement return report logic
        }
    }
}
