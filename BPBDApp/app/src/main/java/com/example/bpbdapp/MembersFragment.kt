package com.example.bpbdapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
            Member("1", "John Doe", "siaga"),
            Member("2", "Jane Smith", "bertugas"),
            Member("3", "Peter Jones", "non-aktif")
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MemberAdapter(members)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
