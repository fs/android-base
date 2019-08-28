package com.flatstack.android.util.storage

import java.lang.reflect.Type

@Suppress("TooManyFunctions")
interface IStorage {

    operator fun <T> get(key: String, type: Type): T?

    fun put(key: String, items: Any)

    fun putString(key: String, str: String)

    fun getString(key: String): String?

    fun putLong(key: String, number: Long)

    fun getLong(key: String, defaultValue: Long): Long

    fun putInt(key: String, number: Int)

    fun getInt(key: String, defaultValue: Int): Int

    fun putBoolean(key: String, value: Boolean)

    fun getBoolean(key: String, defaultValue: Boolean): Boolean

    fun remove(key: String)

    fun <T> putCollection(key: String, items: List<T>)

    fun <T> getCollection(key: String, type: Type): List<T>?
}
