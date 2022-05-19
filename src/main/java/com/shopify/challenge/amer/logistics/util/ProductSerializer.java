package com.shopify.challenge.amer.logistics.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.shopify.challenge.amer.logistics.dto.Product;

import java.io.IOException;

public class ProductSerializer extends StdSerializer<Product> {
    protected ProductSerializer() {
        this(null);
    }

    protected ProductSerializer(Class<Product> t) {
        super(t);
    }

    @Override
    public void serialize(Product product, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException
    {
        jsonGenerator.writeString(""+product.getSKU());
    }
}
