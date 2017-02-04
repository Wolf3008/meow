package com.mycompany.meowcrm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "crmUser")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "pass"})
public class User implements Serializable {

    private long id;
    private String name;
    private String login;
    private String pass;
    private String extNum;

    private String newPass;

    private Set<Role> roles = new HashSet<>(0);

    public User() {
    }

    public User(long id, String name, String login, String pass) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.pass = pass;
    }

    public User(long id, String name, String login, String pass, Set<Role> roles,
            String extNum) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.pass = pass;
        this.roles = roles;
        this.extNum = extNum;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(length = 16)
    public String getExtNum() {
        return extNum;
    }

    public void setExtNum(String extNum) {
        this.extNum = extNum;
    }

    @Column(length = 128, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 64, nullable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    @Transient
    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

}
