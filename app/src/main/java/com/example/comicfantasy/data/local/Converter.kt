package com.example.comicfantasy.data.local

import androidx.room.TypeConverter
import com.example.comicfantasy.data.remote.DataX
import com.example.comicfantasy.data.remote.Results
import com.example.comicfantasy.data.remote.Thumbnail
import com.google.gson.Gson

class Converter {
    companion object {
        val gson = Gson()


        @TypeConverter
        @JvmStatic
        fun fromDataX(value: Results): String {
            return gson.toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toDataX(value: String): Results {
            return gson.fromJson(value, Results::class.java)
        }

        @TypeConverter
        @JvmStatic
        fun fromData(value: DataX): String {
            return gson.toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toData(value: String): DataX {
            return gson.fromJson(value, DataX::class.java)
        }


        @TypeConverter
        @JvmStatic
        fun fromQuotes(value: List<Results>): String {
            return gson.toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toQuotes(value: String): List<Results> {
            return gson.fromJson(value, Array<Results>::class.java).asList()
        }



        @TypeConverter
        @JvmStatic
        fun fromThumbnail(value: Thumbnail): String {
            return gson.toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toThumbnail(value: String): Thumbnail{
            return gson.fromJson(value, Thumbnail::class.java)
        }
    }
}