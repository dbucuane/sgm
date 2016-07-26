/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.commands;

import com.sgm.model.Grupo;
import com.sgm.model.Utilizador;
import com.sgm.service.RepositoryService;
import java.io.Serializable;
import java.util.HashMap;
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
    
    private List<Utilizador> utilizadors;
    private String tselected;
    
    private HashMap<String, Object> mapType=new HashMap<>();
    private String tipo;

    public P_uController() {
    }

    public void guardar() throws Exception {
        RequestContext context = RequestContext.getCurrentInstance();
        Utilizador u = new Utilizador();

        u.setEmail(email);
        u.setPassword(password);
        u.setUsername(username);
        u.setGrupo((Grupo)mapType.get(tipo)); //admins
        u.setTipo(u.getGrupo().getIdgrupo());
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Utilizador> getUtilizadors() {
        utilizadors = csimp.findAll(Utilizador.class);
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

    public HashMap<String, Object> getMapType() {
        for (Grupo grupo : csimp.findAll(Grupo.class)) {
            mapType.put(grupo.getDescription(), grupo);
        }
        return mapType;
    }

    public void setMapType(HashMap<String, Object> mapType) {
        this.mapType = mapType;
    }

}
