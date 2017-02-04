package com.mycompany.meowcrm.model.deal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.meowcrm.model.BasicMarker;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "things"})
public class ThingType extends BasicMarker implements Serializable {

    private Set<Thing> things = new HashSet<>(0);
    private DealType dealType;
    private int cost;

    public ThingType() {
    }

    public ThingType(int id, String name, DealType dealType) {
        this.id = id;
        this.name = name;
        this.dealType = dealType;
    }

    public ThingType(int id, String name, String icon, String description, Set<Thing> things, DealType dealType, int cost) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.description = description;
        this.things = things;
        this.dealType = dealType;
        this.cost = cost;
    }

    @Column
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @OneToMany(mappedBy = "type")
    public Set<Thing> getThings() {
        return things;
    }

    public void setThings(Set<Thing> things) {
        this.things = things;
    }

    //@JsonIgnore
    @ManyToOne
    public DealType getDealType() {
        return dealType;
    }

    //@JsonProperty
    public void setDealType(DealType dealType) {
        this.dealType = dealType;
    }
}
