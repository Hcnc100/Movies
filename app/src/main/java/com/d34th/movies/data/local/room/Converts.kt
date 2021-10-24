package com.d34th.movies.data.local.room

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class Converts {
    @TypeConverter
    fun fromBitMAP(bmp: Bitmap?):ByteArray?{
        if(bmp==null) return null
        val outputStream= ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG,100,outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(bytes:ByteArray?): Bitmap? {
        if(bytes==null) return null
        return BitmapFactory.decodeByteArray(bytes,0,bytes.size)
    }

}