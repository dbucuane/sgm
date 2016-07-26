/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author user
 */
@Entity
@Table(catalog = "sgm", schema = "public")
public class Paciente implements Serializable{
    @Id
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Utilizador iduser;
    
    @Size(max = 45)
    @Column(length = 45)
    private String name;
    
    @Size(max = 45)
    @Column(length = 45)
    private String nid;
    
    @Size(max = 45)
    @Column(length = 45)
    private String profession;
    @Size(max = 45)
    @Column(length = 45)
    private String address;
    @Size(max = 45)
    @Column(length = 45)
    private String contact;
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
    private String workplace;
    
    @Column(nullable = false)
    private Integer sexo;     //1-male, 0-female
    
    private Date birthdate;
    
    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    private List<Consulta> consultas;

    public Paciente() {
    }

    public Utilizador getIduser() {
        return iduser;
    }

    public void setIduser(Utilizador iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
    
    
}
