package com.flatstack.android.utils

import android.support.v4.util.Pair
import java.util.ArrayList
import java.util.HashSet
import org.jetbrains.annotations.Contract
import rx.functions.Action1
import rx.functions.Func1

object Lists {

    @Contract("null -> true")
    fun isEmpty(list: List<*>?): Boolean {
        return list == null || list.isEmpty()
    }

    /**
     * @return list of T which contains values which filter.call(item) returns true
     */
    @Contract(pure = true)
    fun <T> filterBy(
        list: List<T>,
        filter: Func1<T, Boolean>
    ): List<T> {
        val filteredList = ArrayList<T>()
        for (item in list) {
            if (filter.call(item)) {
                filteredList.add(item)
            }
        }
        return filteredList
    }

    @Contract(value = "null, null -> null; !null, _ -> !null; _, !null -> !null", pure = true)
    fun <T> merge(list1: List<T>?, list2: List<T>?): List<T>? {
        if (list1 == null && list2 == null) return null
        val finalList = ArrayList<T>()
        if (list1 != null) {
            finalList.addAll(list1)
        }
        if (list2 != null) {
            finalList.addAll(list2)
        }
        return finalList
    }

    @Contract(value = "null -> null; !null -> !null", pure = false)
    fun <T> uniq(list: MutableList<T>?): List<T>? {
        if (list != null) {
            val set = HashSet(list)
            list.clear()
            list.addAll(set)
        }
        return list
    }

    fun <T> forEach(list: List<T>, action: Action1<T>) {
        for (item in list) {
            action.call(item)
        }
    }

    /**
     * @return Pair of two lists. First list contains values which satisfy groupBy
     * i.e. condition.call(item) returns true. Second list contains rest of items from #list.
     */
    @Contract(pure = true)
    fun <T> groupBy(
        list: List<T>,
        condition: Func1<T, Boolean>
    ): Pair<List<T>, List<T>> {
        val list1 = ArrayList<T>()
        val list2 = ArrayList<T>()
        for (item in list) {
            if (condition.call(item)) {
                list1.add(item)
            } else {
                list2.add(item)
            }
        }
        return Pair(list1, list2)
    }
}
