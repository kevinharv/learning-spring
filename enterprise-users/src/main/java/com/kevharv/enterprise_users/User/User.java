package com.kevharv.enterprise_users.User;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;

@Entity
@Table(name="users")
public class User {
    private @Id @GeneratedValue Long Id;
    private String employeeId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String displayName;
    private String userPrincipalName;
    private String preferredEmailAddress;
    private String personalEmailAddress;
    private String workPhone;
    private String personalPhone;
    // Linux info
    // AD info
    // Groups?
    private Date passwordLastSet;
    private Date dateCreated;
    private Date lastUpdated;
    
    public User() {}

    public User(String employeeId) {
        this.employeeId = employeeId;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserPrincipalName() {
        return userPrincipalName;
    }

    public void setUserPrincipalName(String userPrincipalName) {
        this.userPrincipalName = userPrincipalName;
    }

    public String getPreferredEmailAddress() {
        return preferredEmailAddress;
    }

    public void setPreferredEmailAddress(String preferredEmailAddress) {
        this.preferredEmailAddress = preferredEmailAddress;
    }

    public String getPersonalEmailAddress() {
        return personalEmailAddress;
    }

    public void setPersonalEmailAddress(String personalEmailAddress) {
        this.personalEmailAddress = personalEmailAddress;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getPersonalPhone() {
        return personalPhone;
    }

    public void setPersonalPhone(String personalPhone) {
        this.personalPhone = personalPhone;
    }

    public Date getPasswordLastSet() {
        return passwordLastSet;
    }

    public void setPasswordLastSet(Date passwordLastSet) {
        this.passwordLastSet = passwordLastSet;
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
    
}
