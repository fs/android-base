package com.flatstack.android.utils;

import android.support.v4.util.Pair;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ereminilya on 8/1/17.
 */
public class ListsTest {

    @Test
    public void groupBy() {
        List<TestClass> testList = new ArrayList<>();
        testList.add(new TestClass("1", "kokoko"));
        testList.add(new TestClass("3", "kekeke"));
        testList.add(new TestClass("5", "kikiki"));
        testList.add(new TestClass("7", "kakaka"));
        testList.add(new TestClass("9", "kykyky"));

        Pair<List<TestClass>, List<TestClass>> listListPair =
                Lists.groupBy(testList, testClass -> testClass.id.compareTo("5") < 0);

        Assert.assertEquals(2, listListPair.first.size());
        Assert.assertEquals(3, listListPair.second.size());
        Assert.assertEquals("kokoko", listListPair.first.get(0).anotherField);
        Assert.assertEquals("kekeke", listListPair.first.get(1).anotherField);

        Assert.assertEquals("kikiki", listListPair.second.get(0).anotherField);
        Assert.assertEquals("kakaka", listListPair.second.get(1).anotherField);
        Assert.assertEquals("kykyky", listListPair.second.get(2).anotherField);
    }

    private static class TestClass {

        private String id;
        private String anotherField;

        TestClass(String id, String anotherField) {
            this.id = id;
            this.anotherField = anotherField;
        }
    }
}