package com.flatstack.android.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ereminilya on 19/4/17.
 */
public class CalculatorTest {
    @Test
    public void plus() throws Exception {
        Calculator calculator = new Calculator();
        Assert.assertTrue(calculator.plus(1, 3) >= 4);
    }

    @Test
    public void minus() throws Exception {
        Calculator calculator = new Calculator();
        Assert.assertTrue(calculator.minus(4, 1) >= 3);
    }
}