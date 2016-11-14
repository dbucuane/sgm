/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.commands;

import com.sgm.model.Especialidade;
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
public class EspecialidadeController implements Serializable{
    
    @Autowired
    private RepositoryService csimp;
    
    private List<Especialidade> especialidades;
    private String description;

    public EspecialidadeController() {
    }

    public List<Especialidade> getEspecialidades() {
        especialidades = csimp.findAll(Especialidade.class);
        return especialidades;
    }

    public void guardar(){
        RequestContext context = RequestContext.getCurrentInstance();
        
        Especialidade es = new Especialidade();
        es.setDescription(description);
        try {
            csimp.create(es);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado com Sucesso! ", "Guardado..."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha no Registo! ", e.getLocalizedMessage()));
        }
        
        context.execute("PF('dlg2').hide();");
    }
    
    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
