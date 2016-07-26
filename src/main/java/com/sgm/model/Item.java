/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Entity
@Table(catalog = "sgm", schema = "public")
public class Item implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer iditem;
    @Size(max = 45)
    @Column(length = 45)
    private String namevalue;
    @Size(max = 255)
    @Column(length = 255)
    private String url;
    @Size(max = 255)
    @Column(length = 255)
    private String icon;
    @Size(max = 255)
    @Column(length = 255)
    private String command;
    @Size(max = 255)
    @Column(length = 255)
    private String target;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<Userole> roles;
    
    public Item() {
    }

    public Integer getIditem() {
        return iditem;
    }

    public void setIditem(Integer iditem) {
        this.iditem = iditem;
    }

    public String getNamevalue() {
        return namevalue;
    }

    public void setNamevalue(String namevalue) {
        this.namevalue = namevalue;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<Userole> getRoles() {
        return roles;
    }

    public void setRoles(List<Userole> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.iditem, other.iditem)) {
            return false;
        }
        if (!Objects.equals(this.namevalue, other.namevalue)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.icon, other.icon)) {
            return false;
        }
        if (!Objects.equals(this.command, other.command)) {
            return false;
        }
        if (!Objects.equals(this.target, other.target)) {
            return false;
        }
        return true;
    }
    
    
}
