package com.higgsup.hierarchy.model;/*
  By Chi Can Em  26-01-2018
 */


import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({@NamedQuery(name = "getAll", query = "select c from Company as c ")})
@Entity
@Table(name = "company")
/*
config cachesecond
*/
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Company {

    private int id;
    private String address;
    @NotNull
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcompany")
    public int getId() {
        return id;
    }

    private String name;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
