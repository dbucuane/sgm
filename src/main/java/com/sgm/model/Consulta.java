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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Size;

/**
 *
 * @author user
 */
@Entity
@Table(catalog = "sgm", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Consulta.findByIdconsulta", query = "SELECT c FROM Consulta c WHERE c.idconsulta = :idconsulta"),
    @NamedQuery(name = "Consulta.findByDataconsulta", query = "SELECT c FROM Consulta c WHERE c.dataconsulta = :dataconsulta"),
    @NamedQuery(name = "Consulta.findByTipoconsulta", query = "SELECT c FROM Consulta c WHERE c.tipoconsulta = :tipoconsulta"),
    @NamedQuery(name = "Consulta.findByIdMedicoAndIdestado", query = "SELECT c FROM Consulta c WHERE c.medico = :idmedico AND c.estado != :idestado")})
public class Consulta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idconsulta;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataconsulta;
    
    @Size(max = 45)
    @Column(length = 45)
    private String ctime;

    @JoinColumn(name = "especialidade", referencedColumnName = "idespecialidade")
    @ManyToOne(fetch = FetchType.LAZY)
    private Especialidade especialidade;

    @Size(max = 45)
    @Column(length = 45)
    private String tipoconsulta;

    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne(fetch = FetchType.LAZY)
    private Estado estado;

    @JoinColumn(name = "paciente", referencedColumnName = "iduser_iduser")
    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente paciente;

    @JoinColumn(name = "medico", referencedColumnName = "iduser_iduser")
    @ManyToOne(fetch = FetchType.LAZY)
    private Medico medico;
    
    @Size(max = 45)
    @Column(length = 45)
    private String prescricoes;

    public Consulta() {
    }

    public Integer getIdconsulta() {
        return idconsulta;
    }

    public void setIdconsulta(Integer idconsulta) {
        this.idconsulta = idconsulta;
    }

    public Date getDataconsulta() {
        return dataconsulta;
    }

    public void setDataconsulta(Date dataconsulta) {
        this.dataconsulta = dataconsulta;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public String getTipoconsulta() {
        return tipoconsulta;
    }

    public void setTipoconsulta(String tipoconsulta) {
        this.tipoconsulta = tipoconsulta;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getPrescricoes() {
        return prescricoes;
    }

    public void setPrescricoes(String prescricoes) {
        this.prescricoes = prescricoes;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

}
