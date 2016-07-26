/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Entity
@Table(catalog = "sgm", schema = "public")
public class Patient implements Serializable{
    @Id
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Iuser iduserr;
    
    @Size(max = 45)
    @Column(length = 45)
    private String nid;
    
    private Date birthdate;
    
    @Size(max = 45)
    @Column(length = 45)
    private String nationality;
    @Size(max = 45)
    @Column(length = 45)
    private String naturality;
    @Size(max = 45)
    @Column(length = 45)
    private String maritalstatus;
    @Size(max = 45)
    @Column(length = 45)
    private String profession;
    @Size(max = 45)
    @Column(length = 45)
    private String workplace;
    @Size(max = 45)
    @Column(length = 45)
    private String filiation;

    public Patient() {
    }

    public Iuser getIduserr() {
        return iduserr;
    }

    public void setIduserr(Iuser iduserr) {
        this.iduserr = iduserr;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNaturality() {
        return naturality;
    }

    public void setNaturality(String naturality) {
        this.naturality = naturality;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getFiliation() {
        return filiation;
    }

    public void setFiliation(String filiation) {
        this.filiation = filiation;
    }
    
    
    
}
