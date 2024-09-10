import android.content.ContentValues
import android.content.Context
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.nexttrip.utils.ticketDate
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun savePdfToDownloads(context: Context, pdfDocument: PdfDocument, fileName: String) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        // Use MediaStore for Android 10 and above
        val contentValues = ContentValues().apply {
            put(MediaStore.Downloads.DISPLAY_NAME, fileName) // File name
            put(MediaStore.Downloads.MIME_TYPE, "application/pdf") // MIME type
            put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS) // Directory
        }

        val uri: Uri? =
            context.contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)

        uri?.let {
            context.contentResolver.openOutputStream(it)?.use { outputStream ->
                pdfDocument.writeTo(outputStream)
            }
        }
    } else {
        // Handle for older versions if needed
        // Note: For Android 9 (API level 28) and below, you might use direct file paths
        savePdfToDownloadsLegacy(context, pdfDocument, fileName)
    }

    pdfDocument.close()
}


fun savePdfToDownloadsLegacy(context: Context, pdfDocument: PdfDocument, fileName: String) {
    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        val downloadsDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(downloadsDir, fileName)

        try {
            FileOutputStream(file).use { outputStream ->
                pdfDocument.writeTo(outputStream)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        pdfDocument.close()
    } else {
        // Request permissions if not granted
        ActivityCompat.requestPermissions(
            context as androidx.activity.ComponentActivity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            1
        )
    }
}
