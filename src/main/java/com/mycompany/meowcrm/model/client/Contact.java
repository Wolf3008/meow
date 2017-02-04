package com.mycompany.meowcrm.model.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "client"})
public class Contact implements Serializable {

    private long id;
    private String contact;
    private String comment;
    private Client client;

    private ContactType type;

    public Contact() {
    }

    public Contact(long id, String contact, Client client, ContactType type) {
        this.id = id;
        this.contact = contact;
        this.client = client;
        this.type = type;
    }

    public Contact(long id, String contact, String comment, Client client, ContactType type) {
        this.id = id;
        this.contact = contact;
        this.comment = comment;
        this.client = client;
        this.type = type;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(length = 512)
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Column(length = 1024)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @ManyToOne
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @ManyToOne
    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }
}
