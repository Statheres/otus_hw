package com.otus.hw.hw07.simpson.encoder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JsonEncoderFactory {
    private static final Map<Class<?>, JsonEncoder> primitiveEncoders = new HashMap<Class<?>, JsonEncoder>() {{
        put(Boolean.class, new BooleanEncoder());
        put(String.class, new StringEncoder());
        put(Double.class, new DoubleEncoder());
        put(Float.class, new FloatEncoder());
        put(Long.class, new LongEncoder());
        put(Integer.class, new LongEncoder());
        put(Short.class, new LongEncoder());
        put(Byte.class, new LongEncoder());
    }};

    private static final JsonEncoder arrayEncoder = new ArrayEncoder();
    private static final JsonEncoder collectionEncoder = new CollectionEncoder();
    private static final JsonEncoder mapEncoder = new MapEncoder();
    private static final JsonEncoder objectEncoder = new ObjectEncoder();

    public static JsonEncoder<?> createEncoder(Class<?> dataClass) {
        if (dataClass.isArray()) {
            return arrayEncoder;
        }

        if (Collection.class.isAssignableFrom(dataClass)) {
            return collectionEncoder;
        }

        if (Map.class.isAssignableFrom(dataClass)) {
            return mapEncoder;
        }

        JsonEncoder primitiveEncoder = primitiveEncoders.get(dataClass);
        if (!Objects.isNull(primitiveEncoder)) {
            return primitiveEncoder;
        }

        return objectEncoder;
    }
}
