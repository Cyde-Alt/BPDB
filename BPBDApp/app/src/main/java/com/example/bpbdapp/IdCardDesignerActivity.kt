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

class IdCardDesignerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIdCardDesignerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdCardDesignerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.canvas.setOnDragListener(dragListener)

        binding.addTextButton.setOnClickListener {
            val textView = TextView(this)
            textView.text = "Teks Baru"
            textView.setOnLongClickListener {
                val clipText = "this is a text"
                val item = ClipData.Item(clipText)
                val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                val data = ClipData(clipText, mimeTypes, item)

                val dragShadowBuilder = View.DragShadowBuilder(it)
                it.startDragAndDrop(data, dragShadowBuilder, it, 0)

                it.visibility = View.INVISIBLE
                true
            }
            binding.canvas.addView(textView)
        }

        binding.addImageButton.setOnClickListener {
            // TODO: Implement add image logic
        }

        binding.addQrcodeButton.setOnClickListener {
            try {
                val barcodeEncoder = BarcodeEncoder()
                val bitmap: Bitmap = barcodeEncoder.encodeBitmap("content", BarcodeFormat.QR_CODE, 400, 400)
                val imageView = ImageView(this)
                imageView.setImageBitmap(bitmap)
                binding.canvas.addView(imageView)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.saveButton.setOnClickListener {
            // TODO: Implement save logic
        }

        binding.loadButton.setOnClickListener {
            // TODO: Implement load logic
        }

        binding.generateButton.setOnClickListener {
            val bitmap = Bitmap.createBitmap(binding.canvas.width, binding.canvas.height, Bitmap.Config.ARGB_8888)
            val canvas = android.graphics.Canvas(bitmap)
            binding.canvas.draw(canvas)
            // TODO: Save or share the bitmap
        }
    }

    /**
     * Listener untuk menangani event drag-and-drop pada kanvas.
     */
    private val dragListener = View.OnDragListener { v, event ->
        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }
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
                val item = event.clipData.getItemAt(0)
                val dragData = item.text
                v.invalidate()

                val vw = event.localState as View
                val owner = vw.parent as ViewGroup
                owner.removeView(vw)
                val container = v as ConstraintLayout
                container.addView(vw)
                vw.x = event.x
                vw.y = event.y
                vw.visibility = View.VISIBLE
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                v.invalidate()
                true
            }
            else -> false
        }
    }
}
