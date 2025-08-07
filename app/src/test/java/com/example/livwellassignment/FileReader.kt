package com.example.livwellassignment

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStreamReader
import java.lang.reflect.Type

object FileReader {

    fun readFileResource(fileName: String): String {
        val inputStream = FileReader::class.java.getResourceAsStream(fileName)
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, "UTF-8")
        reader.readLines().forEach {
            builder.append(it)
        }

        return builder.toString()
    }

    inline fun <reified T> stringToJson(str: String?): T? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val type: Type = T::class.java
        val adapter = moshi.adapter<T>(type)
        return str?.let { adapter.fromJson(it) }
    }
}