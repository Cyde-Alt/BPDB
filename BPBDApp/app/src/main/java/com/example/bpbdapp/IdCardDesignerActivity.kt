package com.example.bpbdapp

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bpbdapp.databinding.ActivityIdCardDesignerBinding
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider

class IdCardDesignerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIdCardDesignerBinding
    private lateinit var viewModel: IdCardDesignerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdCardDesignerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(IdCardDesignerViewModel::class.java)

        binding.canvas.setOnDragListener(dragListener)

        observeViewModel()

        binding.addTextButton.setOnClickListener {
            viewModel.addTextElement()
        }

        binding.addQrcodeButton.setOnClickListener {
            viewModel.addQrCodeElement("content") // Contoh konten
        }

        binding.saveButton.setOnClickListener {
            viewModel.saveLayout(this)
            com.google.android.material.snackbar.Snackbar.make(binding.root, "Layout disimpan!", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).show()
        }

        binding.loadButton.setOnClickListener {
            viewModel.loadLayout(this)
        }

        // ... (Tombol lain akan diimplementasikan nanti)
    }

    private fun observeViewModel() {
        viewModel.elements.observe(this) { elements ->
            binding.canvas.removeAllViews()
            elements.forEach { element ->
                val view = createViewForElement(element)
                binding.canvas.addView(view)
            }
        }
    }

    private fun createViewForElement(element: IdCardElement): View {
        return when (element) {
            is IdCardElement.Text -> TextView(this).apply {
                text = element.text
                x = element.x
                y = element.y
                tag = element // Set tag untuk identifikasi saat drag-and-drop
                setOnLongClickListener(longClickListener)
            }
            is IdCardElement.Image -> ImageView(this).apply {
                setImageBitmap(element.bitmap)
                x = element.x
                y = element.y
                tag = element
                setOnLongClickListener(longClickListener)
            }
            is IdCardElement.QrCode -> ImageView(this).apply {
                try {
                    val barcodeEncoder = BarcodeEncoder()
                    val bitmap = barcodeEncoder.encodeBitmap(element.content, BarcodeFormat.QR_CODE, 400, 400)
                    setImageBitmap(bitmap)
                    x = element.x
                    y = element.y
                    tag = element
                    setOnLongClickListener(longClickListener)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private val longClickListener = View.OnLongClickListener {
        val item = ClipData.Item(it.tag as? CharSequence)
        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
        val data = ClipData("drag", mimeTypes, item)
        val dragShadowBuilder = View.DragShadowBuilder(it)
        it.startDragAndDrop(data, dragShadowBuilder, it, 0)
        it.visibility = View.INVISIBLE
        true
    }

    private val dragListener = View.OnDragListener { v, event ->
        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> true
            DragEvent.ACTION_DRAG_ENTERED -> {
                v.invalidate()
                true
            }
            DragEvent.ACTION_DRAG_LOCATION -> true
            DragEvent.ACTION_DRAG_EXITED -> {
                v.invalidate()
                true
            }
            DragEvent.ACTION_DROP -> {
                val view = event.localState as View
                val element = view.tag as IdCardElement
                viewModel.updateElementPosition(element, event.x, event.y)
                view.visibility = View.VISIBLE
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                val view = event.localState as View
                if (!event.result) {
                    view.visibility = View.VISIBLE
                }
                v.invalidate()
                true
            }
            else -> false
        }
    }
}
