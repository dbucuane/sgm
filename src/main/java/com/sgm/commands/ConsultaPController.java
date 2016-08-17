/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.commands;

import com.sgm.model.Consulta;
import com.sgm.model.Especialidade;
import com.sgm.model.Paciente;
import com.sgm.model.Utilizador;
import com.sgm.service.RepositoryService;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author user
 */
@Controller
public class ConsultaPController implements Serializable {

    @Autowired
    private RepositoryService csimp;

    private Paciente pacienteLoggado;
    private Consulta selectedconsult;
    private List<Consulta> consultas;
    
    
    private HashMap<String, Object> mapEsp=new HashMap<>();
    private String especialidade;

    public ConsultaPController() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        Utilizador utilizador = (Utilizador)sessionMap.get("utilizador");

        if (utilizador != null) {
            pacienteLoggado = csimp.find(Paciente.class, utilizador.getIduser());
        }else{
            pacienteLoggado = null;
        }
    }

    public List<Consulta> getConsultas() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        Utilizador utilizador = (Utilizador)sessionMap.get("utilizador");
        pacienteLoggado = csimp.find(Paciente.class, utilizador.getIduser());
        if (pacienteLoggado != null) {
            consultas = pacienteLoggado.getConsultas();
        }
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public Paciente getPacienteLoggado() {
        return pacienteLoggado;
    }

    public void setPacienteLoggado(Paciente pacienteLoggado) {
        this.pacienteLoggado = pacienteLoggado;
    }

    public Consulta getSelectedconsult() {
        return selectedconsult;
    }

    public void setSelectedconsult(Consulta selectedconsult) {
        this.selectedconsult = selectedconsult;
    }

    public HashMap<String, Object> getMapEsp() {
        HashMap<String, Object> map=new HashMap<>();
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
