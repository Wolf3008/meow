package com.mycompany.meowcrm.model.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.meowcrm.model.task.Task;
import com.mycompany.meowcrm.model.User;
import com.mycompany.meowcrm.model.deal.Deal;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OrderBy;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "notices"})
//@JsonIdentityInfo(
//  generator = ObjectIdGenerators.PropertyGenerator.class, 
//  property = "id")
public class Client implements Serializable {

    private long id;
    private String name;
    private Date dtIn;
    private String comment;
    private ClientType type;
    private ClientState state;

    private int version;

    private User manager;

    private Set<Contact> contacts = new HashSet<>(0);
    private Set<Task> tasks = new HashSet<>(0);
    private Set<ClientNotice> notices = new HashSet<>(0);

    private Set<Deal> deals = new HashSet<>(0);

    public Client() {
    }

    public Client(long id, Date dtIn) {
        this.id = id;
        this.dtIn = dtIn;
    }

    public Client(long id, String name, Date dtIn, String comment, ClientType type,
            ClientState state, int version, User manager, Set<Contact> contacts,
            Set<Task> tasks, Set<ClientNotice> notices, Set<Deal> deals) {
        this.id = id;
        this.name = name;
        this.dtIn = dtIn;
        this.comment = comment;
        this.type = type;
        this.state = state;
        this.version = version;
        this.manager = manager;
        this.contacts = contacts;
        this.tasks = tasks;
        this.notices = notices;
        this.deals = deals;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            nullable = false,
            insertable = false,
            updatable = false)
    public Date getDtIn() {
        return dtIn;
    }

    public void setDtIn(Date dtIn) {
        this.dtIn = dtIn;
    }

    @Column(length = 1024)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @ManyToOne
    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    @ManyToOne
    public ClientState getState() {
        return state;
    }

    public void setState(ClientState state) {
        this.state = state;
    }

    @Column
    @Version
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @ManyToOne
    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    @Cascade(CascadeType.ALL)
    //@OrderBy(clause = "id desc")
    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    @ManyToMany(mappedBy = "clients")
    @OrderBy(clause = "tasks_id desc")
    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    @OneToMany(mappedBy = "client", cascade = javax.persistence.CascadeType.REMOVE)
    //@Cascade(CascadeType.)
    @OrderBy(clause = "id desc")
    public Set<ClientNotice> getNotices() {
        return notices;
    }

    public void setNotices(Set<ClientNotice> notices) {
        this.notices = notices;
    }

    @OneToMany(mappedBy = "client", cascade = javax.persistence.CascadeType.REMOVE)
    @OrderBy(clause = "id desc")
    public Set<Deal> getDeals() {
        return deals;
    }

    public void setDeals(Set<Deal> deals) {
        this.deals = deals;
    }

}
