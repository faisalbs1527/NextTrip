package com.example.nexttrip.utils

import android.content.Context
import android.content.Intent
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class ShareUtils {

    companion object{
        private fun savePdfAndGetUri(context: Context, pdfDocument: PdfDocument): Uri? {
            val fileDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
            val file = File(fileDir, "document.pdf")

            try {
                FileOutputStream(file).use { outputStream ->
                    pdfDocument.writeTo(outputStream)
                }
                pdfDocument.close()
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }

            // Return the URI for the saved file using FileProvider
            return FileProvider.getUriForFile(
                context, "${context.packageName}.provider", file
            )
        }

        fun sharePdfToOthers(context: Context, text: String?, pdfDocument: PdfDocument?) {
            val pdfUri: Uri? = pdfDocument?.let { savePdfAndGetUri(context, it) }
            val chooserIntent = Intent(Intent.ACTION_SEND).apply {
                type = "application/pdf"
                putExtra(Intent.EXTRA_TEXT, text)
                putExtra(Intent.EXTRA_STREAM, pdfUri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            try {
                context.startActivity(Intent.createChooser(chooserIntent, "Share PDF"))
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}