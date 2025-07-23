package com.example.bpbdapp

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bpbdapp.databinding.ActivityIdCardDisplayBinding

class IdCardDisplayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIdCardDisplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdCardDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Fetch user data from API
        val userName = "John Doe"
        val userRole = "Operator"

        val bitmap = generateIdCard(userName, userRole)
        binding.idCardImage.setImageBitmap(bitmap)
    }

    private fun generateIdCard(name: String, role: String): Bitmap {
        val bitmap = Bitmap.createBitmap(500, 300, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()

        // Background
        paint.color = Color.WHITE
        canvas.drawRect(0f, 0f, 500f, 300f, paint)

        // Name
        paint.color = Color.BLACK
        paint.textSize = 32f
        canvas.drawText(name, 50f, 100f, paint)

        // Role
        paint.textSize = 24f
        canvas.drawText(role, 50f, 150f, paint)

        return bitmap
    }
}
