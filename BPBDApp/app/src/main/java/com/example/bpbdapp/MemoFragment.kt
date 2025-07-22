package com.example.bpbdapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bpbdapp.databinding.FragmentMemoBinding

class MemoFragment : Fragment() {

    private var _binding: FragmentMemoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val memos = listOf(
            Memo("1", "Peringatan Dini", "Waspada potensi hujan lebat di wilayah Jabodetabek.", System.currentTimeMillis()),
            Memo("2", "Kebutuhan Logistik", "Segera kirimkan bantuan logistik ke posko pengungsian.", System.currentTimeMillis())
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MemoAdapter(memos)
        }

        binding.fab.setOnClickListener {
            // TODO: Implement create memo functionality
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
