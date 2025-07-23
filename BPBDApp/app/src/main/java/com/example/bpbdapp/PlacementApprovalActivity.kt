package com.example.bpbdapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bpbdapp.databinding.ActivityPlacementApprovalBinding

class PlacementApprovalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlacementApprovalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlacementApprovalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Fetch pending placements and display them in a RecyclerView
    }
}
