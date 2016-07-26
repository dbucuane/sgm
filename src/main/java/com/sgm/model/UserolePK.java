/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Embeddable
public class UserolePK implements Serializable{
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int group;
    
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int item;

    public UserolePK() {
    }

    public UserolePK(int group, int item) {
        this.group = group;
        this.item = item;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }
    
    
}
