package com.mycompany.meowcrm.model.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.meowcrm.model.BasicMarker;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "clients"})
public class ClientType extends BasicMarker implements Serializable {

    private Set<Client> clients = new HashSet<>(0);

    public ClientType() {
    }

    public ClientType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ClientType(int id, String name, String icon, String description, Set<Client> clients) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.description = description;
        this.clients = clients;
    }

    @OneToMany(mappedBy = "type")
    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}
