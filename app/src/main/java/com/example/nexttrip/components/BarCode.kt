package com.example.nexttrip.components

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.oned.Code128Writer
import com.google.zxing.common.BitMatrix
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned

@Composable
fun BarcodeView(text: String, modifier: Modifier) {
    var width by remember { mutableStateOf(20) }
    Box(modifier = modifier.onGloballyPositioned {
        width = it.size.width
    }) {
        val height = (width * 0.22).toInt()
        val bitmap = generateBarcode(text, width, height)
        bitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Barcode",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun PreviewBarcodeView() {
    MaterialTheme {
        Surface {
            BarcodeView(text = "123456789012", modifier = Modifier.fillMaxWidth())
        }
    }
}


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

