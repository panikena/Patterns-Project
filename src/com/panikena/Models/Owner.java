package com.panikena.Models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by Artem on 27.04.2016.
 */
@Entity
@Table(name = "owners")
public class Owner {

    public Owner() {
    }

    public Owner(String name, String country, Date dob,  String sex) {
        this.name = name;
        this.sex = sex;
        this.dob = dob;
        this.country = country;
    }

    @Id
    @Column(name ="name")
    String name;

    @Basic
    @Column(name = "sex")
    String sex;

    @Basic
    @Column(name = "dob")
    Date dob;

    @Basic
    @Column(name = "country")
    String country;

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public Date getDob() {
        return dob;
    }

    public String getCountry() {
        return country;
    }

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    Set<Car> cars;


    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
