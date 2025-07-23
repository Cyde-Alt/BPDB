package com.example.bpbdapp

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

object ThemeManager {

    fun applyTheme(context: Context, bottomNavigationView: BottomNavigationView) {
        // TODO: Fetch active theme from API
        // For now, let's use a dummy theme
        val primaryColor = Color.parseColor("#FF0000") // Red
        val secondaryColor = Color.parseColor("#FFFFFF") // White

        val colorStateList = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf(-android.R.attr.state_checked)
            ),
            intArrayOf(
                primaryColor,
                secondaryColor
            )
        )

        bottomNavigationView.itemIconTintList = colorStateList
        bottomNavigationView.itemTextColor = colorStateList
    }
}
