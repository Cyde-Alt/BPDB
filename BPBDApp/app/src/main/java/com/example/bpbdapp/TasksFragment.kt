package com.example.bpbdapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bpbdapp.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tasks = listOf(
            Task("1", "Evakuasi Korban Banjir", "Desa Sukamaju", "Banjir", listOf("1", "2"), "dikerjakan"),
            Task("2", "Pemadaman Kebakaran Hutan", "Gunung Salak", "Kebakaran Hutan", listOf("3"), "selesai")
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TaskAdapter(tasks)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
