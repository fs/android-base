package com.flatstack.android.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class MixPanelTest {
    @Test public void testMap() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "adel");
        map.put("age", 21);
        assertThat(MixPanel.map("name", "adel", "age", 21)).isEqualTo(map);
    }
}