package com.mycompany.meowcrm.model.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.meowcrm.model.BasicMarker;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "contacts"})
public class ContactType extends BasicMarker implements Serializable {

    private Set<Contact> contacts = new HashSet<>(0);
    private boolean callable;

    public ContactType() {
    }

    public ContactType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ContactType(int id, String name, String icon, String description, Set<Contact> contacts, boolean callable) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.description = description;
        this.contacts = contacts;
        this.callable = callable;
    }

    @OneToMany(mappedBy = "type")
    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    @Column
    public boolean isCallable() {
        return callable;
    }

    public void setCallable(boolean callable) {
        this.callable = callable;
    }
}
