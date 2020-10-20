package com.flatstack.android.util.storage

import android.content.SharedPreferences

import com.google.gson.Gson

import java.lang.reflect.Type

@Suppress("TooManyFunctions")
class Storage(private val sp: SharedPreferences, private val gson: Gson) : IStorage {

    override fun <T> get(key: String, type: Type): T? = getString(key)?.let {
        if (it.isEmpty()) {
            null
        } else {
            gson.fromJson<T>(it, type)
        }
    }

    override fun put(key: String, items: Any) {
        sp.edit().putString(key, gson.toJson(items)).apply()
    }

    override fun putString(key: String, str: String) {
        sp.edit().putString(key, str).apply()
    }

    override fun getString(key: String): String? = sp.getString(key, null)

    override fun putLong(key: String, number: Long) {
        sp.edit().putLong(key, number).apply()
    }

    override fun getLong(key: String, defaultValue: Long) = sp.getLong(key, defaultValue)

    override fun putInt(key: String, number: Int) {
        sp.edit().putInt(key, number).apply()
    }

    override fun getInt(key: String, defaultValue: Int) = sp.getInt(key, defaultValue)

    override fun putBoolean(key: String, value: Boolean) {
        sp.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String, defaultValue: Boolean) = sp.getBoolean(key, defaultValue)

    override fun remove(key: String) {
        sp.edit().remove(key).apply()
    }

    override fun <T> putCollection(key: String, items: List<T>) {
        put(key, items)
    }

    override fun <T> getCollection(key: String, type: Type) = get<List<T>>(key, type)
}
