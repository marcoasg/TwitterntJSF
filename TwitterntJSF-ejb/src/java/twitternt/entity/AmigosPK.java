/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitternt.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Trigi
 */
@Embeddable
public class AmigosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "amigo1")
    private int amigo1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amigo2")
    private int amigo2;

    public AmigosPK() {
    }

    public AmigosPK(int amigo1, int amigo2) {
        this.amigo1 = amigo1;
        this.amigo2 = amigo2;
    }

    public int getAmigo1() {
        return amigo1;
    }

    public void setAmigo1(int amigo1) {
        this.amigo1 = amigo1;
    }

    public int getAmigo2() {
        return amigo2;
    }

    public void setAmigo2(int amigo2) {
        this.amigo2 = amigo2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) amigo1;
        hash += (int) amigo2;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AmigosPK)) {
            return false;
        }
        AmigosPK other = (AmigosPK) object;
        if (this.amigo1 != other.amigo1) {
            return false;
        }
        if (this.amigo2 != other.amigo2) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "twitternt.entity.AmigosPK[ amigo1=" + amigo1 + ", amigo2=" + amigo2 + " ]";
    }
    
}
