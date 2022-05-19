package com.shopify.challenge.amer.logistics.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.shopify.challenge.amer.logistics.dto.Product;
import com.shopify.challenge.amer.logistics.dto.Warehouse;

import java.io.IOException;

public class WarehouseSerializer extends StdSerializer<Warehouse> {

    protected WarehouseSerializer() {
        this(null);
    }

    protected WarehouseSerializer(Class<Warehouse> t) {
        super(t);
    }

    @Override
    public void serialize(Warehouse warehouse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException
    {
        jsonGenerator.writeString(""+warehouse.getId());
    }
}