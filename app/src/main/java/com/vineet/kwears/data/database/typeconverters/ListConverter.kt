package com.vineet.kwears.data.database.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vineet.kwears.data.database.dataentity.Source

class ListConverter {
    @TypeConverter
    fun fromStringToList(value:String):List<Source>{
        val listType = object :
            TypeToken<List<Source>>() {}.type
        return Gson().fromJson(value,listType)
    }
    @TypeConverter
    fun fromListToString(value: List<Source>):String{
        return Gson().toJson(value)
    }
}