/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.commands;

import com.sgm.service.RepositoryService;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author User
 */
@Controller
public class Test implements Serializable{
    
    @Autowired
    private RepositoryService csimp;
    

    public Test() {
    }
    
    public String getall(){
        String a = "";
//        for (Group g : csimp.findAll(Group.class) ){
//            a +=" - "+g.getRoles().toString();
//        }
        return a;
    }
    
}
