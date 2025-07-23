package com.example.bpbdapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bpbdapp.databinding.FragmentMembersBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        if (userRole == "operator") {
            binding.fabPlaceMember.visibility = View.VISIBLE
            binding.fabPlaceMember.setOnClickListener {
                val intent = Intent(activity, PlaceMemberActivity::class.java)
                startActivity(intent)
            }
        } else {
            binding.fabPlaceMember.visibility = View.GONE
        }

        if (userRole == "kepala bidang") {
            // TODO: Get user's division ID
            val divisionId = 1
            fetchMembers(divisionId)
        } else {
            fetchMembers()
        }
    }

    private fun fetchMembers(divisionId: Int? = null) {
        binding.progressBar.visibility = View.VISIBLE
        val call = if (divisionId != null) {
            // TODO: Create getMembersByDivision endpoint
            // ApiClient.instance.getMembersByDivision(divisionId)
            ApiClient.instance.getMembers()
        } else {
            ApiClient.instance.getMembers()
        }
        call.enqueue(object : Callback<List<Member>> {
            override fun onResponse(call: Call<List<Member>>, response: Response<List<Member>>) {
                binding.progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    val members = response.body()
                    if (members != null) {
                        binding.recyclerView.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = MemberAdapter(members)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Member>>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                Log.e("MembersFragment", "Error fetching members", t)
                // TODO: Show a user-friendly error message
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
