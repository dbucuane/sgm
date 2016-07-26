/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Entity
@Table(catalog = "sgm", schema = "public")
public class Igroup implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idgroup;
    
    @Size(max = 45)
    @Column(length = 45)
    private String description;
    
    @Size(max = 255)
    @Column(length = 255)
    private String obs;
    
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Userole> roles;

    public Igroup() {
    }

    public Integer getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(Integer idgroup) {
        this.idgroup = idgroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public List<Userole> getRoles() {
        return roles;
    }

    public void setRoles(List<Userole> roles) {
        this.roles = roles;
    }
    
    
}
