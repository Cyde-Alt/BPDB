package com.example.bpbdapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bpbdapp.databinding.ActivityThemeSettingsBinding

class ThemeSettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThemeSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThemeSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            // TODO: Implement save theme logic
        }
    }
}
