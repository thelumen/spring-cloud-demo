package com.ri.springclouddemo;

public class User {
    private String name;
    private String pool;
    private String azname;
    private double rate;

    public double getRate() {
        return rate;
    }

    public User setRate(double rate) {
        this.rate = rate;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;

    }

    public String getPool() {
        return pool;
    }

    public User setPool(String pool) {
        this.pool = pool;
        return this;

    }

    public String getAzname() {
        return azname;
    }

    public User setAzname(String azname) {
        this.azname = azname;
        return this;

    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", pool='" + pool + '\'' +
                ", azname='" + azname + '\'' +
                ", rate=" + rate +
                '}';
    }
}
