package com.example.bpbdapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bpbdapp.databinding.FragmentMembersBinding

class MembersFragment : Fragment() {

    private var _binding: FragmentMembersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMembersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val members = listOf(
            Member("1", "John Doe", "pimpinan", "siaga"),
            Member("2", "Jane Smith", "sekretaris", "bertugas"),
            Member("3", "Peter Jones", "operator", "non-aktif")
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MemberAdapter(members)
        }

        val sharedPref = activity?.getSharedPreferences("BPBDApp", Context.MODE_PRIVATE)
        val userRole = sharedPref?.getString("user_role", "operator")

        if (userRole == "super admin" || userRole == "sekretaris") {
            binding.fabAddMember.visibility = View.VISIBLE
            binding.fabAddMember.setOnClickListener {
                // TODO: Open add member activity
            }
        } else {
            binding.fabAddMember.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
