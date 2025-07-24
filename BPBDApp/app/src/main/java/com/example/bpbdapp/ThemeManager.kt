package com.example.bpbdapp

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

object ThemeManager {
    const val THEME_BLUE = 0
    const val THEME_ORANGE = 1
    const val THEME_GRAY = 2

    fun applyTheme(activity: android.app.Activity) {
        val sharedPref = activity.getSharedPreferences("ThemePref", Context.MODE_PRIVATE)
        val theme = sharedPref.getInt("theme", THEME_BLUE)
        activity.setTheme(getThemeResource(theme))
    }

    fun setTheme(context: Context, theme: Int) {
        val sharedPref = context.getSharedPreferences("ThemePref", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt("theme", theme)
            apply()
        }
    }

    fun getThemeResource(theme: Int): Int {
        return when (theme) {
            THEME_ORANGE -> R.style.Theme_BPBDApp_Orange
            THEME_GRAY -> R.style.Theme_BPBDApp_Gray
            else -> R.style.Theme_BPBDApp_Blue
        }
    }
}
