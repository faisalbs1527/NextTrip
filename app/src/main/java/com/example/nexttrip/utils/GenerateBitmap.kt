package com.example.nexttrip.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.drawToBitmap

fun createBitmapFromComposable(context: Context, composable: @Composable () -> Unit): Bitmap {
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

    rootView?.removeView(composeView)

    return bitmap
}