package com.example.createdynamictextview.objectUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ConvertObject2HashMap {

    public ConvertObject2HashMap() {
    }

    public static Map<String, Object> getFieldNamesValueMap(final Object object)
            throws IllegalArgumentException, IllegalAccessException {
        Class<? extends Object> c1 = object.getClass();
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = c1.getDeclaredFields();
        for (int i = 0; i < fields.length - 2; i++) {
            String name = fields[i].getName();

            fields[i].setAccessible(true);
            Object value = fields[i].get(object);
            map.put(name, value);
        }
        return map;
    }
}

/**
 * fields.length - 2. Cause of Pojo Class $change & servialVersionUid..
 *
 */
