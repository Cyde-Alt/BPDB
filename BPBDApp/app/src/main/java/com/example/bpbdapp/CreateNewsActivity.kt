package com.example.bpbdapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bpbdapp.databinding.ActivityCreateNewsBinding

class CreateNewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Implement UI and logic to create and submit news
    }
}
