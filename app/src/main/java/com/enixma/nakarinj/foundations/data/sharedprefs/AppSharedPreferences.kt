package com.enixma.nakarinj.foundations.data.sharedprefs

import android.content.Context
import android.content.SharedPreferences

import com.enixma.nakarinj.foundations.R


class AppSharedPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences = getSharedPreferences(context)

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.getString(R.string.SHAREDPREF_FILE), Context.MODE_PRIVATE)
    }

    fun putInt(key: String, data: Int) {
        sharedPreferences.edit().putInt(key, data).apply()
    }

    fun getInt(key: String, defValue: Int): Int {
        return sharedPreferences.getInt(key, defValue)
    }

    fun putFloat(key: String, data: Float) {
        sharedPreferences.edit().putFloat(key, data).apply()
    }

    fun getFloat(key: String, defValue: Float?): Float {
        return sharedPreferences.getFloat(key, defValue!!)
    }

    fun putLong(key: String, data: Long?) {
        sharedPreferences.edit().putLong(key, data!!).apply()
    }

    fun getLong(key: String, defValue: Long?): Long? {
        return sharedPreferences.getLong(key, defValue!!)
    }

    fun putString(key: String, data: String) {
        sharedPreferences.edit().putString(key, data).apply()
    }

    fun getString(key: String, defValue: String): String? {
        return sharedPreferences.getString(key, defValue)
    }

    fun putBoolean(key: String, data: Boolean) {
        sharedPreferences.edit().putBoolean(key, data).apply()
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    fun getAll(): Map<String, *> {
        return sharedPreferences.all
    }

    fun has(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

}