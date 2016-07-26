/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.commands;

import com.sgm.model.Utilizador;
import com.sgm.service.RepositoryService;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author User
 */
@Controller
public class P_uController implements Serializable {

    @Autowired
    private RepositoryService csimp;

    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phone;
    private String address;

    private String isMale;
    //-----------------------assistant----------
    private String cardnumber;
    //-----------------------doctor----------
    private String medicalfield;
    //-----------------------patient----------
    private String nID;
    private String bithdate;
    private String nationality;
    private String naturality;
    private String maritalstatus;
    private String profession;
    private String workplace;
    private String filiation;

    private List<Utilizador> utilizadors;
    private String tselected;

    public P_uController() {
    }

    public void guardar() throws Exception {
        RequestContext context = RequestContext.getCurrentInstance();
        Utilizador u = new Utilizador();

        u.setEmail(email);
        u.setPassword(password);
        u.setUsername(username);
        u.setTipo(1);
        u.setFullname(fullname);
        try {
            csimp.create(u);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado com Sucesso! ", "Guardado..."));
         
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha no Registo! ", e.getLocalizedMessage()));
        }
        context.execute("PF('dlg2').hide();");
    }

    public String getTselected() {
        return tselected;
    }

    public void setTselected(String tselected) {
        this.tselected = tselected;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIsMale() {
        return isMale;
    }

    public void setIsMale(String isMale) {
        this.isMale = isMale;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getMedicalfield() {
        return medicalfield;
    }

    public void setMedicalfield(String medicalfield) {
        this.medicalfield = medicalfield;
    }

    public String getnID() {
        return nID;
    }

    public void setnID(String nID) {
        this.nID = nID;
    }

    public String getBithdate() {
        return bithdate;
    }

    public void setBithdate(String bithdate) {
        this.bithdate = bithdate;
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

    public List<Utilizador> getUtilizadors() {
        return utilizadors;
    }

    public void setUtilizadors(List<Utilizador> utilizadors) {
        this.utilizadors = utilizadors;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
