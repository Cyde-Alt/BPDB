package com.example.bpbdapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bpbdapp.databinding.FragmentMemoBinding

import android.content.Intent
import androidx.lifecycle.ViewModelProvider

class MemoFragment : Fragment() {

    private var _binding: FragmentMemoBinding? = null
    private val binding get() = _binding!!
    private lateinit var memoViewModel: MemoViewModel
    private lateinit var memoAdapter: MemoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        memoViewModel = ViewModelProvider(this).get(MemoViewModel::class.java)

        setupRecyclerView()
        observeViewModel()

        binding.fab.setOnClickListener {
            val intent = Intent(activity, CreateMemoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        memoAdapter = MemoAdapter(emptyList())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = memoAdapter
        }
    }

    private fun observeViewModel() {
        memoViewModel.allMemos.observe(viewLifecycleOwner, { memoEntities ->
            val memoList = memoEntities.map {
                Memo(it.id, it.title, it.message, it.status, it.createdAt)
            }

            if (memoList.isNullOrEmpty()) {
                binding.recyclerView.visibility = View.GONE
                binding.emptyView.visibility = View.VISIBLE
            } else {
                binding.recyclerView.visibility = View.VISIBLE
                binding.emptyView.visibility = View.GONE
                memoAdapter = MemoAdapter(memoList)
                binding.recyclerView.adapter = memoAdapter
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
