/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.commands;

import com.sgm.model.Medico;
import com.sgm.service.RepositoryService;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author user
 */
@Controller
public class MedicosController implements Serializable{
    
    @Autowired
    private RepositoryService csimp;
    
    private List<Medico> medicos;

    public MedicosController() {
        
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }
    
    
}
