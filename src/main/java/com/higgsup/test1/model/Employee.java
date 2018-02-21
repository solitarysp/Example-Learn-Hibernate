package com.higgsup.test1.model;/*
  By Chi Can Em  26-01-2018
 */

public class Employee {

    private int id;
    private String name;
    private String phone;
    private company company;

    public company getCompany() {
        return company;
    }

    public void setCompany(com.higgsup.test1.model.company company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
