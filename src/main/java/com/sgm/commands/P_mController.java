/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.commands;

import com.sgm.model.Consulta;
import com.sgm.model.Diagnostico;
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
    private Utilizador utilizador;
    private Paciente paciente;
    private String cpaciente;
    // diagnostic...
    private String enfermidade;
    private String enfdescription;
    private String prescrições;
    private HashMap<String, Object> mapaciente = new HashMap<>();

    public P_mController() {
    }

    public void guardar() {
        RequestContext context = RequestContext.getCurrentInstance();

        Consulta consulta = new Consulta();
        consulta.setEspecialidade(medicoLoggado.getEspecialidade());
        consulta.setEstado(new Estado(1)); //Marcada
        consulta.setPaciente((Paciente) mapaciente.get(cpaciente));
        consulta.setMedico(medicoLoggado);
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
    
    public void registarPrescrição(){
        RequestContext context = RequestContext.getCurrentInstance();
        
        selectedconsult.setPrescricoes(prescrições);
        try {
            csimp.edit(selectedconsult);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado com Sucesso! ", "Guardado..."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha no Registo! ", e.getLocalizedMessage()));

        }
        context.execute("PF('dlg2').hide();");
    }

    public String getCpaciente() {
        return cpaciente;
    }

    public void setCpaciente(String cpaciente) {
        this.cpaciente = cpaciente;
    }

    public String iniciarConsulta() {
        if (selectedconsult != null) {
            paciente = selectedconsult.getPaciente();
            return "consulta";
        }
        return "profile_medico";
    }

    public String terminarConsulta(){
        if (selectedconsult != null) {
            try {
                selectedconsult.setEstado(new Estado(2));
                csimp.edit(selectedconsult);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Terminado com Sucesso! ", "Guardado..."));
            } catch (Exception e) {
            }
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

    

    public List<Diagnostico> getDiagnosticos() {
        Consulta c = csimp.find(Consulta.class, selectedconsult.getIdconsulta());
        if (c != null) {
            diagnosticos = c.getPaciente().getDiagnosticos();
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
        Consulta c = csimp.find(Consulta.class, selectedconsult.getIdconsulta());
        return c.getPaciente().getConsultas();
    }

    public String getPrescrições() {
        return prescrições;
    }

    public void setPrescrições(String prescrições) {
        this.prescrições = prescrições;
    }
    
    
    
}
