package com.mycompany.meowcrm.model.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.meowcrm.model.User;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ClientNotice implements Serializable {

    private long id;
    private Date dtIn;
    private User manager;
    private String text;
    private Client client;

    public ClientNotice() {
    }

    public ClientNotice(long id, Date dtIn, User manager, String text, Client client) {
        this.id = id;
        this.dtIn = dtIn;
        this.manager = manager;
        this.text = text;
        this.client = client;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDtIn() {
        return dtIn;
    }

    public void setDtIn(Date dtIn) {
        this.dtIn = dtIn;
    }

    @ManyToOne
    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    @Column(nullable = false, length = 1024)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
