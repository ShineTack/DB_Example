package com.example.db.models;

public class Car {
    private Integer id;
    private String model;
    private Integer ownerId;

    public Car(Integer id, String model, Integer ownerId) {
        this.id = id;
        this.model = model;
        this.ownerId = ownerId;
    }

    public Car(String model, Integer ownerId) {
        this.model = model;
        this.ownerId = ownerId;
    }

    public Car(String model) {
        this.model = model;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
}
