/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */

@Entity
@Table(catalog = "sgm", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Utilizador.findUser", query = "SELECT u FROM Iuser u WHERE u.username = :username and u.password = :password")})
public class Iuser implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer iduserr;
    @Size(max = 45)
    @Column(length = 45)
    private String fullname;
    @Size(max = 45)
    @Column(length = 45)
    private String username;
    @Size(max = 45)
    @Column(length = 45)
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(length = 45)
    private String email;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastaccess;
    
    private int isMale;
    @Size(max = 45)
    @Column(length = 45)
    private String phone;
    @Size(max = 45)
    @Column(length = 45)
    private String address;
    
    @JoinColumn(name = "type", referencedColumnName = "idtype")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usertype type;
    
    @JoinColumn(name = "group", referencedColumnName = "idgroup")
    @ManyToOne(fetch = FetchType.LAZY)
    private Igroup group;

    public Iuser() {
    }

    public Integer getIduserr() {
        return iduserr;
    }

    public void setIduserr(Integer iduserr) {
        this.iduserr = iduserr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIsMale() {
        return isMale;
    }

    public void setIsMale(int isMale) {
        this.isMale = isMale;
    }

    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastaccess() {
        return lastaccess;
    }

    public void setLastaccess(Date lastaccess) {
        this.lastaccess = lastaccess;
    }

    public Igroup getGroup() {
        return group;
    }

    public void setGroup(Igroup group) {
        this.group = group;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    

    public Usertype getType() {
        return type;
    }

    public void setType(Usertype type) {
        this.type = type;
    }

    
    
}
