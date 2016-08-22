/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.commands;

import com.sgm.model.Consulta;
import com.sgm.model.Especialidade;
import com.sgm.model.Estado;
import com.sgm.model.Grupo;
import com.sgm.model.Medico;
import com.sgm.model.Paciente;
import com.sgm.model.Utilizador;
import com.sgm.service.RepositoryService;
import com.sun.xml.internal.ws.api.model.MEP;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ConsultaRController implements Serializable {

    @Autowired
    private RepositoryService csimp;

    private String radio;
    private Consulta selectedconsult;
    private List<Consulta> consultas;
    private Date date1;
    private String tipoconsulta;

    private HashMap<String, Object> mapEsp = new HashMap<>();
    private String especialidade;
    private HashMap<String, Object> mapmedico = new HashMap<>();
    private String medico;
    private HashMap<String, Object> mapaciente = new HashMap<>();
    private String paciente;
    
    private boolean flag = false;

    public ConsultaRController() {
    }

    public void marcar() {
        RequestContext context = RequestContext.getCurrentInstance();

        Consulta consulta = new Consulta();
        consulta.setEspecialidade((Especialidade) mapEsp.get(especialidade));
        consulta.setEstado(new Estado(1)); //Marcada
        consulta.setPaciente((Paciente) mapaciente.get(paciente));
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
            selectedconsult.setEstado(new Estado(2));

            try {
                csimp.edit(selectedconsult);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancelada com Sucesso! ", "Guardado..."));

            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha ao cancelar! ", e.getLocalizedMessage()));
            }
        }
        context.execute("PF('dlg3').hide();");
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public Consulta getSelectedconsult() {
        return selectedconsult;
    }

    public void setSelectedconsult(Consulta selectedconsult) {
        this.selectedconsult = selectedconsult;
    }

    public List<Consulta> getConsultas() {
        if(!flag){
            consultas = csimp.findAll(Consulta.class);
            flag = false;
        }
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public HashMap<String, Object> getMapaciente() {
        HashMap<String, Object> map = new HashMap<>();
        for (Paciente e : csimp.findAll(Paciente.class)) {
            map.put(e.getName(), e);
        }
        mapaciente = map;
        return mapaciente;
    }

    public void setMapaciente(HashMap<String, Object> mapaciente) {
        this.mapaciente = mapaciente;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
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

    public HashMap<String, Object> getMapmedico() {
        HashMap<String, Object> map = new HashMap<>();
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

    public void buscar() {
        RequestContext context = RequestContext.getCurrentInstance();

        if (radio.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione Item de Busca! ", "Guardado..."));
        } else {
            flag = true;
            Map<String, Object> todo = new HashMap();
            List list;
            switch(radio){
                case "Medico" : 
                            todo.put("idmedico", ((Medico)mapmedico.get(medico)).getIduser().getIduser());
                            list = csimp.findByJPQuery("select cc from Consulta cc where cc.medico.iduser.iduser = :idmedico", todo);
                            consultas = list;
                            break;
                case "Especialidade" :
                            todo.put("idesp", ((Especialidade)mapEsp.get(especialidade)).getIdespecialidade());
                            list = csimp.findByJPQuery("select cc from Consulta cc where cc.especialidade.idespecialidade = :idesp", todo);
                            consultas = list;
                            break;
                case "Data" :
                            
                default:
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione Item de Busca! ", "Guardado..."));
                            break;
            }
            
            
        }

    }

    public String getTipoconsulta() {
        return tipoconsulta;
    }

    public void setTipoconsulta(String tipoconsulta) {
        this.tipoconsulta = tipoconsulta;
    }

}
