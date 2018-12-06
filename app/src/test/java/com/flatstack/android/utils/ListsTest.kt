package com.flatstack.android.utils

import org.junit.Assert
import org.junit.Test
import rx.functions.Func1

import java.util.ArrayList

class ListsTest {

    @Test
    fun groupBy() {
        val testList = ArrayList<TestClass>()
        testList.add(TestClass("1", "kokoko"))
        testList.add(TestClass("3", "kekeke"))
        testList.add(TestClass("5", "kikiki"))
        testList.add(TestClass("7", "kakaka"))
        testList.add(TestClass("9", "kykyky"))

        val listListPair = Lists.groupBy(testList, Func1 { testClass -> testClass.id < "5" })

        Assert.assertEquals(2, listListPair.first?.size)
        Assert.assertEquals(3, listListPair.second?.size)
        Assert.assertEquals("kokoko", listListPair.first!![0].anotherField)
        Assert.assertEquals("kekeke", listListPair.first!![1].anotherField)

        Assert.assertEquals("kikiki", listListPair.second!![0].anotherField)
        Assert.assertEquals("kakaka", listListPair.second!![1].anotherField)
        Assert.assertEquals("kykyky", listListPair.second!![2].anotherField)
    }

    private class TestClass internal constructor(internal val id: String, internal val anotherField: String)
}
