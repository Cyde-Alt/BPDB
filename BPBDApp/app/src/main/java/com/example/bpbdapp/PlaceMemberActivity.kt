package com.example.bpbdapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bpbdapp.databinding.ActivityPlaceMemberBinding

class PlaceMemberActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlaceMemberBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaceMemberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Populate spinners with members and divisions

        binding.submitButton.setOnClickListener {
            // TODO: Implement placement submission logic
        }
    }
}
