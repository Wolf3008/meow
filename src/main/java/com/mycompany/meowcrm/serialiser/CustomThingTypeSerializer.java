package com.mycompany.meowcrm.serialiser;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.mycompany.meowcrm.model.deal.ThingType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomThingTypeSerializer extends StdSerializer<Set<ThingType>> {

    public CustomThingTypeSerializer() {
        this(null);
    }

    public CustomThingTypeSerializer(Class<Set<ThingType>> t) {
        super(t);
    }

    @Override
    public void serialize(Set<ThingType> t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonGenerationException {
        List<Map> cls = new ArrayList<>();
        for (ThingType item : t) {
            Map c = new HashMap();
            c.put("id", item.getId());
            c.put("name", item.getName());
            c.put("icon", item.getIcon());
            c.put("description", item.getDescription());
            c.put("cost", item.getCost());
            cls.add(c);
        }
        jg.writeObject(cls);
    }

}
