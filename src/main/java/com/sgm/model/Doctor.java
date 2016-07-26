/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.model;

import java.io.Serializable;
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
public class Doctor implements Serializable{
    @Id
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Iuser iduserr;
    @Size(max = 45)
    @Column(length = 45)
    private String medicalfield;

    public Doctor() {
    }

    public Iuser getIduserr() {
        return iduserr;
    }

    public void setIduserr(Iuser iduserr) {
        this.iduserr = iduserr;
    }

    public String getMedicalfield() {
        return medicalfield;
    }

    public void setMedicalfield(String medicalfield) {
        this.medicalfield = medicalfield;
    }
    
    
    
}
