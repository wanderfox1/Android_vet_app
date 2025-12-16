package com.example.jetpack.store.utils

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

object ImageUtils {

  fun byteArrayToImageBitmap(byteArray: ByteArray?): ImageBitmap? {
    return byteArray?.let {
      try {
        val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
        bitmap?.asImageBitmap()
      } catch (e: Exception) {
        null
      }
    }
  }
}