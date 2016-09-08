/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.commands;

import com.sgm.model.Consulta;
import com.sgm.model.Diagnostico;
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
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author user
 */
@Controller
public class P_mController implements Serializable {

    @Autowired
    private RepositoryService csimp;

    private Medico medicoLoggado;
    private List<Consulta> consultas;
    private List<Diagnostico> diagnosticos;
    private Date date1;
    private String tipoconsulta;
    private Consulta selectedconsult;
    private HashMap<String, Object> mapEsp = new HashMap<>();
    private String especialidade;
    private Utilizador utilizador;
    private Paciente paciente;
    // diagnostic...
    private String enfermidade;
    private String enfdescription;
    private String prescri��es;

    public P_mController() {
    }

    public void guardar() {

    }

    public void registarDiagnostico() {
        RequestContext context = RequestContext.getCurrentInstance();

        Diagnostico d = new Diagnostico();
        d.setDatadiagnostico(new Date());
        d.setDescription(enfermidade + ": " +enfdescription);
        d.setMedico(medicoLoggado);
        d.setPaciente(paciente);
        
        try {
            csimp.create(d);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado com Sucesso! ", "Guardado..."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha no Registo! ", e.getLocalizedMessage()));

        }
        context.execute("PF('dlg1').hide();");
    }
    
    public void registarPrescri��o(){
        RequestContext context = RequestContext.getCurrentInstance();
        
        selectedconsult.setPrescricoes(prescri��es);
        try {
            csimp.edit(selectedconsult);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado com Sucesso! ", "Guardado..."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha no Registo! ", e.getLocalizedMessage()));

        }
        context.execute("PF('dlg2').hide();");
    }

    public String iniciarConsulta() {
        if (selectedconsult != null) {
            paciente = selectedconsult.getPaciente();
            return "consulta";
        }
        return "profile_medico";
    }

    public Medico getMedicoLoggado() {

        return medicoLoggado;
    }

    public void setMedicoLoggado(Medico medicoLoggado) {
        this.medicoLoggado = medicoLoggado;
    }

    public List<Consulta> getConsultas() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        utilizador = (Utilizador) sessionMap.get("utilizador");
        medicoLoggado = csimp.find(Medico.class, utilizador.getIduser());

        Map<String, Object> todo = new HashMap();
        todo.put("idmedico", utilizador.getIduser());
        todo.put("idestado", 6); // estado =
        List list = csimp.findByJPQuery("select cc from Consulta cc where cc.medico.iduser.iduser = :idmedico and cc.estado.idestado != :idestado", todo);
        if (list != null && !list.isEmpty()) {
            consultas = list;
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

    public String getTipoconsulta() {
        return tipoconsulta;
    }

    public void setTipoconsulta(String tipoconsulta) {
        this.tipoconsulta = tipoconsulta;
    }

    public Consulta getSelectedconsult() {
        return selectedconsult;
    }

    public String getEnfermidade() {
        return enfermidade;
    }

    public void setEnfermidade(String enfermidade) {
        this.enfermidade = enfermidade;
    }

    public RepositoryService getCsimp() {
        return csimp;
    }

    public void setCsimp(RepositoryService csimp) {
        this.csimp = csimp;
    }

    public String getEnfdescription() {
        return enfdescription;
    }

    public void setEnfdescription(String enfdescription) {
        this.enfdescription = enfdescription;
    }

    public void setSelectedconsult(Consulta selectedconsult) {
        this.selectedconsult = selectedconsult;
    }

    public HashMap<String, Object> getMapEsp() {
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

    public List<Diagnostico> getDiagnosticos() {
        if (paciente != null) {
            diagnosticos = paciente.getDiagnosticos();
        }
        return diagnosticos;
    }

    public void setDiagnosticos(List<Diagnostico> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Consulta> getConsultasPaciente() {
        return paciente.getConsultas();
    }

    public String getPrescri��es() {
        return prescri��es;
    }

    public void setPrescri��es(String prescri��es) {
        this.prescri��es = prescri��es;
    }
    
    
    
}
