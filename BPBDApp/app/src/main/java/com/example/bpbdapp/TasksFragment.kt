package com.example.bpbdapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bpbdapp.databinding.FragmentTasksBinding

import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar

class TasksFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!
    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tasksViewModel = ViewModelProvider(this).get(TasksViewModel::class.java)

        setupRecyclerView()
        observeViewModel()

        tasksViewModel.getTasks()

        val sharedPref = activity?.getSharedPreferences("BPBDApp", Context.MODE_PRIVATE)
        val userRole = sharedPref?.getString("user_role", "operator")

        if (userRole == "sekretaris") {
            binding.fabCreateTask.visibility = View.VISIBLE
            binding.fabCreateTask.setOnClickListener {
                val intent = Intent(activity, CreateTaskActivity::class.java)
                startActivity(intent)
            }
        } else {
            binding.fabCreateTask.visibility = View.GONE
        }
    }

    private fun setupRecyclerView() {
        taskAdapter = TaskAdapter(emptyList())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = taskAdapter
        }
    }

    private fun observeViewModel() {
        tasksViewModel.tasks.observe(viewLifecycleOwner, { tasks ->
            taskAdapter = TaskAdapter(tasks)
            binding.recyclerView.adapter = taskAdapter
        })

        tasksViewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        tasksViewModel.errorMessage.observe(viewLifecycleOwner, { errorMessage ->
            Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
