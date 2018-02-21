package com.higgsup.test1.model;/*
  By Chi Can Em  26-01-2018
 */


import java.util.Set;

//@NamedQueries({@NamedQuery(name = "getAll", query = "from company ")})
public class company {
    private int id;
    private String name;
    private String address;
    private Set<Employee> employee;

    public Set<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(Set<Employee> employee) {
        this.employee = employee;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
