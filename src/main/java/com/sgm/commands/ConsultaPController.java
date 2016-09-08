/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.commands;

import com.sgm.model.Consulta;
import com.sgm.model.Especialidade;
import com.sgm.model.Estado;
import com.sgm.model.Medico;
import com.sgm.model.Paciente;
import com.sgm.model.Utilizador;
import com.sgm.service.RepositoryService;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
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
    private Date date1;
    private String tipoconsulta;
    
    
    
    private HashMap<String, Object> mapEsp=new HashMap<>();
    private String especialidade;
    private HashMap<String, Object> mapmedico=new HashMap<>();
    private String medico;
    

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
    
    public void marcar() {
        RequestContext context = RequestContext.getCurrentInstance();

        Consulta consulta = new Consulta();
        consulta.setEspecialidade((Especialidade) mapEsp.get(especialidade));
        consulta.setEstado(new Estado(1)); //Marcada
        consulta.setPaciente(pacienteLoggado);
        consulta.setMedico((Medico) mapmedico.get(medico));
        consulta.setDataconsulta(date1);
        consulta.setTipoconsulta(tipoconsulta);

        try {

            csimp.create(consulta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado com Sucesso! ", "Guardado..."));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha no Registo! ", e.getLocalizedMessage()));
        }
        context.execute("PF('dlg2').hide();");
    }
    
    public void cancelarConsulta() {
        RequestContext context = RequestContext.getCurrentInstance();
        if (selectedconsult != null) {
            selectedconsult.setEstado(new Estado(3));

            try {
                csimp.edit(selectedconsult);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancelada com Sucesso! ", "Guardado..."));

            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha ao cancelar! ", e.getLocalizedMessage()));
            }
        }
        context.execute("PF('dlg3').hide();");
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

    public String getTipoconsulta() {
        return tipoconsulta;
    }

    public void setTipoconsulta(String tipoconsulta) {
        this.tipoconsulta = tipoconsulta;
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

    public RepositoryService getCsimp() {
        return csimp;
    }

    public void setCsimp(RepositoryService csimp) {
        this.csimp = csimp;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public HashMap<String, Object> getMapmedico() {
        HashMap<String, Object> map=new HashMap<>();
        for (Medico e : csimp.findAll(Medico.class)) {
            map.put(e.getName(), e);
        }
        mapmedico = map;
        return mapmedico;
    }

    public void setMapmedico(HashMap<String, Object> mapmedico) {
        this.mapmedico = mapmedico;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }
    
    public void buscar(){
    
    }

}
