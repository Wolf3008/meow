package com.mycompany.meowcrm.serialiser;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.mycompany.meowcrm.model.client.Client;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomClientSetSerializer extends StdSerializer<Set<Client>> {

    public CustomClientSetSerializer() {
        this(null);
    }

    public CustomClientSetSerializer(Class<Set<Client>> t) {
        super(t);
    }

    @Override
    public void serialize(Set<Client> t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonGenerationException {
        List<Map> cls = new ArrayList<>();
        for (Client item : t) {
            Map c = new HashMap();
            c.put("id", item.getId());
            c.put("name", item.getName());
            c.put("comment", item.getComment());
            cls.add(c);
        }
        jg.writeObject(cls);
    }

}
