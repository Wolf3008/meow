package com.mycompany.meowcrm.serialiser;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.mycompany.meowcrm.model.client.Client;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomClientSerializer extends StdSerializer<Client> {

    public CustomClientSerializer() {
        this(null);
    }

    public CustomClientSerializer(Class<Client> t) {
        super(t);
    }

    @Override
    public void serialize(Client item, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonGenerationException {
        Map c = new HashMap();
        c.put("id", item.getId());
        c.put("name", item.getName());
        c.put("comment", item.getComment());
        jg.writeObject(c);
    }

}
