package com.example.bpbdapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bpbdapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            // TODO: Add authentication logic here
            // For now, let's assume the user is a "pimpinan"
            val role = "pimpinan"
            val sharedPref = getSharedPreferences("BPBDApp", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("user_role", role)
                apply()
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
