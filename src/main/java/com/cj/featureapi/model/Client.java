package com.cj.featureapi.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Client {
    @Id
    @Column
    private int id;

    @Column
    private String email;

    @ManyToMany
    @JoinTable(
            name = "client_features",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id")
    )
    Set<Feature> features;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    public boolean canAccess(String featureName) {
        boolean hasAccess = false;
        for (Feature feature : features) {
            if (feature.getName().equals(featureName)) hasAccess = true;
        }
        return hasAccess;
    }
}
