package com.mycompany.meowcrm.model.deal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mycompany.meowcrm.model.BasicMarker;
import com.mycompany.meowcrm.serialiser.CustomThingTypeSerializer;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.hibernate.annotations.OrderBy;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "deals"})
public class DealType extends BasicMarker implements Serializable {

    private Set<ThingType> thingTypes = new HashSet<>(0);
    private Set<Deal> deals = new HashSet<>(0);

    public DealType() {
    }

    public DealType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DealType(int id, String name, String icon, String description, Set<ThingType> thingTypes, Set<Deal> deals) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.description = description;
        this.thingTypes = thingTypes;
        this.deals = deals;
    }

    @JsonSerialize(using = CustomThingTypeSerializer.class)
    @OneToMany(mappedBy = "dealType")
    @OrderBy(clause = "id")
    public Set<ThingType> getThingTypes() {
        return thingTypes;
    }

    public void setThingTypes(Set<ThingType> thingTypes) {
        this.thingTypes = thingTypes;
    }

    @OneToMany(mappedBy = "type")
    public Set<Deal> getDeals() {
        return deals;
    }

    public void setDeals(Set<Deal> deals) {
        this.deals = deals;
    }
}
