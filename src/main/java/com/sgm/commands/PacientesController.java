/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.commands;

import com.sgm.model.Grupo;
import com.sgm.model.Paciente;
import com.sgm.model.Utilizador;
import com.sgm.service.RepositoryService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author user
 */
@Controller
public class PacientesController implements Serializable{
    
    @Autowired
    private RepositoryService csimp;
    
    private List<Paciente> pacientes;
    private String name;

    private String nid;
    private String profession;
    private String address;
    private String contact;
    private String nationality;
    private String naturality;
    private String maritalstatus;
    private String workplace;
    private Integer sexo;     //1-male, 0-female
    private Date birthdate;
    private String email;

    public PacientesController() {
    }
    
    public void guardar(){
        RequestContext context = RequestContext.getCurrentInstance();
        Utilizador u = new Utilizador();

        u.setEmail(email);
        u.setPassword("123456789");
        u.setUsername(email);
        u.setGrupo(new Grupo(4)); 
        u.setTipo(u.getGrupo().getIdgrupo());
        u.setFullname(name);

        try {
            csimp.create(u);

            Paciente p = new Paciente();
            p.setAddress(address);
            p.setBirthdate(birthdate);
            p.setContact(contact);
            p.setName(name);
            p.setNationality(nationality);
            p.setNaturality(naturality);
            p.setNid(nid);
            p.setProfession(profession);
            p.setSexo(sexo);
            p.setWorkplace(workplace);
            p.setMaritalstatus(maritalstatus);
            p.setIduser(u);
            
            csimp.create(p);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado com Sucesso! ", "Guardado..."));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha no Registo! ", e.getLocalizedMessage()));
        }
        context.execute("PF('dlg2').hide();");
    
    }

    public List<Paciente> getPacientes() {
        pacientes = csimp.findAll(Paciente.class);
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
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

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
