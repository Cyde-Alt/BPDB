package com.example.bpbdapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bpbdapp.databinding.ActivityMainBinding
import android.content.Context
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("BPBDApp", Context.MODE_PRIVATE)
        val userRole = sharedPref.getString("user_role", "operator")

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)

        ThemeManager.applyTheme(this, bottomNavigationView)

        // I want to load the DashboardFragment by default
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
            DashboardFragment()).commit()

        // Customize menu visibility based on user role
        val menu = bottomNavigationView.menu
        when (userRole) {
            "pimpinan" -> {
                menu.findItem(R.id.nav_tasks).isVisible = false
            }
            "kepala bidang" -> {
                menu.findItem(R.id.nav_memo).isVisible = false
            }
            "sekretaris" -> {
                menu.findItem(R.id.nav_tasks).isVisible = false
            }
            "bendahara" -> {
                menu.findItem(R.id.nav_tasks).isVisible = false
                menu.findItem(R.id.nav_memo).isVisible = false
            }
            "operator" -> {
                menu.findItem(R.id.nav_memo).isVisible = false
                menu.findItem(R.id.nav_members).isVisible = false
            }
        }
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null

        when (item.itemId) {
            R.id.nav_dashboard -> selectedFragment = DashboardFragment()
            R.id.nav_members -> selectedFragment = MembersFragment()
            R.id.nav_tasks -> selectedFragment = TasksFragment()
            R.id.nav_memo -> selectedFragment = MemoFragment()
        }

        if (selectedFragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit()
        }

        true
    }
}
