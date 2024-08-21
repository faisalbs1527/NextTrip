package com.example.nexttrip.utils

import android.app.Activity
import android.content.Context
import android.graphics.pdf.PdfDocument
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.drawToBitmap
import savePdfToDownloads
import kotlin.math.ceil

fun createPdfFromComposable(context: Context, composable: @Composable () -> Unit) {
    val composeView = ComposeView(context).apply {
        setContent {
            composable()
        }
    }

    val activity = context as? Activity // Assuming context is an Activity
    val rootView = activity?.findViewById<ViewGroup>(android.R.id.content)
    rootView?.addView(composeView)

    // Capture Composable as a Bitmap
    composeView.measure(
        View.MeasureSpec.makeMeasureSpec(1080, View.MeasureSpec.EXACTLY),
        View.MeasureSpec.makeMeasureSpec(3840, View.MeasureSpec.AT_MOST)
    )
    composeView.layout(0, 0, composeView.measuredWidth, composeView.measuredHeight)
    val bitmap = composeView.drawToBitmap()

    val pageHeight = 1920
    val pageWidth = bitmap.width
    val contentHeight = bitmap.height
    val numberOfPages = ceil(contentHeight / pageHeight.toDouble()).toInt()

    rootView?.removeView(composeView)

    // Create a PDF document
    val pdfDocument = PdfDocument()
    var offset = 0

    for (pageNumber in 1..numberOfPages) {
        val pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber).create()
        val page = pdfDocument.startPage(pageInfo)
        page.canvas.drawBitmap(bitmap, 0f, -offset.toFloat(), null)
        pdfDocument.finishPage(page)
        offset += pageHeight
    }

    // Save the PDF to a file
    savePdfToDownloads(context,  pdfDocument)
    pdfDocument.close()
}