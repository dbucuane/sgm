/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Medico implements Serializable{
    @Id
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Utilizador iduser;
    
    @Size(max = 45)
    @Column(length = 45)
    private String name;
    
    @Size(max = 45)
    @Column(length = 45)
    private String email;
    @Size(max = 45)
    @Column(length = 45)
    private String cardnumber;
    
    @Column(nullable = false)
    private Integer sexo;     //1-male, 0-female
    
    @JoinColumn(name = "especialidade", referencedColumnName = "idespecialidade")
    @ManyToOne(fetch = FetchType.LAZY)
    private Especialidade especialidade;
    
    @OneToMany(mappedBy = "medico", fetch = FetchType.LAZY)
    private List<Consulta> consultas;

    public Medico() {
    }

    public Medico(Utilizador iduser) {
        this.iduser = iduser;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
    
    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
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

    @Override
    public String toString() {
        return name;
    }
    
    
}
