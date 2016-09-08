/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.commands;

import com.sgm.model.Especialidade;
import com.sgm.model.Grupo;
import com.sgm.model.Medico;
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
 * @author user
 */
@Controller
public class MedicosController implements Serializable {

    @Autowired
    private RepositoryService csimp;

    private List<Medico> medicos;
    private String cardnumber;
    private String nome;
    private String email;
    private Integer sexo;

    private HashMap<String, Object> mapEsp = new HashMap<>();
    private String especialidade;

    public MedicosController() {
    }

    public void guardar() {
        RequestContext context = RequestContext.getCurrentInstance();
        Utilizador u = new Utilizador();

        u.setEmail(email);
        u.setPassword("123456789");
        u.setUsername(email);
        u.setGrupo(new Grupo(3)); //admins
        u.setTipo(u.getGrupo().getIdgrupo());
        u.setFullname(nome);

        try {
            csimp.create(u);

            Medico m = new Medico();
            m.setIduser(u);
            m.setCardnumber(cardnumber);
            m.setEmail(email);
            m.setEspecialidade((Especialidade) mapEsp.get(especialidade));
            m.setName(nome);
            m.setSexo(sexo);
            
            csimp.create(m);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado com Sucesso! ", "Guardado..."));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha no Registo! ", e.getLocalizedMessage()));
        }
        context.execute("PF('dlg2').hide();");
    }

    public List<Medico> getMedicos() {
        medicos = csimp.findAll(Medico.class);
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public HashMap<String, Object> getMapEsp() {
        HashMap<String, Object> map = new HashMap<>();
        
        for (Especialidade e : csimp.findAll(Especialidade.class)) {
            map.put(e.getDescription(), e);
        }
        mapEsp = map;
        return mapEsp;
    }

    public void setMapEsp(HashMap<String, Object> mapEsp) {
        this.mapEsp = mapEsp;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

}
