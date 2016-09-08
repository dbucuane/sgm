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
public class ExameLaboratorial implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idexame;
    
    @Size(max = 45)
    @Column(length = 45)
    private String description;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataexamw;
    
    @JoinColumn(name = "paciente", referencedColumnName = "iduser_iduser")
    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente paciente;

    public ExameLaboratorial() {
    }

    public Integer getIdexame() {
        return idexame;
    }

    public void setIdexame(Integer idexame) {
        this.idexame = idexame;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDataexamw() {
        return dataexamw;
    }

    public void setDataexamw(Date dataexamw) {
        this.dataexamw = dataexamw;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    
    
}
