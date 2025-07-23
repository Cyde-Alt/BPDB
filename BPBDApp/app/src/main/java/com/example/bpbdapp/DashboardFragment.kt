package com.example.bpbdapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.Intent
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

        // Inflate the weather view stub
        val weatherView = binding.weatherViewStub.inflate()

        // Get weather data
        val weather = Weather("Jakarta", 32.0, "Cerah Berawan", "")

        // Bind weather data to the view
        weatherView.findViewById<TextView>(R.id.weather_location).text = weather.location
        weatherView.findViewById<TextView>(R.id.weather_temperature).text = "${weather.temperature.toInt()}°C"
        weatherView.findViewById<TextView>(R.id.weather_description).text = weather.description
        // TODO: Load weather icon using a library like Glide or Picasso

        val newsList = listOf(
            News("1", "Banjir di Jakarta", "Banjir merendam beberapa wilayah di Jakarta akibat hujan deras.", "", System.currentTimeMillis()),
            News("2", "Gunung Merapi Erupsi", "Gunung Merapi kembali erupsi dan mengeluarkan awan panas.", "", System.currentTimeMillis())
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = NewsAdapter(newsList)
        }

        binding.fabReport.setOnClickListener {
            val intent = Intent(activity, ReportActivity::class.java)
            startActivity(intent)
        }

        if (userRole == "super admin" || userRole == "operator") {
            binding.fabIdCard.visibility = View.VISIBLE
            binding.fabIdCard.setOnClickListener {
                val intent = Intent(activity, IdCardDesignerActivity::class.java)
                startActivity(intent)
            }
        } else {
            binding.fabIdCard.visibility = View.GONE
        }

        if (userRole == "super admin") {
            binding.fabThemeSettings.visibility = View.VISIBLE
            binding.fabThemeSettings.setOnClickListener {
                val intent = Intent(activity, ThemeSettingsActivity::class.java)
                startActivity(intent)
            }
        } else {
            binding.fabThemeSettings.visibility = View.GONE
        }

        if (userRole == "pimpinan") {
            // TODO: Add a button or menu item to open PlacementApprovalActivity
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
