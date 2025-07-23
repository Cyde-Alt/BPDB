package com.example.bpbdapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bpbdapp.databinding.ItemPlacementApprovalBinding

class PlacementApprovalAdapter(private val placements: List<Member>) :
    RecyclerView.Adapter<PlacementApprovalAdapter.PlacementApprovalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacementApprovalViewHolder {
        val binding = ItemPlacementApprovalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlacementApprovalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlacementApprovalViewHolder, position: Int) {
        holder.bind(placements[position])
    }

    override fun getItemCount() = placements.size

    class PlacementApprovalViewHolder(private val binding: ItemPlacementApprovalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(member: Member) {
            binding.memberName.text = member.name
            // TODO: Display division name
            // binding.divisionName.text = member.division.name

            binding.approveButton.setOnClickListener {
                // TODO: Implement approve placement logic
            }

            binding.rejectButton.setOnClickListener {
                // TODO: Implement reject placement logic
            }
        }
    }
}
