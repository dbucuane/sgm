/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Size;

/**
 *
 * @author user
 */
@Entity
@Table(catalog = "sgm", schema = "public")
public class Diagnostico implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idiagnostico;
    
    @Size(max = 45)
    @Column(length = 45)
    private String description;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datadiagnostico;
    
    @JoinColumn(name = "paciente", referencedColumnName = "iduser_iduser")
    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente paciente;
    
    @JoinColumn(name = "medico", referencedColumnName = "iduser_iduser")
    @ManyToOne(fetch = FetchType.LAZY)
    private Medico medico;

    public Diagnostico() {
    }

    public Integer getIdiagnostico() {
        return idiagnostico;
    }

    public void setIdiagnostico(Integer idiagnostico) {
        this.idiagnostico = idiagnostico;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatadiagnostico() {
        return datadiagnostico;
    }

    public void setDatadiagnostico(Date datadiagnostico) {
        this.datadiagnostico = datadiagnostico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    
    
}
