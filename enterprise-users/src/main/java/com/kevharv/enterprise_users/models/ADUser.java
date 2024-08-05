package com.kevharv.enterprise_users.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "ad_users")
public class ADUser {
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long Id;
    private String dn;
    private String sAMAccountName;

    @OneToOne(mappedBy = "adUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    public ADUser() {}

    public ADUser(String dn, String sAMAccountName) {
        this.dn = dn;
        this.sAMAccountName = sAMAccountName;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getsAMAccountName() {
        return sAMAccountName;
    }

    public void setsAMAccountName(String sAMAccountName) {
        this.sAMAccountName = sAMAccountName;
    }

    
}
