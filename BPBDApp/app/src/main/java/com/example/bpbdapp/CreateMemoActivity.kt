package com.example.bpbdapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bpbdapp.databinding.ActivityCreateMemoBinding

class CreateMemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateMemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Add logic to show/hide recipient options based on user role

        binding.submitButton.setOnClickListener {
            // TODO: Implement memo submission logic
        }
    }
}
