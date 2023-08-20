package com.example.platzi_api.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PrefManager @Inject constructor(@ApplicationContext context: Context) {
    val pref = context.getSharedPreferences("pref",Context.MODE_PRIVATE)
    fun setPref(key: String,value:String){
        val prefEdit: SharedPreferences.Editor = pref.edit()
        prefEdit.putString(key,value)
        prefEdit.apply()
    }

    fun getPref(key:String): String{
        return pref.getString(key,"").toString()
    }

}