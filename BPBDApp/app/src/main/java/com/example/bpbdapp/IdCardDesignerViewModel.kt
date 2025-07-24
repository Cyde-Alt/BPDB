package com.example.bpbdapp

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

sealed class IdCardElement {
    data class Text(val text: String, var x: Float, var y: Float) : IdCardElement()
    data class Image(val bitmap: Bitmap, var x: Float, var y: Float) : IdCardElement()
    data class QrCode(val content: String, var x: Float, var y: Float) : IdCardElement()
}

class IdCardDesignerViewModel : ViewModel() {

    private val _elements = MutableLiveData<List<IdCardElement>>(emptyList())
    val elements: LiveData<List<IdCardElement>> = _elements

    fun addTextElement() {
        val currentList = _elements.value ?: emptyList()
        _elements.value = currentList + IdCardElement.Text("Teks Baru", 0f, 0f)
    }

    fun addQrCodeElement(content: String) {
        val currentList = _elements.value ?: emptyList()
        _elements.value = currentList + IdCardElement.QrCode(content, 0f, 0f)
    }

    fun updateElementPosition(element: IdCardElement, x: Float, y: Float) {
        element.apply {
            when (this) {
                is IdCardElement.Text -> {
                    this.x = x
                    this.y = y
                }
                is IdCardElement.Image -> {
                    this.x = x
                    this.y = y
                }
                is IdCardElement.QrCode -> {
                    this.x = x
                    this.y = y
                }
            }
        }
        _elements.value = _elements.value // Trigger update
    }

    // TODO: Implement add image, save, and load logic
}
