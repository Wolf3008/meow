package com.mycompany.meowcrm.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(indexes = {
    @Index(name = "my_calldate_index", columnList = "calldate", unique = false),
    @Index(name = "my_src_index", columnList = "src", unique = false),
    @Index(name = "my_dst_index", columnList = "dst", unique = false)
})
public class Cdr implements Serializable {
//  calldate timestamp without time zone NOT NULL,  +
//  clid character varying(80) NOT NULL,            +
//  src character varying(80) NOT NULL,             +
//  dst character varying(80) NOT NULL,             +
//  dcontext character varying(80) NOT NULL,        +
//  channel character varying(80) NOT NULL,         +
//  dstchannel character varying(80) NOT NULL,      +
//  lastapp character varying(80) NOT NULL,         +
//  lastdata character varying(80) NOT NULL,        +
//  duration integer NOT NULL,                      +
//  billsec integer NOT NULL,                       +
//  disposition character varying(45) NOT NULL,     +
//  amaflags integer NOT NULL,                      +
//  accountcode character varying(20) NOT NULL,     +
//  uniqueid character varying(150) NOT NULL,       +
//  userfield character varying(255) NOT NULL,      +
//  id bigserial NOT NULL,                          +
//  filename character varying(120),                +

    private long id;
    private Date calldate;
    private String clid;
    private String src;
    private String dst;
    private String dcontext;
    private String channel;
    private String dstchannel;
    private String lastapp;
    private String lastdata;
    private int duration;
    private int billsec;
    private String disposition;
    private int amaflags;
    private String accountcode;
    private String uniqueid;
    private String userfield;
    private String filename;

    public Cdr() {
    }

    public Cdr(long id, Date calldate, String clid, String src, String dst, String dcontext, String channel, String dstchannel, String lastapp, String lastdata, int duration, int billsec, String disposition, int amaflags, String accountcode, String uniqueid, String userfield) {
        this.id = id;
        this.calldate = calldate;
        this.clid = clid;
        this.src = src;
        this.dst = dst;
        this.dcontext = dcontext;
        this.channel = channel;
        this.dstchannel = dstchannel;
        this.lastapp = lastapp;
        this.lastdata = lastdata;
        this.duration = duration;
        this.billsec = billsec;
        this.disposition = disposition;
        this.amaflags = amaflags;
        this.accountcode = accountcode;
        this.uniqueid = uniqueid;
        this.userfield = userfield;
    }

    public Cdr(long id, Date calldate, String clid, String src, String dst, String dcontext, String channel, String dstchannel, String lastapp, String lastdata, int duration, int billsec, String disposition, int amaflags, String accountcode, String uniqueid, String userfield, String filename) {
        this.id = id;
        this.calldate = calldate;
        this.clid = clid;
        this.src = src;
        this.dst = dst;
        this.dcontext = dcontext;
        this.channel = channel;
        this.dstchannel = dstchannel;
        this.lastapp = lastapp;
        this.lastdata = lastdata;
        this.duration = duration;
        this.billsec = billsec;
        this.disposition = disposition;
        this.amaflags = amaflags;
        this.accountcode = accountcode;
        this.uniqueid = uniqueid;
        this.userfield = userfield;
        this.filename = filename;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    //@Index()
    public Date getCalldate() {
        return calldate;
    }

    public void setCalldate(Date calldate) {
        this.calldate = calldate;
    }

    @Column(nullable = false, length = 80)
    public String getClid() {
        return clid;
    }

    public void setClid(String clid) {
        this.clid = clid;
    }

    @Column(nullable = false, length = 80)
    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Column(nullable = false, length = 80)
    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    @Column(nullable = false, length = 80)
    public String getDcontext() {
        return dcontext;
    }

    public void setDcontext(String dcontext) {
        this.dcontext = dcontext;
    }

    @Column(nullable = false, length = 80)
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Column(nullable = false, length = 80)
    public String getDstchannel() {
        return dstchannel;
    }

    public void setDstchannel(String dstchannel) {
        this.dstchannel = dstchannel;
    }

    @Column(nullable = false, length = 80)
    public String getLastapp() {
        return lastapp;
    }

    public void setLastapp(String lastapp) {
        this.lastapp = lastapp;
    }

    @Column(nullable = false, length = 80)
    public String getLastdata() {
        return lastdata;
    }

    public void setLastdata(String lastdata) {
        this.lastdata = lastdata;
    }

    @Column(nullable = false)
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Column(nullable = false)
    public int getBillsec() {
        return billsec;
    }

    public void setBillsec(int billsec) {
        this.billsec = billsec;
    }

    @Column(nullable = false, length = 45)
    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    @Column(nullable = false)
    public int getAmaflags() {
        return amaflags;
    }

    public void setAmaflags(int amaflags) {
        this.amaflags = amaflags;
    }

    @Column(nullable = false, length = 20)
    public String getAccountcode() {
        return accountcode;
    }

    public void setAccountcode(String accountcode) {
        this.accountcode = accountcode;
    }

    @Column(nullable = false, length = 150)
    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    @Column(nullable = false, length = 255)
    public String getUserfield() {
        return userfield;
    }

    public void setUserfield(String userfield) {
        this.userfield = userfield;
    }

    @Column(length = 120)
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
