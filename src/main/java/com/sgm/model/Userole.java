/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(catalog = "sgm", schema = "public")
public class Userole implements Serializable{
    @EmbeddedId
    protected UserolePK userolePK;
    
    @JoinColumn(name = "group", referencedColumnName = "idgroup", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Igroup group;
    
    @JoinColumn(name = "item", referencedColumnName = "iditem", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Item item;

    public Userole() {
    }

    public Userole(UserolePK userolePK) {
        this.userolePK = userolePK;
    }

    public UserolePK getUserolePK() {
        return userolePK;
    }

    public void setUserolePK(UserolePK userolePK) {
        this.userolePK = userolePK;
    }

    public Igroup getGroup() {
        return group;
    }

    public void setGroup(Igroup group) {
        this.group = group;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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
        final Userole other = (Userole) obj;
        if (!Objects.equals(this.group, other.group)) {
            return false;
        }
        if (!Objects.equals(this.item, other.item)) {
            return false;
        }
        return true;
    }
    
    
}
