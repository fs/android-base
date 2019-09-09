package com.flatstack.android.utils.storage

import com.flatstack.android.util.storage.IStorage
import java.lang.reflect.Type
import java.util.*

class RuntimeStorage : IStorage {

    private val map = HashMap<String, Any>()

    override fun <T> get(key: String, clazz: Type): T? {
        return map[key] as T?
    }

    override fun putLong(key: String, number: Long) {
        map[key] = java.lang.Long.valueOf(number)
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        val o = map[key]
        return if (o != null) o as Long else defaultValue
    }

    override fun putInt(key: String, number: Int) {
        map[key] = Integer.valueOf(number)
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        val o = map[key]
        return if (o != null) o as Int else defaultValue
    }

    override fun putBoolean(key: String, value: Boolean) {
        map[key] = java.lang.Boolean.valueOf(value)
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        val o = map[key]
        return if (o != null) o as Boolean else defaultValue
    }

    override fun putString(key: String, str: String) {
        map[key] = str
    }

    override fun getString(key: String): String? {
        val o = map[key]
        return if (o != null) o as String? else null
    }

    override fun remove(key: String) {
        map.remove(key)
    }

    override fun <T> getCollection(key: String, type: Type): List<T>? {
        return map[key] as List<T>?
    }

    override fun put(key: String, items: Any) {
        map[key] = items
    }

    override fun <T> putCollection(key: String, items: List<T>) {
        map[key] = items
    }
}
