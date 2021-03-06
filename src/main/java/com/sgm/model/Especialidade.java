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
 * @author user
 */
@Entity
@Table(catalog = "sgm", schema = "public")
public class Especialidade implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idespecialidade;
    
    @Size(max = 45)
    @Column(length = 45)
    private String description;

    @OneToMany(mappedBy = "especialidade", fetch = FetchType.LAZY)
    private List<Consulta> consultas;
    
    @OneToMany(mappedBy = "especialidade", fetch = FetchType.LAZY)
    private List<Medico> medicos;
    
    public Especialidade() {
    }

    public Integer getIdespecialidade() {
        return idespecialidade;
    }

    public void setIdespecialidade(Integer idespecialidade) {
        this.idespecialidade = idespecialidade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    @Override
    public String toString() {
        return description;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }
    
    
}
