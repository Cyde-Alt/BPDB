package com.example.bpbdapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bpbdapp.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsList = listOf(
            News("1", "Banjir di Jakarta", "Banjir merendam beberapa wilayah di Jakarta akibat hujan deras.", "", System.currentTimeMillis()),
            News("2", "Gunung Merapi Erupsi", "Gunung Merapi kembali erupsi dan mengeluarkan awan panas.", "", System.currentTimeMillis())
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = NewsAdapter(newsList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
