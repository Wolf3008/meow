package com.mycompany.meowcrm.model.deal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mycompany.meowcrm.model.client.Client;
import com.mycompany.meowcrm.model.User;
import com.mycompany.meowcrm.model.task.Task;
import com.mycompany.meowcrm.serialiser.CustomClientSerializer;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.hibernate.annotations.OrderBy;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Deal implements Serializable {

    private long id;
    private String name;
    private Date dtIn;
    private Date ddate;
    private Client client;
    private User manager;
    private int cost;
    private DealState state;
    private DealType type;
    private String comment;

    private int version;

    private Set<Thing> things = new HashSet<>(0);
    private Set<Task> tasks = new HashSet<>(0);

    public Deal() {
    }

    public Deal(long id, Date dtIn, Client client, User manager) {
        this.id = id;
        this.dtIn = dtIn;
        this.client = client;
        this.manager = manager;
    }

    public Deal(long id, String name, Date dtIn, Date date, Client client,
            User manager, int cost, DealState state, Set<Thing> things, int version,
            DealType type, String comment, Set<Task> tasks) {
        this.id = id;
        this.name = name;
        this.dtIn = dtIn;
        this.ddate = date;
        this.client = client;
        this.manager = manager;
        this.cost = cost;
        this.state = state;
        this.things = things;
        this.type = type;
        this.comment = comment;
        this.tasks = tasks;

        this.version = version;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            nullable = false,
            insertable = false,
            updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDtIn() {
        return dtIn;
    }

    public void setDtIn(Date dtIn) {
        this.dtIn = dtIn;
    }

    @Column
    @Version
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date date) {
        this.ddate = date;
    }

    @JsonSerialize(using = CustomClientSerializer.class)
    @ManyToOne(optional = false)
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @ManyToOne(optional = false)
    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    @Column
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Column(length = 1024)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @ManyToOne
    public DealState getState() {
        return state;
    }

    public void setState(DealState state) {
        this.state = state;
    }

    @OneToMany(mappedBy = "deal", orphanRemoval = true, cascade = CascadeType.ALL)
    @OrderBy(clause = "id desc")
    public Set<Thing> getThings() {
        return things;
    }

    public void setThings(Set<Thing> things) {
        this.things = things;
    }

    @ManyToOne
    public DealType getType() {
        return type;
    }

    public void setType(DealType type) {
        this.type = type;
    }

    @ManyToMany(mappedBy = "deals")
    @OrderBy(clause = "tasks_id desc")
    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

}
