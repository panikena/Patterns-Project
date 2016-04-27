package com.panikena.Models;

import javax.persistence.*;

/**
 * Created by Artem on 27.04.2016.
 */

@Entity
@Table(name = "users", schema = "cars")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idusers;

    @Basic
    @Column(name = "name")
    String name;

    @Basic
    @Column(name = "password")
    String password;

    public int getIdusers() {
        return idusers;
    }

    public void setIdusers(int idusers) {
        this.idusers = idusers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }
}
