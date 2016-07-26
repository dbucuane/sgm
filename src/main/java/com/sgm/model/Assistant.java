/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(catalog = "sgm", schema = "public")
public class Assistant implements Serializable{
    @Id
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Iuser iduserr;
    
    private String cardnumber;

    public Assistant() {
    }

    public Iuser getIduserr() {
        return iduserr;
    }

    public void setIduserr(Iuser iduserr) {
        this.iduserr = iduserr;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }
    
    
}
