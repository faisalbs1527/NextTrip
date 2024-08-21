package com.example.nexttrip.utils

import android.app.Activity
import android.content.Context
import android.graphics.pdf.PdfDocument
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.drawToBitmap

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
        View.MeasureSpec.makeMeasureSpec(1920, View.MeasureSpec.EXACTLY)
    )
    composeView.layout(0, 0, composeView.measuredWidth, composeView.measuredHeight)
    val bitmap = composeView.drawToBitmap()

    rootView?.removeView(composeView)

    // Create a PDF document
    val pdfDocument = PdfDocument()
    val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 1).create()
    val page = pdfDocument.startPage(pageInfo)
    page.canvas.drawBitmap(bitmap, 0f, 0f, null)
    pdfDocument.finishPage(page)

    // Save the PDF to a file
    ShareUtils.sharePdfToOthers(context, "ticket", pdfDocument)
    pdfDocument.close()
}