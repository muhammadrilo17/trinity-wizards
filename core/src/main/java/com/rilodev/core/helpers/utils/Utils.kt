package com.rilodev.core.helpers.utils

import android.content.Context
import okio.IOException
import java.io.InputStream
import java.nio.charset.Charset

object Utils {
    fun loadJsonFromAssets(context: Context, fileName: String): String? {
        return try {
            val inputStream: InputStream = context.assets.open("${fileName}.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}