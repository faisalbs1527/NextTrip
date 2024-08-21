package com.example.nexttrip.components

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.oned.Code128Writer
import com.google.zxing.common.BitMatrix

fun generateBarcode(text: String, width: Int, height: Int): Bitmap? {
    return try {
        val writer = Code128Writer() // Use QRCodeWriter() for QR codes
        val bitMatrix: BitMatrix = writer.encode(text, BarcodeFormat.CODE_128, width, height)
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(
                    x,
                    y,
                    if (bitMatrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE
                )
            }
        }
        bitmap
    } catch (e: WriterException) {
        e.printStackTrace()
        null
    }
}

