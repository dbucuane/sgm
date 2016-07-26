/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.commands;

import com.sgm.model.Iuser;
import com.sgm.model.Userole;
import com.sgm.service.RepositoryService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author User
 */
@Controller
public class AuthenticationService implements Serializable{

    @Autowired
    private RepositoryService csimp;

    private String username;
    private String password;
    private List<String> images;
    private boolean loggedIn;
    private MenuModel menuModel;
    private Iuser user;

    public AuthenticationService() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void opendialog() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialog1').show();");
    }

    public String logout() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
    }

    public MenuModel getMenuModel() {
        menuModel = new DefaultMenuModel();

        DefaultSubMenu firstSubmenu = new DefaultSubMenu(user.getGroup().getDescription());
        for (Userole role: user.getGroup().getRoles()) {

            DefaultMenuItem itemm = new DefaultMenuItem(role.getItem().getNamevalue());
            itemm.setUrl(role.getItem().getUrl());
            itemm.setIcon(role.getItem().getIcon());
            itemm.setTarget(role.getItem().getTarget());
            firstSubmenu.addElement(itemm);
        }
        menuModel.addElement(firstSubmenu);
        return menuModel;
    }
    
    public String login() throws Exception {

        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        loggedIn = false;

        if (username.isEmpty() || password.isEmpty()) {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro: Dados Incompletos", "Dados Incompletos");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "login";

        } else {
            /*Map<String, Object> todo = new HashMap<String, Object>();
            todo.put("username", username);
            todo.put("password", password);*/
            user = null;
            //List users = csimp.findByJPQuery("select aa from Iuser aa where aa.username like ':username' and aa.password like ':password'", todo);
            //Iuser user = csimp.GetUniqueEntityByNamedQuery("Utilizador.findUser", todo);
            for (Iuser u : csimp.findAll(Iuser.class)) {
                if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                      user = u;  
                      break;
                }
            }

            if (user != null) {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem Vindo " + user.getEmail(), username);
                FacesContext.getCurrentInstance().addMessage(null, message);
                loggedIn = true;
                HttpSession session=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("utilizador", user);
                
                return "homepage";
            }
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: Dados Incorrectos", "Dados Incorrectos");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "login";

        }
        

    }
    public List<String> getImages() {
        images = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            images.add("img" + i + ".jpg");
        }

        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}