package com.panikena.Models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by Artem on 27.04.2016.
 */
@Entity
@Table(name = "owners", schema = "")
public class Owner {

    @Id
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

}
