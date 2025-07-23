package com.example.bpbdapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bpbdapp.databinding.ActivityExpenseApprovalBinding

class ExpenseApprovalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExpenseApprovalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpenseApprovalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Fetch and display pending expense requests
    }
}
