package com.kevharv.enterprise_users.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "groups")
public class Group {
    private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long Id;
    private String name;
    private String description;
    // AD info?
    private Date dateCreated;
    private Date lastUpdated;

    // User membership
    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();


    public Group() {}

    public Group(String name) {
        this.name = name;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    @PrePersist
    protected void onCreate() {
        this.dateCreated = new Date();
    }
    
    public Date getLastUpdated() {
        return this.lastUpdated;
    }
    
    @PostUpdate
    protected void onUpdate() {
        this.lastUpdated = new Date();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Id == null) ? 0 : Id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((users == null) ? 0 : users.hashCode());
        result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
        result = prime * result + ((lastUpdated == null) ? 0 : lastUpdated.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Group other = (Group) obj;
        if (Id == null) {
            if (other.Id != null)
                return false;
        } else if (!Id.equals(other.Id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (users == null) {
            if (other.users != null)
                return false;
        } else if (!users.equals(other.users))
            return false;
        if (dateCreated == null) {
            if (other.dateCreated != null)
                return false;
        } else if (!dateCreated.equals(other.dateCreated))
            return false;
        if (lastUpdated == null) {
            if (other.lastUpdated != null)
                return false;
        } else if (!lastUpdated.equals(other.lastUpdated))
            return false;
        return true;
    }
}
