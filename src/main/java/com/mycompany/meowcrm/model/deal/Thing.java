package com.mycompany.meowcrm.model.deal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "deal"})
public class Thing implements Serializable {

    private long id;
    private String text;
    private int count;
    private int cost;
    private Date tdate;
    private Date dtIn;
    private ThingType type;
    private Deal deal;
    private boolean complite;

    public Thing() {
    }

    public Thing(long id, Date dtIn, ThingType type, Deal deal) {
        this.id = id;
        this.dtIn = dtIn;
        this.type = type;
        this.deal = deal;
    }

    public Thing(long id, String text, int count, int cost, Date tdate, Date dtIn, ThingType type, Deal deal, boolean complite) {
        this.id = id;
        this.text = text;
        this.count = count;
        this.cost = cost;
        this.tdate = tdate;
        this.dtIn = dtIn;
        this.type = type;
        this.deal = deal;
        this.complite = complite;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(length = 512)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Column
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTdate() {
        return tdate;
    }

    public void setTdate(Date tdate) {
        this.tdate = tdate;
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

    @ManyToOne(optional = false)
    public ThingType getType() {
        return type;
    }

    public void setType(ThingType type) {
        this.type = type;
    }

    @ManyToOne(optional = false)
    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    @Column
    public boolean isComplite() {
        return complite;
    }

    public void setComplite(boolean complite) {
        this.complite = complite;
    }

}
