package com.flatstack.android.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.OpenableColumns
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class FileUtils(private val context: Context) {

    fun getRealFileName(uri: Uri): String {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        val nameIndex: Int = cursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cursor.moveToFirst()
        return cursor.getString(nameIndex).apply { cursor.close() }
    }

    fun getFileMimeType(uri: Uri) =
        context.contentResolver.getType(uri) ?: ""

    fun getFileSizeInBytes(uri: Uri): Int =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(
                    context.contentResolver,
                    uri
                )
            ).byteCount
        } else {
            MediaStore.Images.Media.getBitmap(context.contentResolver, uri).byteCount
        }

    fun getFileFromContentUri(uri: Uri, filename: String) : File? {

        val file = File(context.cacheDir, filename)
        file.createNewFile()

        val bitmap = BitmapFactory.decodeStream(context.contentResolver.openInputStream(uri))
        var outputStream : FileOutputStream? = null
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, JPEG_COMPRESS_QUALITY, byteArrayOutputStream)
        val bitmapData = byteArrayOutputStream.toByteArray()
        runCatching {
            outputStream = FileOutputStream(file)
        }.onSuccess {
            runCatching {
                outputStream?.write(bitmapData)
                outputStream?.flush()
                outputStream?.close()
            }.onSuccess {
                return file
            }
        }
        return null
    }

    companion object {
        const val JPEG_COMPRESS_QUALITY = 100
    }
}
