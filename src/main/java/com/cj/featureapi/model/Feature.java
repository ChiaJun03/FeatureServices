package com.cj.featureapi.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Feature {
    @Id
    @Column
    private int id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "features")
    Set<Client> clients;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
