package com.example.bpbdapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bpbdapp.databinding.ActivityThemeSettingsBinding

class ThemeSettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThemeSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.applyTheme(this)
        binding = ActivityThemeSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.applyButton.setOnClickListener {
            val selectedTheme = when (binding.themePresetsGroup.checkedRadioButtonId) {
                R.id.orange_theme -> ThemeManager.THEME_ORANGE
                R.id.gray_theme -> ThemeManager.THEME_GRAY
                else -> ThemeManager.THEME_BLUE
            }
            ThemeManager.setTheme(this, selectedTheme)
            recreate()
        }
    }
}
