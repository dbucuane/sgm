/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.commands;

import com.sgm.model.Grupo;
import com.sgm.model.Item;
import com.sgm.service.RepositoryService;
import java.io.Serializable;
import java.util.ArrayList;
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
public class P_gController implements Serializable{
    @Autowired
    private RepositoryService csimp;
    
    private String [] selectedItems;
    private String nome;
    private String obs;
    private Grupo selectedgroup;
    private List<Grupo> grupos;
    private List<Item> items;
    
    public void guardar() throws Exception {
        RequestContext context = RequestContext.getCurrentInstance();
        List<Item> ites=new ArrayList<>();
        
        Grupo g = new Grupo();
        g.setDescription(nome);
        g.setObs(obs);
        //-------------------
        
        for(String s:selectedItems){
            for (Item ite : items) {
                if(ite.getNameValue().equals(s)){
                    ites.add(ite);
                }
            }
        }
        
        g.setItems(ites);
        try {
            csimp.create(g);
        } catch (Exception e) {
            System.out.println(""+e.getMessage());
        }
        
        //grupos=gjc.findGrupoEntities();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado com Sucesso!", "Guardado..."));
        context.execute("PF('dlg2').hide();");
        
    }

    public String[] getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(String[] selectedItems) {
        this.selectedItems = selectedItems;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Grupo getSelectedgroup() {
        return selectedgroup;
    }

    public void setSelectedgroup(Grupo selectedgroup) {
        this.selectedgroup = selectedgroup;
    }

    public List<Grupo> getGrupos() {
        grupos = csimp.findAll(Grupo.class);
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public List<Item> getItems() {
        items = csimp.findAll(Item.class);
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    
}
