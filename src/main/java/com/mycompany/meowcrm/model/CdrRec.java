package com.mycompany.meowcrm.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "cdr_rec",
        indexes = {
            @Index(columnList = "src"),
            @Index(columnList = "dst"),
            @Index(columnList = "uniqueId"),})
public class CdrRec implements Serializable {

    private long id;
    private String src;
    private String dst;
    private String uniqueId;
    private String fileName;

    public CdrRec() {
    }

    public CdrRec(long id, String src, String dst, String uniqueId) {
        this.id = id;
        this.uniqueId = uniqueId;
    }

    public CdrRec(long id, String src, String dst, String uniqueId, String fileName) {
        this.id = id;
        this.src = src;
        this.dst = dst;
        this.uniqueId = uniqueId;
        this.fileName = fileName;
    }

    @Id
    @Column(columnDefinition = "bigserial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(length = 150)
    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Column(length = 150)
    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    @Column(length = 150)
    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Column(length = 250)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
