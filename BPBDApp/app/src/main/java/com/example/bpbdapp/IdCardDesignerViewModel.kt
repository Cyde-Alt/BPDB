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
        val index = _elements.value?.indexOf(element)
        if (index != null && index != -1) {
            val newList = _elements.value!!.toMutableList()
            val updatedElement = when (element) {
                is IdCardElement.Text -> element.copy(x = x, y = y)
                is IdCardElement.Image -> element.copy(x = x, y = y)
                is IdCardElement.QrCode -> element.copy(x = x, y = y)
            }
            newList[index] = updatedElement
            _elements.value = newList
        }
    }

    fun saveLayout(context: android.content.Context) {
        val gson = com.google.gson.Gson()
        val json = gson.toJson(_elements.value)
        val sharedPref = context.getSharedPreferences("IdCardLayout", android.content.Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("layout", json)
            apply()
        }
    }

    fun loadLayout(context: android.content.Context) {
        val sharedPref = context.getSharedPreferences("IdCardLayout", android.content.Context.MODE_PRIVATE)
        val json = sharedPref.getString("layout", null)
        if (json != null) {
            val gson = com.google.gson.Gson()
            val type = object : com.google.gson.reflect.TypeToken<List<IdCardElement>>() {}.type
            _elements.value = gson.fromJson(json, type)
        }
    }
}
