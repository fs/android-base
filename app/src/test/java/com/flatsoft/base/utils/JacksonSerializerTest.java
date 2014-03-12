package com.flatsoft.base.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.junit.Assert.assertEquals;

/**
 * Created by adelnizamutdinov on 12/03/2014
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class JacksonSerializerTest {
    JacksonSerializer jacksonSerializer = new JacksonSerializer(new ObjectMapper());

    @Data
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    static class Test1 implements Serializable {
        String test;
        Test2  yo;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    static class Test2 {
        int foo, bar;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    static class Test3 {
        float foo;
        Test2 test2;
    }

    @Test public void testSerializable() throws Exception {
        Test1 test1 = new Test1("foo", new Test2(2, 5));
        String json = jacksonSerializer.serialize(test1);
        assertEquals(test1, jacksonSerializer.deserialize(json, Test1.class));
        assertEquals(test1, jacksonSerializer.deserialize(json, new TypeReference<Test1>() {}));
    }

    @Test public void testObject() throws Exception {
        Test3 test3 = new Test3(2.5f, new Test2(5, 6));
        String json = jacksonSerializer.serialize(test3);
        assertEquals(test3, jacksonSerializer.deserialize(json, Test3.class));
        assertEquals(test3, jacksonSerializer.deserialize(json, new TypeReference<Test3>() {}));
    }
}
