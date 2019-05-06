package `is`.qual.marshall

import com.google.gson.Gson

interface JsonData {
    fun toJson(): String = Gson().toJson(this)
}

inline fun <reified T : JsonData> String.toObject(): T = Gson().fromJson(this, T::class.java)
