package com.mycompany.meowcrm.model.task;

import com.mycompany.meowcrm.model.client.Client;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mycompany.meowcrm.model.User;
import com.mycompany.meowcrm.model.deal.Deal;
import com.mycompany.meowcrm.serialiser.CustomClientSetSerializer;
import com.mycompany.meowcrm.serialiser.CustomDealSetSerializer;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.OrderBy;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonIdentityInfo(
//  generator = ObjectIdGenerators.PropertyGenerator.class, 
//  property = "id")
public class Task implements Serializable {

    private long id;
    private Date dtIn;
    private Date date;
    private Date remindingDate;
    private String text;

    private TaskState state;
    private TaskType type;

    private Boolean executable;

    private User responsibleUser;

    private Set<Client> clients;
    private Set<Deal> deals;

    public Task() {
    }

    public Task(long id, Date dtIn, Date date) {
        this.id = id;
        this.dtIn = dtIn;
        this.date = date;
    }

    public Task(long id, Date dtIn, Date date, Date remindingDate, String text,
            Boolean executable, User responsibleUser, Set<Client> clients,
            TaskState state, TaskType type, Set<Deal> deals) {
        this.id = id;
        this.dtIn = dtIn;
        this.date = date;
        this.remindingDate = remindingDate;
        this.text = text;
        this.executable = executable;
        this.responsibleUser = responsibleUser;
        this.clients = clients;
        this.deals = deals;

        this.state = state;
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

    @Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column
    public Date getRemindingDate() {
        return remindingDate;
    }

    public void setRemindingDate(Date remindingDate) {
        this.remindingDate = remindingDate;
    }

    @Column
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column
    public Boolean getExecutable() {
        return executable;
    }

    public void setExecutable(Boolean executable) {
        this.executable = executable;
    }

    @ManyToOne
    public User getResponsibleUser() {
        return responsibleUser;
    }

    public void setResponsibleUser(User responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    @JsonSerialize(using = CustomClientSetSerializer.class)
    @ManyToMany
    @OrderBy(clause = "clients_id desc")
    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    @ManyToOne
    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    @ManyToOne
    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    @JsonSerialize(using = CustomDealSetSerializer.class)
    @ManyToMany
    @OrderBy(clause = "deals_id desc")
    public Set<Deal> getDeals() {
        return deals;
    }

    public void setDeals(Set<Deal> deals) {
        this.deals = deals;
    }

}
