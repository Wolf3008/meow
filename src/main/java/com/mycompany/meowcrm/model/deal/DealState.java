package com.mycompany.meowcrm.model.deal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.meowcrm.model.BasicMarker;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "deals"})
public class DealState extends BasicMarker implements Serializable {

    private Set<Deal> deals = new HashSet<>(0);

    public DealState() {
    }

    public DealState(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DealState(int id, String name, String icon, String description, Set<Deal> deals) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.description = description;
        this.deals = deals;
    }

    @OneToMany(mappedBy = "state")
    public Set<Deal> getDeals() {
        return deals;
    }

    public void setDeals(Set<Deal> deals) {
        this.deals = deals;
    }

}
